package me.abraxator.horrorcallingyou.init;

import me.abraxator.horrorcallingyou.HorrorCallingYou;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, HorrorCallingYou.MOD_ID);

    public static final RegistryObject<SoundEvent> RING = createEvent("item.horrorcallingyou.ring");

    private static RegistryObject<SoundEvent> createEvent(String sound) {
        return SOUND_EVENTS.register(sound, () -> SoundEvent.createVariableRangeEvent(HorrorCallingYou.path(sound)));
    }
}
