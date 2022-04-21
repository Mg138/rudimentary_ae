package io.github.mg138.rudimentary_ae.mixins;

import appeng.api.networking.IGrid;
import appeng.api.networking.IGridNode;
import appeng.me.pathfinding.PathingCalculation;
import io.github.mg138.rudimentary_ae.blocks.RudimentaryControllerEntity;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = PathingCalculation.class, remap = false)
public abstract class AddRudimentaryControllerCalcMixin {
    @Redirect(
            method = "<init>(Lappeng/api/networking/IGrid;)V",
            at = @At(value = "INVOKE", target = "Lappeng/api/networking/IGrid;getMachineNodes(Ljava/lang/Class;)Ljava/lang/Iterable;")
    )
    public Iterable<IGridNode> rudimentary_ae_getControllers(@NotNull IGrid instance, Class<?> clazz) {
        List<IGridNode> list = new ArrayList<>();
        instance.getMachineNodes(clazz).forEach(list::add);
        instance.getMachineNodes(RudimentaryControllerEntity.class).forEach(list::add);

        return list;
    }
}
