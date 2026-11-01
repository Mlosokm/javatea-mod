package com.javatea.registry;

import com.javatea.JavaTea;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, JavaTea.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MAIN = CREATIVE_TABS.register("main",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.javatea"))
                    .icon(() -> new ItemStack(ModItems.CAST_IRON_INGOT.get()))
                    .displayItems((params, output) -> {
                        output.accept(ModItems.CAST_IRON_NUGGET.get());
                        output.accept(ModItems.CAST_IRON_INGOT.get());
                        output.accept(ModItems.CAST_IRON_BLOCK_ITEM.get());
                        output.accept(ModItems.PRESSURE_PLATE.get());
                        output.accept(ModItems.FLUID_PORT_ITEM.get());
                        output.accept(ModItems.STEAM_TANK_ITEM.get());
                        output.accept(ModItems.STEAM_PIPE_ITEM.get());
                        output.accept(ModItems.BOILER_ITEM.get());
                        output.accept(ModItems.SUPERHEATER_ITEM.get());
                        output.accept(ModItems.SAFETY_VALVE_ITEM.get());
                        output.accept(ModItems.PRESSURE_GAUGE_ITEM.get());
                        output.accept(ModItems.STEAM_ENGINE_ITEM.get());
                        output.accept(ModItems.STEAM_TURBINE_ITEM.get());
                        output.accept(ModItems.IRON_BOILER_UPGRADE.get());
                        output.accept(ModItems.CAST_IRON_BOILER_UPGRADE.get());
                        output.accept(ModItems.NETHERITE_BOILER_UPGRADE.get());
                    })
                    .build());
}
