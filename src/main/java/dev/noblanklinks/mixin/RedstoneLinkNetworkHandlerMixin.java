package dev.noblanklinks.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.simibubi.create.content.redstone.link.IRedstoneLinkable;
import com.simibubi.create.content.redstone.link.RedstoneLinkNetworkHandler;
import com.simibubi.create.content.redstone.link.RedstoneLinkNetworkHandler.Frequency;
import dev.noblanklinks.NoBlankLinks;
import net.createmod.catnip.data.Couple;
import net.minecraft.world.level.LevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RedstoneLinkNetworkHandler.class)
public class RedstoneLinkNetworkHandlerMixin {

    @ModifyExpressionValue(method = "updateNetworkOf", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/content/redstone/link/IRedstoneLinkable;getTransmittedStrength()I"))
    private int noblanklinks$blankCarriesNoPower(int strength, LevelAccessor world, IRedstoneLinkable actor) {
        return NoBlankLinks.isBlank(actor.getNetworkKey()) ? 0 : strength;
    }

    @Inject(method = "hasAnyLoadedPower", at = @At("HEAD"), cancellable = true)
    private void noblanklinks$blankHasNoPower(Couple<Frequency> frequency, CallbackInfoReturnable<Boolean> cir) {
        if (NoBlankLinks.isBlank(frequency)) cir.setReturnValue(false);
    }
}
