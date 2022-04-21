package io.github.mg138.rudimentary_ae.mixins;

import appeng.api.config.Actionable;
import appeng.api.config.PowerMultiplier;
import appeng.me.service.EnergyService;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EnergyService.class, remap = false)
public abstract class DisablePowerUsageMixin3 {
    @Inject(
            method = "extractAEPower(DLappeng/api/config/Actionable;Lappeng/api/config/PowerMultiplier;)D",
            at = @At("HEAD"),
            cancellable = true
    )
    private void rudimentary_ae_extractAEPower(double amt, Actionable mode, PowerMultiplier pm, CallbackInfoReturnable<Double> cir) {
        if (mode == Actionable.SIMULATE) {
            cir.setReturnValue(amt);
        }
    }
}
