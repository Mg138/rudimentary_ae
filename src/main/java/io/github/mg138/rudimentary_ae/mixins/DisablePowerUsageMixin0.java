package io.github.mg138.rudimentary_ae.mixins;

import appeng.api.networking.IManagedGridNode;
import appeng.parts.reporting.AbstractReportingPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = AbstractReportingPart.class, remap = false)
public abstract class DisablePowerUsageMixin0 {
    @Redirect(
            method = "<init>(Lappeng/api/parts/IPartItem;Z)V",
            at = @At(value = "INVOKE", target = "Lappeng/api/networking/IManagedGridNode;setIdlePowerUsage(D)Lappeng/api/networking/IManagedGridNode;")
    )
    private IManagedGridNode rudimentary_ae_setIdlePowerUsage(IManagedGridNode instance, double v) {
        return instance;
    }
}

