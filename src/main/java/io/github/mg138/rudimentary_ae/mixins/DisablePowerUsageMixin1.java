package io.github.mg138.rudimentary_ae.mixins;

import appeng.api.networking.IGridNodeListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = {"appeng/me/ManagedGridNode$InitData"}, remap = false)
public abstract class DisablePowerUsageMixin1 {
    @Shadow private double idlePowerUsage;

    @Inject(method = "<init>(Ljava/lang/Object;Lappeng/api/networking/IGridNodeListener;)V",
            at = @At("TAIL")
    )
    private void rudimentary_ae_InitData_constructor(Object logicalHost, IGridNodeListener<?> listener, CallbackInfo ci) {
        idlePowerUsage = 0.0;
    }
}
