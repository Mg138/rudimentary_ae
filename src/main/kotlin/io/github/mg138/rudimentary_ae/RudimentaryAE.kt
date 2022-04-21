package io.github.mg138.rudimentary_ae

import appeng.blockentity.AEBaseBlockEntity
import io.github.mg138.rudimentary_ae.blocks.RudimentaryController
import io.github.mg138.rudimentary_ae.blocks.RudimentaryControllerEntity
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.client.render.RenderLayer
import net.minecraft.item.BlockItem
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import org.apache.logging.log4j.LogManager

@Suppress("UNUSED")
object RudimentaryAE : ModInitializer {
    const val MOD_ID = "rudimentary_ae"
    private val LOGGER = LogManager.getLogger(MOD_ID)

    private val ITEM_GROUP = FabricItemGroupBuilder.build(id("rudimentary_ae")) {
        RudimentaryController.asItem().defaultStack
    }

    fun registerBlockWithItem(path: String, block: Block) {
        Registry.register(Registry.BLOCK, id(path), block)
        Registry.register(Registry.ITEM, id(path), BlockItem(block, FabricItemSettings().group(ITEM_GROUP)))
    }

    val RUDIMENTARY_CONTROLLER_ENTITY: BlockEntityType<RudimentaryControllerEntity> = Registry.register(
        Registry.BLOCK_ENTITY_TYPE,
        id("rudimentary_controller"),
        FabricBlockEntityTypeBuilder.create(::RudimentaryControllerEntity, RudimentaryController).build()
    )

    fun id(s: String) = Identifier(MOD_ID, s)

    override fun onInitialize() {
        RudimentaryController.register()

        AEBaseBlockEntity.registerBlockEntityItem(RUDIMENTARY_CONTROLLER_ENTITY, RudimentaryController.asItem())

        BlockRenderLayerMap.INSTANCE.putBlock(RudimentaryController, RenderLayer.getCutout())

        LOGGER.info("Rudimentary AE loaded.")
    }
}