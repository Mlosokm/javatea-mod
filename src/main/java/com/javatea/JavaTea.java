package com.javatea;

import com.javatea.registry.ModBlockEntities;
import com.javatea.registry.ModBlocks;
import com.javatea.registry.ModCreativeTabs;
import com.javatea.registry.ModFluids;
import com.javatea.registry.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(JavaTea.MOD_ID)
public class JavaTea {
    public static final String MOD_ID = "javatea";
    public static final Logger LOGGER = LogUtils.getLogger();

    public JavaTea() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModFluids.FLUID_TYPES.register(modBus);
        ModFluids.FLUIDS.register(modBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modBus);
        ModCreativeTabs.CREATIVE_TABS.register(modBus);

        MinecraftForge.EVENT_BUS.register(this);

        LOGGER.info("JavaTea loaded");
    }
}
