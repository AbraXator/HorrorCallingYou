package me.abraxator.horrorcallingyou.calling;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import me.abraxator.horrorcallingyou.calling.processes.OffCallingProcess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public abstract class CallingYouProcess {
    public static final BiMap<CallingYouProcess, CallingYouProcess> NEXT_BY_PROCESS = ImmutableBiMap.<CallingYouProcess, CallingYouProcess>builder()
                .put(Processes.OFF, Processes.NOTIFY)
                .put(Processes.NOTIFY, Processes.FAKE_CALL)
                .put(Processes.FAKE_CALL, Processes.SILENT_HOUR)
                .put(Processes.SILENT_HOUR, Processes.CALLING_YOU)
                .put(Processes.CALLING_YOU, Processes.OFF)
            .build();
    @Nullable
    private final SoundEvent sound;
    private final ScaringYouStage stage;
    public int caveNoiseTimes;

    public CallingYouProcess(ScaringYouStage stage) {
        this(null, stage);
    }

    public CallingYouProcess(@Nullable SoundEvent sound, ScaringYouStage stage) {
        this.sound = sound;
        this.stage = stage;

        this.caveNoiseTimes = 0;
    }

    @Nullable
    public SoundEvent getSound() {
        return sound;
    }

    public ScaringYouStage getStage() {
        return stage;
    }

    public void tick(Player player, Level level) {}

    public boolean canChangeToNext() {
        return false;
    }

    public void onChangeToThis(Player player, Level level) {

    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("id", this.stage.name);
        tag.putInt("caveNoiseTimes", this.caveNoiseTimes);
        return tag;
    }

    public static CallingYouProcess deserializeNBT(CompoundTag tag) {
        var optional = CallingYouProcessesRegistry.PROCESSES.getEntries().stream().filter(o ->
                o.getId().getPath().equals(tag.getString("id"))).findFirst();
        if(optional.isPresent()) {
            CallingYouProcess callingYouProcess = optional.get().get();
            callingYouProcess.caveNoiseTimes = tag.getInt("caveNoiseTimes");
            return callingYouProcess;
        } else {
            return new OffCallingProcess();
        }
    }
}
