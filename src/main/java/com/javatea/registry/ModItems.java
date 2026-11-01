package com.javatea.registry;

import com.javatea.JavaTea;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, JavaTea.MOD_ID);

    public static final RegistryObject<Item> CAST_IRON_INGOT = ITEMS.register("cast_iron_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAST_IRON_NUGGET = ITEMS.register("cast_iron_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PRESSURE_PLATE = ITEMS.register("pressure_plate",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAST_IRON_BLOCK_ITEM = ITEMS.register("cast_iron_block",
            () -> new BlockItem(ModBlocks.CAST_IRON_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> FLUID_PORT_ITEM = ITEMS.register("fluid_port",
            () -> new BlockItem(ModBlocks.FLUID_PORT.get(), new Item.Properties()));

    public static final RegistryObject<Item> STEAM_TANK_ITEM = ITEMS.register("steam_tank",
            () -> new BlockItem(ModBlocks.STEAM_TANK.get(), new Item.Properties()));

    public static final RegistryObject<Item> STEAM_PIPE_ITEM = ITEMS.register("steam_pipe",
            () -> new BlockItem(ModBlocks.STEAM_PIPE.get(), new Item.Properties()));

    public static final RegistryObject<Item> BOILER_ITEM = ITEMS.register("boiler",
            () -> new BlockItem(ModBlocks.BOILER.get(), new Item.Properties()));

    public static final RegistryObject<Item> SUPERHEATER_ITEM = ITEMS.register("superheater",
            () -> new BlockItem(ModBlocks.SUPERHEATER.get(), new Item.Properties()));

    public static final RegistryObject<Item> SAFETY_VALVE_ITEM = ITEMS.register("safety_valve",
            () -> new BlockItem(ModBlocks.SAFETY_VALVE.get(), new Item.Properties()));

    public static final RegistryObject<Item> PRESSURE_GAUGE_ITEM = ITEMS.register("pressure_gauge",
            () -> new BlockItem(ModBlocks.PRESSURE_GAUGE.get(), new Item.Properties()));

    public static final RegistryObject<Item> STEAM_ENGINE_ITEM = ITEMS.register("steam_engine",
            () -> new BlockItem(ModBlocks.STEAM_ENGINE.get(), new Item.Properties()));

    public static final RegistryObject<Item> STEAM_TURBINE_ITEM = ITEMS.register("steam_turbine",
            () -> new BlockItem(ModBlocks.STEAM_TURBINE.get(), new Item.Properties()));

    public static final RegistryObject<Item> IRON_BOILER_UPGRADE = ITEMS.register("iron_boiler_upgrade",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CAST_IRON_BOILER_UPGRADE = ITEMS.register("cast_iron_boiler_upgrade",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_BOILER_UPGRADE = ITEMS.register("netherite_boiler_upgrade",
            () -> new Item(new Item.Properties()));
}
