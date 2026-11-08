package com.javatea.registry;

import com.javatea.JavaTea;
import com.javatea.blockentity.MachineBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, JavaTea.MOD_ID);

    public static final RegistryObject<BlockEntityType<MachineBlockEntities.BoilerBlockEntity>> BOILER =
            BLOCK_ENTITIES.register("boiler",
                    () -> BlockEntityType.Builder.of(MachineBlockEntities.BoilerBlockEntity::new,
                            ModBlocks.BOILER.get()).build(null));

    public static final RegistryObject<BlockEntityType<MachineBlockEntities.SteamTankBlockEntity>> STEAM_TANK =
            BLOCK_ENTITIES.register("steam_tank",
                    () -> BlockEntityType.Builder.of(MachineBlockEntities.SteamTankBlockEntity::new,
                            ModBlocks.STEAM_TANK.get()).build(null));

    public static final RegistryObject<BlockEntityType<MachineBlockEntities.FluidPortBlockEntity>> FLUID_PORT =
            BLOCK_ENTITIES.register("fluid_port",
                    () -> BlockEntityType.Builder.of(MachineBlockEntities.FluidPortBlockEntity::new,
                            ModBlocks.FLUID_PORT.get()).build(null));

    public static final RegistryObject<BlockEntityType<MachineBlockEntities.SuperheaterBlockEntity>> SUPERHEATER =
            BLOCK_ENTITIES.register("superheater",
                    () -> BlockEntityType.Builder.of(MachineBlockEntities.SuperheaterBlockEntity::new,
                            ModBlocks.SUPERHEATER.get()).build(null));

    public static final RegistryObject<BlockEntityType<MachineBlockEntities.SafetyValveBlockEntity>> SAFETY_VALVE =
            BLOCK_ENTITIES.register("safety_valve",
                    () -> BlockEntityType.Builder.of(MachineBlockEntities.SafetyValveBlockEntity::new,
                            ModBlocks.SAFETY_VALVE.get()).build(null));

    public static final RegistryObject<BlockEntityType<MachineBlockEntities.PressureGaugeBlockEntity>> PRESSURE_GAUGE =
            BLOCK_ENTITIES.register("pressure_gauge",
                    () -> BlockEntityType.Builder.of(MachineBlockEntities.PressureGaugeBlockEntity::new,
                            ModBlocks.PRESSURE_GAUGE.get()).build(null));

    public static final RegistryObject<BlockEntityType<MachineBlockEntities.SteamEngineBlockEntity>> STEAM_ENGINE =
            BLOCK_ENTITIES.register("steam_engine",
                    () -> BlockEntityType.Builder.of(MachineBlockEntities.SteamEngineBlockEntity::new,
                            ModBlocks.STEAM_ENGINE.get()).build(null));

    public static final RegistryObject<BlockEntityType<MachineBlockEntities.SteamTurbineBlockEntity>> STEAM_TURBINE =
            BLOCK_ENTITIES.register("steam_turbine",
                    () -> BlockEntityType.Builder.of(MachineBlockEntities.SteamTurbineBlockEntity::new,
                            ModBlocks.STEAM_TURBINE.get()).build(null));

    public static final RegistryObject<BlockEntityType<MachineBlockEntities.SteamPipeBlockEntity>> STEAM_PIPE =
            BLOCK_ENTITIES.register("steam_pipe",
                    () -> BlockEntityType.Builder.of(MachineBlockEntities.SteamPipeBlockEntity::new,
                            ModBlocks.STEAM_PIPE.get()).build(null));
}
