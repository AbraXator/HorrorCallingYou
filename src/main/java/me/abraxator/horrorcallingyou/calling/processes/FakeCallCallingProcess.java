package me.abraxator.horrorcallingyou.calling.processes;

import me.abraxator.horrorcallingyou.calling.CallingYouProcess;
import me.abraxator.horrorcallingyou.calling.ScaringYouStage;

public class FakeCallCallingProcess extends CallingYouProcess {
    public FakeCallCallingProcess() {
        super(ScaringYouStage.FAKE_CALL);
    }

    @Override
    public boolean canChangeToNext() {
        return this.caveNoiseTimes >= 2;
    }
}
