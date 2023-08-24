package me.abraxator.horrorcallingyou.capabilities;

import me.abraxator.horrorcallingyou.HorrorCallingYou;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Locale;

public interface PhoneCap extends INBTSerializable<CompoundTag> {
    ResourceLocation ID = HorrorCallingYou.path("phone");

    void setPlayedCaveNoise(int playedCaveNoise);

    void addPlayedCaveNoise();

    void resetPlayedCaveNoise();

    int getPlayedCaveNoise();

    ScaringStage geScaringStage();

    void setScaringStage(ScaringStage scaringStage);

     enum ScaringStage implements StringRepresentable {
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
}
