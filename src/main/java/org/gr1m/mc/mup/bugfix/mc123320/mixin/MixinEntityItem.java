package org.gr1m.mc.mup.bugfix.mc123320.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.world.World;
import org.gr1m.mc.mup.Mup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityItem.class)
public abstract class MixinEntityItem extends Entity {

    public MixinEntityItem(World worldIn) {
        super(worldIn);
    }

    @Redirect(method = "onUpdate", at = @At(value = "FIELD", target = "Lnet/minecraft/world/World;isRemote:Z"),
            slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/item/EntityItem;hasNoGravity()Z"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/entity/item/EntityItem;pushOutOfBlocks(DDD)Z")))
    private boolean clientPushOutOfBlocksRedirect(World world) {
        return !(Mup.config.mc123320.enabled && Mup.config.mc4.enabled) && world.isRemote;
    }

    @ModifyVariable(method = "onUpdate", name = "d3", ordinal = 0, at = @At(value = "STORE"))
    private double cancelPrevMotionX(double x, CallbackInfoReturnable<Boolean> cir) {
        return (Mup.config.mc123320.enabled && !Mup.config.mc4.enabled) ? this.motionX : x;
    }

    @ModifyVariable(method = "onUpdate", name = "d4", ordinal = 0, at = @At(value = "STORE"))
    private double cancelPrevMotionY(double y, CallbackInfoReturnable<Boolean> cir) {
        return (Mup.config.mc123320.enabled && !Mup.config.mc4.enabled) ? this.motionY : y;
    }

    @ModifyVariable(method = "onUpdate", name = "d5", ordinal = 0, at = @At(value = "STORE"))
    private double cancelPrevMotionZ(double z, CallbackInfoReturnable<Boolean> cir) {
        return (Mup.config.mc123320.enabled && !Mup.config.mc4.enabled) ? this.motionZ : z;
    }
}
