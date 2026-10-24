package dev.noblanklinks;

import com.simibubi.create.content.redstone.link.RedstoneLinkNetworkHandler.Frequency;
import net.createmod.catnip.data.Couple;
import net.neoforged.fml.common.Mod;

@Mod(NoBlankLinks.MOD_ID)
public class NoBlankLinks {
    public static final String MOD_ID = "noblanklinks";

    public static boolean isBlank(Couple<Frequency> key) {
        return key != null && key.getFirst().getStack().isEmpty() && key.getSecond().getStack().isEmpty();
    }
}
