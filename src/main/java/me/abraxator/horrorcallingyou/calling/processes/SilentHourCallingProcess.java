package me.abraxator.horrorcallingyou.calling.processes;

import me.abraxator.horrorcallingyou.calling.CallingYouProcess;
import me.abraxator.horrorcallingyou.calling.ScaringYouStage;

public class SilentHourCallingProcess extends CallingYouProcess {
    public SilentHourCallingProcess() {
        super(ScaringYouStage.SILENT_HOUR);
    }
}
