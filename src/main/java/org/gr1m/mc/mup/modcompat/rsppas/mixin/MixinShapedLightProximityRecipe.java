package org.gr1m.mc.mup.modcompat.rsppas.mixin;

import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.math.BlockPos;
import org.gr1m.mc.mup.modcompat.rsppas.IContainerCraftingTableFix;
import org.gr1m.mc.mup.modcompat.rsppas.IContainerWorkbench;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Pseudo
@Mixin(targets = "hellfirepvp.astralsorcery.common.crafting.ShapedLightProximityRecipe")
public abstract class MixinShapedLightProximityRecipe implements IRecipe
{
    @Redirect(method = "matches(Lnet/minecraft/inventory/InventoryCrafting;Lnet/minecraft/world/World;)Z",
            at = @At(value = "FIELD", target = "Lnet/minecraft/inventory/ContainerWorkbench;pos:Lnet/minecraft/util/math/BlockPos;", ordinal = 0))
    private BlockPos getTSPos(ContainerWorkbench workbench)
    {
        BlockPos pos = BlockPos.ORIGIN;

        if (workbench instanceof IContainerCraftingTableFix)
        {
            pos = ((IContainerCraftingTableFix) workbench).getTEPos();
        }
        else if (workbench instanceof IContainerWorkbench)
        {
            pos = ((IContainerWorkbench) workbench).getPos();
        }

        return pos;
    }
}