package me.abraxator.horrorcallingyou.calling.processes;

import me.abraxator.horrorcallingyou.calling.CallingYouProcess;
import me.abraxator.horrorcallingyou.calling.ScaringYouStage;
import me.abraxator.horrorcallingyou.init.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FakeCallCallingProcess extends CallingYouProcess {
    public FakeCallCallingProcess() {
        super(ScaringYouStage.FAKE_CALL);
    }

    @Override
    public void onTriggerFired(Player player, Level level, ItemStack phoneStack) {
        SoundManager soundEngine = Minecraft.getInstance().getSoundManager();
        SoundInstance soundInstance =
                new SimpleSoundInstance(ModSounds.RING.getId(), SoundSource.AMBIENT, 1.0F, 1.0F, level.random, true, 0,
                SoundInstance.Attenuation.LINEAR, player.getX(), player.getY(), player.getZ(), false);
        soundEngine.play(soundInstance);
    }
}
