package me.abraxator.horrorcallingyou.calling.processes;

import me.abraxator.horrorcallingyou.calling.CallingYouProcess;
import me.abraxator.horrorcallingyou.calling.ScaringYouStage;
import me.abraxator.horrorcallingyou.init.ModSounds;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class NotifyCallingProcess extends CallingYouProcess {
    public NotifyCallingProcess() {
        super(ModSounds.RING.get(), ScaringYouStage.NOTIFY);
    }

    @Override
    public void tick(Player player, Level level) {
        if(canPlaySound(player, level)) {
            assert getSound() != null;
            level.playSound(null, player.getOnPos(), getSound(), SoundSource.HOSTILE, 1.0F, 1.0F);
            this.caveNoiseTimes++;
        }
    }

    private boolean canPlaySound(Player player, Level level) {
        return player instanceof LocalPlayer localPlayer &&
                localPlayer.getCurrentMood() >= 6 &&
                !level.canSeeSky(player.getOnPos()) &&
                level.isNight() &&
                level.getSkyDarken() >= 6;
    }
}
