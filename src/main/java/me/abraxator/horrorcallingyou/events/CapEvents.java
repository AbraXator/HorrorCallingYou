package me.abraxator.horrorcallingyou.events;

import me.abraxator.horrorcallingyou.HorrorCallingYou;
import me.abraxator.horrorcallingyou.init.ModCapabilities;
import me.abraxator.horrorcallingyou.networking.ModPacketHandler;
import me.abraxator.horrorcallingyou.networking.UpdateCaveNoiseCountPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

public class CapEvents {

    @Mod.EventBusSubscriber(modid = HorrorCallingYou.MOD_ID)
    public static class ModEvents {
        @SubscribeEvent
        public static void updateCaps(LivingEvent.LivingTickEvent event) {
        }
    }

    @Mod.EventBusSubscriber(modid = HorrorCallingYou.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void soundEvent(PlaySoundEvent event) {
            ResourceLocation resourceLocation = event.getSound().getLocation();
            ResourceLocation resourceLocation1 = SoundEvents.AMBIENT_CAVE.get().getLocation();
            if(resourceLocation.equals(resourceLocation1)) {
                ModPacketHandler.CHANNEL.sendToServer(new UpdateCaveNoiseCountPacket());
            }
        }
    }
}
