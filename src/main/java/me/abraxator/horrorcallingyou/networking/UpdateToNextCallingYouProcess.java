package me.abraxator.horrorcallingyou.networking;

import me.abraxator.horrorcallingyou.init.ModCapabilities;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdateToNextCallingYouProcess {
    public UpdateToNextCallingYouProcess() {}

    public UpdateToNextCallingYouProcess(FriendlyByteBuf buf) {}

    public void encode(FriendlyByteBuf buf) {}

    public static class Handler {
        public static boolean onMessage(UpdateToNextCallingYouProcess packet, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                ServerPlayer player = ctx.get().getSender();
                if (player == null) return;
                player.getCapability(ModCapabilities.PHONE).ifPresent(phoneCapHandler -> {
                    phoneCapHandler.callingYouProcess.onUpdateToNextCallingYouProcess(player, player.serverLevel(), phoneCapHandler, phoneCapHandler.callingYouProcess);
                });
            });

            ctx.get().setPacketHandled(true);
            return true;
        }
    }
}
