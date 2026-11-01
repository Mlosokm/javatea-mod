package com.javatea.registry;

import com.javatea.JavaTea;
import com.javatea.fluid.ModFluidType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, JavaTea.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, JavaTea.MOD_ID);

    public static final RegistryObject<FluidType> STEAM_TYPE = FLUID_TYPES.register("steam",
            () -> new ModFluidType(FluidType.Properties.create()
                    .descriptionId("fluid.javatea.steam")
                    .density(-500)
                    .viscosity(200)
                    .temperature(400)
                    .canConvertToSource(false)
                    .supportsBoating(false)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY),
                    new ResourceLocation(JavaTea.MOD_ID, "block/steam_still"),
                    new ResourceLocation(JavaTea.MOD_ID, "block/steam_flow")));

    public static final RegistryObject<FlowingFluid> STEAM_SOURCE = FLUIDS.register("steam",
            () -> new ForgeFlowingFluid.Source(steamProperties()));
    public static final RegistryObject<FlowingFluid> STEAM_FLOWING = FLUIDS.register("steam_flowing",
            () -> new ForgeFlowingFluid.Flowing(steamProperties()));

    private static ForgeFlowingFluid.Properties steamProperties() {
        return new ForgeFlowingFluid.Properties(STEAM_TYPE, STEAM_SOURCE, STEAM_FLOWING)
                .slopeFindDistance(2)
                .levelDecreasePerBlock(2);
    }

    public static final RegistryObject<FluidType> HP_STEAM_TYPE = FLUID_TYPES.register("hp_steam",
            () -> new ModFluidType(FluidType.Properties.create()
                    .descriptionId("fluid.javatea.hp_steam")
                    .density(-400)
                    .viscosity(150)
                    .temperature(600)
                    .canConvertToSource(false)
                    .supportsBoating(false)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY),
                    new ResourceLocation(JavaTea.MOD_ID, "block/hp_steam_still"),
                    new ResourceLocation(JavaTea.MOD_ID, "block/hp_steam_flow")));

    public static final RegistryObject<FlowingFluid> HP_STEAM_SOURCE = FLUIDS.register("hp_steam",
            () -> new ForgeFlowingFluid.Source(hpSteamProperties()));
    public static final RegistryObject<FlowingFluid> HP_STEAM_FLOWING = FLUIDS.register("hp_steam_flowing",
            () -> new ForgeFlowingFluid.Flowing(hpSteamProperties()));

    private static ForgeFlowingFluid.Properties hpSteamProperties() {
        return new ForgeFlowingFluid.Properties(HP_STEAM_TYPE, HP_STEAM_SOURCE, HP_STEAM_FLOWING)
                .slopeFindDistance(2)
                .levelDecreasePerBlock(2);
    }
}
