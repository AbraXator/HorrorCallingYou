package me.abraxator.horrorcallingyou.calling.processes;

import me.abraxator.horrorcallingyou.calling.CallingYouProcess;
import me.abraxator.horrorcallingyou.calling.ScaringYouStage;

public class OffCallingProcess extends CallingYouProcess {
    public OffCallingProcess() {
        super(ScaringYouStage.OFF);
    }

    @Override
    public boolean canChangeToNext() {
        return this.caveNoiseTimes >= 2;
    }
}
