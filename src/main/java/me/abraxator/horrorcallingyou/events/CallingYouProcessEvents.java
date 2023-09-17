package me.abraxator.horrorcallingyou.events;

import me.abraxator.horrorcallingyou.HorrorCallingYou;
import me.abraxator.horrorcallingyou.init.ModCapabilities;
import me.abraxator.horrorcallingyou.networking.CaveNoisePacket;
import me.abraxator.horrorcallingyou.networking.ModPacketHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class CallingYouProcessEvents {
    @Mod.EventBusSubscriber(modid = HorrorCallingYou.MOD_ID)
    public static class ModEvents {
    }

    @Mod.EventBusSubscriber(modid = HorrorCallingYou.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void onItemPickup(PlayerEvent.ItemPickupEvent itemPickupEvent) {

        }

        @SubscribeEvent
        public static void soundEvent(PlaySoundEvent event) {
            ResourceLocation resourceLocation = event.getSound().getLocation();
            ResourceLocation resourceLocation1 = SoundEvents.AMBIENT_CAVE.get().getLocation();
            if(resourceLocation.equals(resourceLocation1)) {
                ModPacketHandler.CHANNEL.sendToServer(new CaveNoisePacket());
            }
        }

        @SubscribeEvent
        public void onTickPlayerTick(TickEvent.PlayerTickEvent event) {
            Player player = event.player;
            player.getCapability(ModCapabilities.PHONE).ifPresent(phoneCapHandler -> {
                phoneCapHandler.callingYouProcess.tick(player, player.level());
            });
        }
    }
}
