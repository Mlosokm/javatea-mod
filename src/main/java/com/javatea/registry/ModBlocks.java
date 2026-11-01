package com.javatea.registry;

import com.javatea.JavaTea;
import com.javatea.block.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, JavaTea.MOD_ID);

    public static final RegistryObject<Block> CAST_IRON_BLOCK = BLOCKS.register("cast_iron_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .mapColor(MapColor.COLOR_GRAY)
                    .strength(5.0f, 6.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    public static final RegistryObject<Block> FLUID_PORT = BLOCKS.register("fluid_port",
            () -> new FluidPortBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(3.0f)
                    .sound(SoundType.METAL)
                    .noOcclusion()));

    public static final RegistryObject<Block> STEAM_TANK = BLOCKS.register("steam_tank",
            () -> new SteamTankBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(4.0f)
                    .sound(SoundType.METAL)
                    .noOcclusion()));

    public static final RegistryObject<Block> STEAM_PIPE = BLOCKS.register("steam_pipe",
            () -> new SteamPipeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(2.0f)
                    .sound(SoundType.METAL)
                    .noOcclusion()));

    public static final RegistryObject<Block> BOILER = BLOCKS.register("boiler",
            () -> new BoilerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(5.0f)
                    .sound(SoundType.METAL)
                    .lightLevel(state -> state.getValue(BoilerBlock.LIT) ? 8 : 0)));

    public static final RegistryObject<Block> SUPERHEATER = BLOCKS.register("superheater",
            () -> new SuperheaterBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(5.0f)
                    .sound(SoundType.METAL)
                    .lightLevel(state -> state.getValue(SuperheaterBlock.LIT) ? 10 : 0)));

    public static final RegistryObject<Block> SAFETY_VALVE = BLOCKS.register("safety_valve",
            () -> new SafetyValveBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(3.0f)
                    .sound(SoundType.METAL)));

    public static final RegistryObject<Block> PRESSURE_GAUGE = BLOCKS.register("pressure_gauge",
            () -> new PressureGaugeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(2.0f)
                    .sound(SoundType.METAL)
                    .noOcclusion()));

    public static final RegistryObject<Block> STEAM_ENGINE = BLOCKS.register("steam_engine",
            () -> new SteamEngineBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(5.0f)
                    .sound(SoundType.METAL)));

    public static final RegistryObject<Block> STEAM_TURBINE = BLOCKS.register("steam_turbine",
            () -> new SteamTurbineBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(5.0f)
                    .sound(SoundType.METAL)));
}
