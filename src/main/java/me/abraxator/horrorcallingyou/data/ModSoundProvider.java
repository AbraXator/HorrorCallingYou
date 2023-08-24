package me.abraxator.horrorcallingyou.data;

import me.abraxator.horrorcallingyou.HorrorCallingYou;
import me.abraxator.horrorcallingyou.init.ModSounds;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import net.minecraftforge.registries.RegistryObject;

public class ModSoundProvider extends SoundDefinitionsProvider {
    protected ModSoundProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, HorrorCallingYou.MOD_ID, helper);
    }

    @Override
    public void registerSounds() {
        generateNewSoundWithSubtitle(ModSounds.RING, "ring", 1);
    }

    public void generateNewSoundWithSubtitle(RegistryObject<SoundEvent> event, String baseSoundDirectory, int numberOfSounds) {
        generateNewSound(event, baseSoundDirectory, numberOfSounds, true);
    }

    public void generateNewSound(RegistryObject<SoundEvent> event, String baseSoundDirectory, int numberOfSounds, boolean subtitle) {
        SoundDefinition definition = SoundDefinition.definition();
        if (subtitle) {
            String[] splitSoundName = event.getId().getPath().split("\\.", 3);
            definition.subtitle("subtitles.horrorcallingyou." + splitSoundName[0] + "." + splitSoundName[2]);
        }
        for (int i = 1; i <= numberOfSounds; i++) {
            definition.with(SoundDefinition.Sound.sound(new ResourceLocation(HorrorCallingYou.MOD_ID, baseSoundDirectory + (numberOfSounds > 1 ? i : "")), SoundDefinition.SoundType.SOUND));
        }
        this.add(event, definition);
    }

    public void generateNewSoundMC(RegistryObject<SoundEvent> event, String baseSoundDirectory, int numberOfSounds, boolean subtitle) {
        SoundDefinition definition = SoundDefinition.definition();
        if (subtitle) {
            String[] splitSoundName = event.getId().getPath().split("\\.", 3);
            definition.subtitle("subtitles.horrorcallingyou." + splitSoundName[0] + "." + splitSoundName[2]);
        }
        for (int i = 1; i <= numberOfSounds; i++) {
            definition.with(SoundDefinition.Sound.sound(new ResourceLocation(baseSoundDirectory + (numberOfSounds > 1 ? i : "")), SoundDefinition.SoundType.SOUND));
        }
        this.add(event, definition);
    }

    public void generateExistingSoundWithSubtitle(RegistryObject<SoundEvent> event, SoundEvent referencedSound) {
        this.generateExistingSound(event, referencedSound, true);
    }

    public void generateSoundWithCustomSubtitle(RegistryObject<SoundEvent> event, SoundEvent referencedSound, String subtitle) {
        this.add(event, SoundDefinition.definition()
                .subtitle(subtitle)
                .with(SoundDefinition.Sound.sound(referencedSound.getLocation(), SoundDefinition.SoundType.EVENT)));
    }

    public void generateExistingSound(RegistryObject<SoundEvent> event, SoundEvent referencedSound, boolean subtitle) {
        SoundDefinition definition = SoundDefinition.definition();
        if (subtitle) {
            String[] splitSoundName = event.getId().getPath().split("\\.", 3);
            definition.subtitle("subtitles.horrorcallingyou." + splitSoundName[0] + "." + splitSoundName[2]);
        }
        this.add(event, definition
                .with(SoundDefinition.Sound.sound(referencedSound.getLocation(), SoundDefinition.SoundType.EVENT)));
    }

    public void makeStepSound(RegistryObject<SoundEvent> event, SoundEvent referencedSound) {
        this.add(event, SoundDefinition.definition()
                .subtitle("subtitles.block.generic.footsteps")
                .with(SoundDefinition.Sound.sound(referencedSound.getLocation(), SoundDefinition.SoundType.EVENT)));
    }

    public void makeMusicDisc(RegistryObject<SoundEvent> event, String discName) {
        this.add(event, SoundDefinition.definition()
                .with(SoundDefinition.Sound.sound(new ResourceLocation(HorrorCallingYou.MOD_ID, "music/" + discName), SoundDefinition.SoundType.SOUND)
                        .stream()));
    }

    public void generateParrotSound(RegistryObject<SoundEvent> event, SoundEvent referencedSound) {
        SoundDefinition definition = SoundDefinition.definition();
        String[] splitSoundName = event.getId().getPath().split("\\.", 3);
        definition.subtitle("subtitles.horrorcallingyou." + splitSoundName[0] + "." + splitSoundName[2]);

        this.add(event, definition
                .with(SoundDefinition.Sound.sound(referencedSound.getLocation(), SoundDefinition.SoundType.EVENT).pitch(1.8F).volume(0.6F)));
    }
}
