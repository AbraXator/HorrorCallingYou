package me.abraxator.horrorcallingyou.networking;

import me.abraxator.horrorcallingyou.init.ModCapabilities;
import me.abraxator.horrorcallingyou.init.ModItems;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
                    phoneCapHandler.addPlayedCaveNoise();
                    if(phoneCapHandler.getPlayedCaveNoise() >= 2) {
                        if(player.getInventory().contains(ModItems.PHONE.get().getDefaultInstance())) {
                            phoneCapHandler.setPlayedCaveNoise(1);
                        } else {
                            phoneCapHandler.resetPlayedCaveNoise();
                            player.addItem(new ItemStack(ModItems.PHONE.get()));
                        }
                    }
                });
            });

            ctx.get().setPacketHandled(true);
            return true;
        }
    }
}
