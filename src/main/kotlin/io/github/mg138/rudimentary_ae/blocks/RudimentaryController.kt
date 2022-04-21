package io.github.mg138.rudimentary_ae.blocks

import appeng.block.AEBaseEntityBlock
import appeng.block.networking.ControllerBlock.*
import io.github.mg138.rudimentary_ae.RudimentaryAE.RUDIMENTARY_CONTROLLER_ENTITY
import io.github.mg138.rudimentary_ae.RudimentaryAE.registerBlockWithItem
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import kotlin.math.abs

object RudimentaryController : AEBaseEntityBlock<RudimentaryControllerEntity>(
    defaultProps(Material.METAL).strength(4.0F)
) {
    fun register() {
        registerBlockWithItem("rudimentary_controller", this)
        this.setBlockEntity(
            RudimentaryControllerEntity::class.java,
            RUDIMENTARY_CONTROLLER_ENTITY,
            null,
            null
        )
    }

    init {
        defaultState = defaultState
            .with(CONTROLLER_STATE, ControllerBlockState.offline)
            .with(CONTROLLER_TYPE, ControllerRenderType.block)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(CONTROLLER_STATE)
        builder.add(CONTROLLER_TYPE)
    }

    override fun getPlacementState(context: ItemPlacementContext) =
        getControllerType(defaultState, context.world, context.blockPos)

    @Deprecated("Deprecated in Java")
    override fun getStateForNeighborUpdate(
        state: BlockState, facing: Direction, facingState: BlockState, level: WorldAccess,
        pos: BlockPos, facingPos: BlockPos
    ) = getControllerType(state, level, pos)

    private fun getControllerType(baseState: BlockState, level: WorldAccess, pos: BlockPos): BlockState {
        var type = ControllerRenderType.block
        val x = pos.x
        val y = pos.y
        val z = pos.z

        val xx = (this.getBlockEntity(level, x - 1, y, z) != null && this.getBlockEntity(level, x + 1, y, z) != null)
        val yy = (this.getBlockEntity(level, x, y - 1, z) != null && this.getBlockEntity(level, x, y + 1, z) != null)
        val zz = (this.getBlockEntity(level, x, y, z - 1) != null && this.getBlockEntity(level, x, y, z + 1) != null)

        if (xx && !yy && !zz) {
            type = ControllerRenderType.column_x
        } else if (!xx && yy && !zz) {
            type = ControllerRenderType.column_y
        } else if (!xx && !yy && zz) {
            type = ControllerRenderType.column_z
        } else if ((if (xx) 1 else 0) + (if (yy) 1 else 0) + (if (zz) 1 else 0) >= 2) {
            val v = (abs(x) + abs(y) + abs(z)) % 2

            type = if (v == 0) {
                ControllerRenderType.inside_a
            } else {
                ControllerRenderType.inside_b
            }
        }
        return baseState.with(CONTROLLER_TYPE, type)
    }

    @Deprecated("Deprecated in Java", ReplaceWith("this.getBlockEntity(level, pos)?.updateState()"))
    override fun neighborUpdate(
        state: BlockState, level: World, pos: BlockPos, blockIn: Block, fromPos: BlockPos,
        isMoving: Boolean
    ) {
        this.getBlockEntity(level, pos)?.updateState()
    }
}
