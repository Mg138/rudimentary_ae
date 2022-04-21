package io.github.mg138.rudimentary_ae.blocks

import appeng.api.config.Actionable
import appeng.api.networking.GridFlags
import appeng.api.networking.events.GridPowerStorageStateChanged.PowerEventType
import appeng.api.util.AECableType
import appeng.blockentity.networking.ControllerBlockEntity
import io.github.mg138.rudimentary_ae.RudimentaryAE.RUDIMENTARY_CONTROLLER_ENTITY
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction

class RudimentaryControllerEntity(pos: BlockPos, blockState: BlockState) :
    ControllerBlockEntity(RUDIMENTARY_CONTROLLER_ENTITY, pos, blockState) {
    init {
        internalMaxPower = 0.0
        setInternalPublicPowerStorage(false)
        mainNode.setIdlePowerUsage(0.0)
        mainNode.setFlags(GridFlags.CANNOT_CARRY)
    }


    override fun getFunnelPowerDemand(maxReceived: Double): Double {
        return 0.0
    }

    override fun funnelPowerIntoStorage(power: Double, mode: Actionable?): Double {
        return 0.0
    }

    override fun PowerEvent(x: PowerEventType?) {
    }

    override fun getCableConnectionType(dir: Direction) =
        AECableType.SMART
}
