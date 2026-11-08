package com.javatea.block;

import com.javatea.blockentity.MachineBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

/**
 * Common block-entity host for the registered steam-machine blocks.
 */
public class MachineBlock extends BaseEntityBlock {
    private final BiFunction<BlockPos, BlockState, ? extends BlockEntity> entityFactory;

    public MachineBlock(BlockBehaviour.Properties properties,
                        BiFunction<BlockPos, BlockState, ? extends BlockEntity> entityFactory) {
        super(properties);
        this.entityFactory = entityFactory;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return entityFactory.apply(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide) {
            return null;
        }
        return (world, pos, blockState, blockEntity) -> {
            if (blockEntity instanceof MachineBlockEntities.MachineBlockEntity machine) {
                machine.serverTick(world, pos, blockState);
            }
        };
    }
}
