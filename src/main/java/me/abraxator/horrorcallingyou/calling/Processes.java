package me.abraxator.horrorcallingyou.calling;

import me.abraxator.horrorcallingyou.calling.processes.NotifyCallingProcess;
import me.abraxator.horrorcallingyou.calling.processes.OffCallingProcess;

public class Processes {
    public static final CallingYouProcess OFF = new OffCallingProcess();
    public static final CallingYouProcess NOTIFY = new NotifyCallingProcess();
    public static final CallingYouProcess FAKE_CALL = new NotifyCallingProcess();
    public static final CallingYouProcess SILENT_HOUR = new NotifyCallingProcess();
    public static final CallingYouProcess CALLING_YOU = new NotifyCallingProcess();
}
