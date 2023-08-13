package org.gr1m.mc.mup.bugfix.mc64836.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import org.gr1m.mc.mup.Mup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityMinecart.class)
public abstract class MixinEntityMinecart
{
    @Redirect(method = "moveAlongTrack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;isBeingRidden()Z"))
    private boolean disableMobControl(Entity entity)
    {
        if (Mup.config.mc64836.enabled && !(entity instanceof EntityPlayer))
        {
            return false;
        }

        return entity.isBeingRidden();
    }
}
