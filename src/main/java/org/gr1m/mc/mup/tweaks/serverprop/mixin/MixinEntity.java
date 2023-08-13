package org.gr1m.mc.mup.tweaks.serverprop.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.gr1m.mc.mup.Mup;
import org.gr1m.mc.mup.tweaks.serverprop.config.ServerPropCustomConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class MixinEntity
{
    private double overriddenBound;

    @Inject(method = "move", at = @At("HEAD"))
    private void captureOverriddenBound(double x, double y, double z, CallbackInfoReturnable<Boolean> ci)
    {
        if (Mup.config.serverprop.enabled)
        {
            overriddenBound = ((ServerPropCustomConfig) Mup.config.serverprop.customConfig).pistonEntityPushLimit;
        }
    }

    @ModifyVariable(method = "move", ordinal = 0, at = @At(value = "INVOKE_ASSIGN",
            target = "Lnet/minecraft/util/math/MathHelper;clamp(DDD)D"))
    private double pistonEntityMoveClamp(double delta)
    {
        if (overriddenBound != 0.0D)
        {
            return MathHelper.clamp(delta, 0 - overriddenBound, overriddenBound);
        }

        return delta;
    }
}