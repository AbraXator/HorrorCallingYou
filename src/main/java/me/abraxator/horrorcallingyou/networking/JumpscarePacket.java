package me.abraxator.horrorcallingyou.networking;

import me.abraxator.horrorcallingyou.HorrorCallingYou;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class JumpscarePacket {
    public JumpscarePacket() {
    }

    public JumpscarePacket(FriendlyByteBuf buf) {

    }

    public void encode(FriendlyByteBuf buf) {}

    public static class Handler {
        public static boolean onMessage(JumpscarePacket packet, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                Minecraft.getInstance().screen = new JumpscareScreen(HorrorCallingYou.path("video.mp4"));
            });

            ctx.get().setPacketHandled(true);
            return true;
        }
    }
}
