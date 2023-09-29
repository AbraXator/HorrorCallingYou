package me.abraxator.horrorcallingyou.calling;

import com.google.common.collect.ImmutableBiMap;
import me.abraxator.horrorcallingyou.calling.processes.OffCallingProcess;
import me.abraxator.horrorcallingyou.init.ModCallingYouProcesses;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public abstract class CallingYouProcess {

    @Nullable public final SoundEvent sound;
    public final ScaringYouStage stage;
    public final String id;

    public int caveNoiseTimes;
    public int notifyNoiseTimes;

    public CallingYouProcess(ScaringYouStage stage) {
        this(null, stage);
    }

    public CallingYouProcess(@Nullable SoundEvent sound, ScaringYouStage stage) {
        this.sound = sound;
        this.stage = stage;

        this.id = stage.name();
    }

    public void tick(Player player, Level level) {}

    public boolean canChangeToNext() {
        return false;
    }

    public void onChangeToThis(Player player, Level level) {}

    public static ImmutableBiMap<CallingYouProcess, CallingYouProcess> getNextProcess() {
        return ImmutableBiMap.<CallingYouProcess, CallingYouProcess>builder()
                .put(ModCallingYouProcesses.OFF.get(), ModCallingYouProcesses.NOTIFY.get())
                .put(ModCallingYouProcesses.NOTIFY.get(), ModCallingYouProcesses.FAKE_CALL.get())
                .put(ModCallingYouProcesses.FAKE_CALL.get(), ModCallingYouProcesses.SILENT_HOUR.get())
                .put(ModCallingYouProcesses.SILENT_HOUR.get(), ModCallingYouProcesses.CALLING_YOU.get())
                .put(ModCallingYouProcesses.CALLING_YOU.get(), ModCallingYouProcesses.OFF.get())
                .build();
    }

    public static CallingYouProcess updateToNextProcess(ServerPlayer player, ServerLevel serverLevel, CallingYouProcess callingYouProcess) {
        callingYouProcess = getNextProcess().getOrDefault(callingYouProcess, ModCallingYouProcesses.OFF.get());
        callingYouProcess.onChangeToThis(player, serverLevel);
        return callingYouProcess;
        /*if(callingYouProcess.canChangeToNext()) {
            callingYouProcess = getNextProcess().getOrDefault(callingYouProcess, ModCallingYouProcesses.OFF.get());
            callingYouProcess.onChangeToThis(player, serverLevel);
        }
        return callingYouProcess;*/
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("id", this.stage.name);
        tag.putInt("caveNoiseTimes", this.caveNoiseTimes);
        tag.putInt("notifyNoiseTimes", this.notifyNoiseTimes);
        return tag;
    }

    public CallingYouProcess deserializeNBT(CompoundTag tag) {
        var optional = CallingYouProcessesRegistry.PROCESSES.getEntries().stream().filter(o ->
                o.getId().getPath().equals(tag.getString("id"))).findFirst();
        if(optional.isPresent()) {
            CallingYouProcess callingYouProcess = optional.get().get();
            callingYouProcess.caveNoiseTimes = tag.getInt("caveNoiseTimes");
            callingYouProcess.notifyNoiseTimes = tag.getInt("notifyNoiseTimes");
            return callingYouProcess;
        } else {
            return new OffCallingProcess();
        }
    }

    public void onTriggerFired(Player player, Level level, ItemStack phoneStack) {

    }
}
