package me.abraxator.horrorcallingyou.networking;

import me.abraxator.horrorcallingyou.calling.CallingYouProcess;
import me.abraxator.horrorcallingyou.init.ModCapabilities;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CaveNoisePacket {
    public CaveNoisePacket() {}

    public CaveNoisePacket(FriendlyByteBuf buf) {}

    public void encode(FriendlyByteBuf buf) {}

    public static class Handler {
        public static boolean onMessage(CaveNoisePacket packet, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                ServerPlayer player = ctx.get().getSender();
                if (player == null) return;
                player.getCapability(ModCapabilities.PHONE).ifPresent(phoneCapHandler -> {
                    CallingYouProcess callingYouProcess = phoneCapHandler.callingYouProcess;
                    callingYouProcess.caveNoiseTimes++;
                    phoneCapHandler.callingYouProcess = CallingYouProcess.updateToNextProcess(player, player.serverLevel(), callingYouProcess);
                });
            });

            ctx.get().setPacketHandled(true);
            return true;
        }
    }
}
