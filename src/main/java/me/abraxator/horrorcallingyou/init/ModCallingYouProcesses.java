package me.abraxator.horrorcallingyou.init;

import me.abraxator.horrorcallingyou.calling.CallingYouProcess;
import me.abraxator.horrorcallingyou.calling.processes.*;
import net.minecraftforge.registries.RegistryObject;

import static me.abraxator.horrorcallingyou.calling.CallingYouProcessesRegistry.PROCESSES;

public class ModCallingYouProcesses {
    public static final RegistryObject<CallingYouProcess> OFF = PROCESSES.register("off", OffCallingProcess::new);
    public static final RegistryObject<CallingYouProcess> NOTIFY = PROCESSES.register("notify", NotifyCallingProcess::new);
    public static final RegistryObject<CallingYouProcess> FAKE_CALL = PROCESSES.register("fake_call", FakeCallCallingProcess::new);
    public static final RegistryObject<CallingYouProcess> SILENT_HOUR = PROCESSES.register("silent_hour", SilentHourCallingProcess::new);
    public static final RegistryObject<CallingYouProcess> CALLING_YOU = PROCESSES.register("calling_you", CallingYouCallingProcess::new);

    public static void register() {
        ModCallingYouProcesses modCallingYouProcesses = null;
    }
}
