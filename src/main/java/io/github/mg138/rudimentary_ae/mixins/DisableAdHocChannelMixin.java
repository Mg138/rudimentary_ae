package io.github.mg138.rudimentary_ae.mixins;

import appeng.api.networking.pathing.ChannelMode;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ChannelMode.class, remap = false)
public abstract class DisableAdHocChannelMixin {
    @Inject(
            method = "getAdHocNetworkChannels()I",
            at = @At("HEAD"),
            cancellable = true
    )
    public void rudimentary_ae_getAdHocNetworkChannels(@NotNull CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(0);
    }
}
