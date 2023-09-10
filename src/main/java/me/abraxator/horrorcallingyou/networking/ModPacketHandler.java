package me.abraxator.horrorcallingyou.networking;

import me.abraxator.horrorcallingyou.HorrorCallingYou;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModPacketHandler {
    private static final String VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            HorrorCallingYou.path("channel"),
            () -> VERSION,
            VERSION::equals,
            VERSION::equals
    );

    public static void init() {
        int id = 0;
        CHANNEL.registerMessage(id++, UpdateToNextCallingYouProcess.class, UpdateToNextCallingYouProcess::encode, UpdateToNextCallingYouProcess::new, UpdateToNextCallingYouProcess.Handler::onMessage);
    }
}
