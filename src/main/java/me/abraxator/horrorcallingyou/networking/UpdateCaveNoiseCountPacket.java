package me.abraxator.horrorcallingyou.networking;

import me.abraxator.horrorcallingyou.calling.CallingYouProcess;
import me.abraxator.horrorcallingyou.init.ModCapabilities;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdateCaveNoiseCountPacket {
    public UpdateCaveNoiseCountPacket() {}

    public UpdateCaveNoiseCountPacket(FriendlyByteBuf buf) {}

    public void encode(FriendlyByteBuf buf) {}

    public static class Handler {
        public static boolean onMessage(UpdateCaveNoiseCountPacket packet, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                Player player = ctx.get().getSender();
                player.getCapability(ModCapabilities.PHONE).ifPresent(phoneCapHandler -> {
                    CallingYouProcess callingYouProcess = phoneCapHandler.callingYouProcess;
                    if(callingYouProcess.canChangeToNext()) {
                        phoneCapHandler.callingYouProcess = CallingYouProcess.NEXT_BY_PROCESS.get(callingYouProcess);
                        callingYouProcess = phoneCapHandler.callingYouProcess;
                        callingYouProcess.onChangeToThis(player, player.level());
                    }
                });
            });

            ctx.get().setPacketHandled(true);
            return true;
        }
    }
}
