package com.javatea.fluid;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModFluidType extends FluidType {
    private final ResourceLocation still;
    private final ResourceLocation flowing;

    public ModFluidType(Properties properties, ResourceLocation still, ResourceLocation flowing) {
        super(properties);
        this.still = still;
        this.flowing = flowing;
    }

    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            @Override
            public @NotNull ResourceLocation getStillTexture() {
                return still;
            }

            @Override
            public @NotNull ResourceLocation getFlowingTexture() {
                return flowing;
            }
        });
    }
}
