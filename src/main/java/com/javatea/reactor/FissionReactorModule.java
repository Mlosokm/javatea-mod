package com.javatea.reactor;

import com.javatea.JavaTea;
import com.mojang.math.Vector3f;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public final class FissionReactorModule {
    private FissionReactorModule() {}

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, JavaTea.MOD_ID);
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, JavaTea.MOD_ID);

    private static BlockBehaviour.Properties metal(float hardness) {
        return BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                .strength(hardness, 6.0F).sound(SoundType.METAL);
    }

    public static final RegistryObject<Block> REACTOR_CASING = block("reactor_casing",
            () -> new Block(metal(5.0F)));
    public static final RegistryObject<Block> REACTOR_FUEL_ROD_ASSEMBLY = block("reactor_fuel_rod_assembly",
            () -> new Block(metal(4.0F)));
    public static final RegistryObject<Block> REACTOR_ROD_CONTROLLER_ASSEMBLY = block("reactor_rod_controller_assembly",
            () -> new Block(metal(4.0F)));
    public static final RegistryObject<Block> REACTOR_INPUT_PORT = block("reactor_input_port",
            () -> new Block(metal(3.0F).noOcclusion()));
    public static final RegistryObject<Block> REACTOR_OUTPUT_PORT = block("reactor_output_port",
            () -> new Block(metal(3.0F).noOcclusion()));
    public static final RegistryObject<Block> REACTOR_WASTE_OUTPUT_PORT = block("reactor_waste_output_port",
            () -> new Block(metal(3.0F).noOcclusion()));
    public static final RegistryObject<Block> REACTOR_CONTROLLER = block("reactor_controller",
            () -> new FissionControllerBlock(metal(4.0F).noOcclusion()));

    private static RegistryObject<Block> block(String name,
                                                 java.util.function.Supplier<Block> supplier) {
        RegistryObject<Block> registered = BLOCKS.register(name, supplier);
        ITEMS.register(name, () -> new BlockItem(registered.get(), new Item.Properties()));
        return registered;
    }

    public static void register(IEventBus modBus) {
        BLOCKS.register(modBus);
        ITEMS.register(modBus);
    }

    public static final class FissionControllerBlock extends Block {
        public static final BooleanProperty ENABLED = BooleanProperty.create("enabled");
        public static final BooleanProperty RUNNING = BooleanProperty.create("running");

        public FissionControllerBlock(Properties properties) {
            super(properties);
            registerDefaultState(stateDefinition.any()
                    .setValue(ENABLED, false).setValue(RUNNING, false));
        }

        @Override
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
            builder.add(ENABLED, RUNNING);
        }

        @Override
        public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                     InteractionHand hand, BlockHitResult hit) {
            if (level.isClientSide) return InteractionResult.SUCCESS;

            if (state.getValue(RUNNING)) {
                level.setBlock(pos, state.setValue(RUNNING, false), 3);
                player.displayClientMessage(Component.literal("Reactor controller: stopped."), true);
                return InteractionResult.CONSUME;
            }

            if (!state.getValue(ENABLED)) {
                level.setBlock(pos, state.setValue(ENABLED, true), 3);
                player.displayClientMessage(Component.literal("Controller enabled. Press again to validate."), true);
                return InteractionResult.CONSUME;
            }

            ValidationResult result = validate((ServerLevel) level, pos);
            if (result.valid) {
                level.setBlock(pos, state.setValue(RUNNING, true), 3);
                player.displayClientMessage(Component.literal("Structure valid. Reactor status: RUNNING."), true);
            } else {
                player.displayClientMessage(Component.literal(
                        "Structure invalid. Red = incorrect block; amber = missing block."), true);
            }
            return InteractionResult.CONSUME;
        }
    }

    private static final class ValidationResult {
        final boolean valid;
        ValidationResult(boolean valid) { this.valid = valid; }
    }

    private static ValidationResult validate(ServerLevel level, BlockPos controllerPos) {
        BlockPos start = findNearbyCasing(level, controllerPos);
        if (start == null) {
            mark(level, controllerPos, false);
            return new ValidationResult(false);
        }

        Set<BlockPos> shell = collectConnectedShell(level, start);
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, minZ = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE, maxZ = Integer.MIN_VALUE;
        for (BlockPos p : shell) {
            minX = Math.min(minX, p.getX()); minY = Math.min(minY, p.getY()); minZ = Math.min(minZ, p.getZ());
            maxX = Math.max(maxX, p.getX()); maxY = Math.max(maxY, p.getY()); maxZ = Math.max(maxZ, p.getZ());
        }

        boolean valid = true;
        if (maxX - minX < 2 || maxY - minY < 2 || maxZ - minZ < 2) valid = false;

        // Every position on the rectangular outer boundary must be casing or a reactor port.
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    if (!boundary(x, y, z, minX, minY, minZ, maxX, maxY, maxZ)) continue;
                    BlockPos p = new BlockPos(x, y, z);
                    if (!isShellBlock(level.getBlockState(p))) {
                        mark(level, p, false);
                        valid = false;
                    }
                }
            }
        }

        // Each FRA column must be continuous and have exactly one RRC directly above it.
        Set<BlockPos> expectedRrc = new HashSet<>();
        for (int x = minX + 1; x < maxX; x++) {
            for (int z = minZ + 1; z < maxZ; z++) {
                boolean seenFra = false;
                boolean gapAfterFra = false;
                int topFraY = Integer.MIN_VALUE;
                for (int y = minY + 1; y < maxY; y++) {
                    BlockPos p = new BlockPos(x, y, z);
                    boolean fra = level.getBlockState(p).is(REACTOR_FUEL_ROD_ASSEMBLY.get());
                    if (fra) {
                        if (gapAfterFra) { mark(level, p, true); valid = false; }
                        seenFra = true;
                        topFraY = y;
                    } else if (seenFra) {
                        gapAfterFra = true;
                    }
                }
                if (seenFra) {
                    BlockPos cap = new BlockPos(x, topFraY + 1, z);
                    expectedRrc.add(cap);
                    if (!level.getBlockState(cap).is(REACTOR_ROD_CONTROLLER_ASSEMBLY.get())) {
                        mark(level, cap, false);
                        valid = false;
                    }
                }
            }
        }

        // RRC blocks anywhere other than the top of an FRA column are invalid.
        for (int x = minX + 1; x < maxX; x++) {
            for (int y = minY + 1; y < maxY; y++) {
                for (int z = minZ + 1; z < maxZ; z++) {
                    BlockPos p = new BlockPos(x, y, z);
                    if (level.getBlockState(p).is(REACTOR_ROD_CONTROLLER_ASSEMBLY.get())
                            && !expectedRrc.contains(p)) {
                        mark(level, p, true);
                        valid = false;
                    }
                }
            }
        }

        // Every FRA and its RRC cap must remain fully inside the casing.
        for (BlockPos p : shell) {
            if (level.getBlockState(p).is(REACTOR_FUEL_ROD_ASSEMBLY.get())
                    || level.getBlockState(p).is(REACTOR_ROD_CONTROLLER_ASSEMBLY.get())) {
                mark(level, p, true);
                valid = false;
            }
        }
        return new ValidationResult(valid);
    }

    private static BlockPos findNearbyCasing(ServerLevel level, BlockPos center) {
        BlockPos best = null;
        double bestDistance = Double.MAX_VALUE;
        for (BlockPos p : BlockPos.betweenClosed(center.offset(-16, -16, -16), center.offset(16, 16, 16))) {
            if (level.getBlockState(p).is(REACTOR_CASING.get())) {
                double d = p.distToCenterSqr(center.getX() + 0.5, center.getY() + 0.5, center.getZ() + 0.5);
                if (d < bestDistance) { bestDistance = d; best = p.immutable(); }
            }
        }
        return best;
    }

    private static Set<BlockPos> collectConnectedShell(ServerLevel level, BlockPos start) {
        Set<BlockPos> visited = new HashSet<>();
        ArrayDeque<BlockPos> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty() && visited.size() < 6000) {
            BlockPos p = queue.removeFirst();
            if (!visited.add(p) || !isShellBlock(level.getBlockState(p))) continue;
            for (BlockPos next : new BlockPos[]{p.north(), p.south(), p.east(), p.west(), p.above(), p.below()}) {
                if (!visited.contains(next) && next.closerThan(start, 24)) queue.addLast(next);
            }
        }
        visited.removeIf(p -> !isShellBlock(level.getBlockState(p)));
        return visited;
    }

    private static boolean isShellBlock(BlockState state) {
        return state.is(REACTOR_CASING.get()) || state.is(REACTOR_INPUT_PORT.get())
                || state.is(REACTOR_OUTPUT_PORT.get()) || state.is(REACTOR_WASTE_OUTPUT_PORT.get());
    }

    private static boolean boundary(int x, int y, int z, int minX, int minY, int minZ,
                                    int maxX, int maxY, int maxZ) {
        return x == minX || x == maxX || y == minY || y == maxY || z == minZ || z == maxZ;
    }

    private static void mark(ServerLevel level, BlockPos pos, boolean invalid) {
        DustParticleOptions dust = new DustParticleOptions(
                invalid ? new Vector3f(0.95F, 0.05F, 0.03F) : new Vector3f(1.0F, 0.55F, 0.04F),
                1.15F);
        level.sendParticles(dust, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                18, 0.32, 0.32, 0.32, 0.01);
        level.sendParticles(ParticleTypes.CRIT, pos.getX() + 0.5, pos.getY() + 0.5,
                pos.getZ() + 0.5, 3, 0.2, 0.2, 0.2, 0.02);
    }
}
