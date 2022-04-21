package io.github.mg138.rudimentary_ae.mixins;

import appeng.me.service.PathingService;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PathingService.class, remap = false)
public abstract class DisablePowerUsageMixin2 {
    @Inject(
            method = "setChannelPowerUsage(D)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void rudimentary_ae_setChannelPowerUsage(double channelPowerUsage, CallbackInfo ci) {
        ci.cancel();
    }
}
