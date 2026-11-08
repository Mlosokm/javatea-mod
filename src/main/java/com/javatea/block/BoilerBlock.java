package com.javatea.block;

import com.javatea.blockentity.MachineBlockEntities;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class BoilerBlock extends MachineBlock {
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public BoilerBlock(BlockBehaviour.Properties properties) {
        super(properties, MachineBlockEntities.BoilerBlockEntity::new);
        registerDefaultState(defaultBlockState().setValue(LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        builder.add(LIT);
    }
}
