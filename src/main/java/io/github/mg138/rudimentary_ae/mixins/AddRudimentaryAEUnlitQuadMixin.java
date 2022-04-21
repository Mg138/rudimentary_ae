package io.github.mg138.rudimentary_ae.mixins;

import appeng.hooks.UnlitQuadHooks;
import io.github.mg138.rudimentary_ae.RudimentaryAE;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = UnlitQuadHooks.class, remap = false)
public abstract class AddRudimentaryAEUnlitQuadMixin {
    @Shadow @Final private static ThreadLocal<Boolean> ENABLE_UNLIT_EXTENSIONS;

    @Inject(
            method = "beginDeserializingModel(Lnet/minecraft/util/Identifier;)V",
            at = @At("HEAD"),
            remap = true
    )
    private static void rudimentary_ae_beginDeserializingModel(@NotNull Identifier location, CallbackInfo ci) {
        String rudimentary_ae_namespace = location.getNamespace();
        if (rudimentary_ae_namespace.equals(RudimentaryAE.MOD_ID)) {
            ENABLE_UNLIT_EXTENSIONS.set(true);
        }
    }
}
