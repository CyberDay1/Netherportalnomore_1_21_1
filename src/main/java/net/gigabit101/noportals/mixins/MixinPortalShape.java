package net.gigabit101.noportals.mixins;

import net.minecraft.world.level.portal.PortalShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PortalShape.class)
public class MixinPortalShape
{
    @Inject(at = @At("RETURN"), method = "isValid", cancellable = true)
    public void isValid(CallbackInfoReturnable<Boolean> cir)
    {
        cir.setReturnValue(false);
    }
}
