package me.abraxator.horrorcallingyou.calling;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public enum ScaringStage implements StringRepresentable {
    OFF("off"),
    NOTIFY("notify"),
    FAKE_CALL("fake_call"),
    SILENT_HOUR("silent_hour"),
    CALLING_YOU("calling_you");

    final String name;

    ScaringStage(String name) {
        this.name = name;
    }

    public static final StringRepresentable.EnumCodec<ScaringStage> CODEC = StringRepresentable.fromEnum(ScaringStage::values);

    @Nullable
    public static ScaringStage byName(String pTranslationKey, @Nullable ScaringStage pFallback) {
        ScaringStage scaringStage = CODEC.byName(pTranslationKey);
        return scaringStage != null ? scaringStage : pFallback;
    }

    @Override
    public @NotNull String getSerializedName() {
        return name;
    }
}
