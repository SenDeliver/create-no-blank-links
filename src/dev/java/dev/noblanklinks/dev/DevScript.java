package dev.noblanklinks.dev;

import dev.noblanklinks.NoBlankLinks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;
import net.minecraft.client.gui.screens.PauseScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Deque;

@EventBusSubscriber(modid = NoBlankLinks.MOD_ID, value = Dist.CLIENT)
public final class DevScript {
    private static final Logger LOG = LoggerFactory.getLogger("noblanklinks-script");
    private static Deque<String> lines;
    private static int waitTicks;

    @SubscribeEvent
    static void onTick(ClientTickEvent.Post event) {
        String file = System.getProperty("noblanklinks.script");
        if (file == null) return;
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) return;
        if (mc.screen instanceof PauseScreen) mc.setScreen(null);
        if (lines == null) {
            try {
                lines = new ArrayDeque<>(Files.readAllLines(Path.of(file)));
            } catch (IOException e) {
                LOG.error("cannot read script {}", file, e);
                lines = new ArrayDeque<>();
            }
            waitTicks = 60;
            LOG.info("script start");
        }
        if (waitTicks > 0) {
            waitTicks--;
            return;
        }
        while (!lines.isEmpty()) {
            String line = lines.poll().trim();
            if (line.isEmpty() || line.startsWith("#")) continue;
            LOG.info("script: {}", line);
            if (line.startsWith("wait ")) {
                waitTicks = Integer.parseInt(line.substring(5).trim());
                return;
            } else if (line.startsWith("cmd ")) {
                String command = line.substring(4).trim();
                mc.player.connection.sendCommand(command.startsWith("/") ? command.substring(1) : command);
            } else if (line.startsWith("shot ")) {
                String name = line.substring(5).trim() + ".png";
                mc.gui.getChat().clearMessages(true);
                Screenshot.grab(mc.gameDirectory, name, mc.getMainRenderTarget(), msg -> LOG.info("screenshot {}: {}", name, msg.getString()));
                waitTicks = 2;
                return;
            } else if (line.equals("hud off")) {
                mc.options.hideGui = true;
            } else if (line.equals("quit")) {
                mc.stop();
                return;
            }
        }
    }
}
