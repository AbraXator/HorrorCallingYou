package me.abraxator.horrorcallingyou.calling;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public enum ScaringYouStage implements StringRepresentable {
    OFF("off"),
    NOTIFY("notify"),
    FAKE_CALL("fake_call"),
    SILENT_HOUR("silent_hour"),
    CALLING_YOU("calling_you");

    final String name;

    ScaringYouStage(String name) {
        this.name = name;
    }

    public static final StringRepresentable.EnumCodec<ScaringYouStage> CODEC = StringRepresentable.fromEnum(ScaringYouStage::values);

    @Nullable
    public static ScaringYouStage byName(String pTranslationKey, @Nullable ScaringYouStage pFallback) {
        ScaringYouStage scaringYouStage = CODEC.byName(pTranslationKey);
        return scaringYouStage != null ? scaringYouStage : pFallback;
    }

    @Override
    public @NotNull String getSerializedName() {
        return name;
    }
}
