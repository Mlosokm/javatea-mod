package com.javatea.blockentity;

import com.javatea.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * Block-entity types used by the original steam-machine registry.
 *
 * These are persistence/registration foundations. Individual machine
 * processing (steam transfer, pressure, fuel, power output, etc.) is not
 * implemented by this compatibility layer.
 */
public final class MachineBlockEntities {
    private MachineBlockEntities() {}

    public abstract static class MachineBlockEntity extends BlockEntity {
        protected MachineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
            super(type, pos, state);
        }

        public void serverTick(Level level, BlockPos pos, BlockState state) {
            // Machine-specific simulation will be implemented by each machine.
        }
    }

    public static final class BoilerBlockEntity extends MachineBlockEntity {
        public BoilerBlockEntity(BlockPos pos, BlockState state) {
            super(ModBlockEntities.BOILER.get(), pos, state);
        }
    }

    public static final class SteamTankBlockEntity extends MachineBlockEntity {
        public SteamTankBlockEntity(BlockPos pos, BlockState state) {
            super(ModBlockEntities.STEAM_TANK.get(), pos, state);
        }
    }

    public static final class FluidPortBlockEntity extends MachineBlockEntity {
        public FluidPortBlockEntity(BlockPos pos, BlockState state) {
            super(ModBlockEntities.FLUID_PORT.get(), pos, state);
        }

        public static void tick(Level level, BlockPos pos, BlockState state,
                                FluidPortBlockEntity blockEntity) {
            if (!level.isClientSide) {
                blockEntity.serverTick(level, pos, state);
            }
        }
    }

    public static final class SuperheaterBlockEntity extends MachineBlockEntity {
        public SuperheaterBlockEntity(BlockPos pos, BlockState state) {
            super(ModBlockEntities.SUPERHEATER.get(), pos, state);
        }
    }

    public static final class SafetyValveBlockEntity extends MachineBlockEntity {
        public SafetyValveBlockEntity(BlockPos pos, BlockState state) {
            super(ModBlockEntities.SAFETY_VALVE.get(), pos, state);
        }
    }

    public static final class PressureGaugeBlockEntity extends MachineBlockEntity {
        public PressureGaugeBlockEntity(BlockPos pos, BlockState state) {
            super(ModBlockEntities.PRESSURE_GAUGE.get(), pos, state);
        }
    }

    public static final class SteamEngineBlockEntity extends MachineBlockEntity {
        public SteamEngineBlockEntity(BlockPos pos, BlockState state) {
            super(ModBlockEntities.STEAM_ENGINE.get(), pos, state);
        }
    }

    public static final class SteamTurbineBlockEntity extends MachineBlockEntity {
        public SteamTurbineBlockEntity(BlockPos pos, BlockState state) {
            super(ModBlockEntities.STEAM_TURBINE.get(), pos, state);
        }
    }

    public static final class SteamPipeBlockEntity extends MachineBlockEntity {
        public SteamPipeBlockEntity(BlockPos pos, BlockState state) {
            super(ModBlockEntities.STEAM_PIPE.get(), pos, state);
        }
    }
}
