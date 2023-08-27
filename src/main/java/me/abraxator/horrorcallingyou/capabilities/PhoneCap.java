package me.abraxator.horrorcallingyou.capabilities;

import me.abraxator.horrorcallingyou.HorrorCallingYou;
import me.abraxator.horrorcallingyou.calling.ScaringStage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Locale;

public interface PhoneCap extends INBTSerializable<CompoundTag> {
    ResourceLocation ID = HorrorCallingYou.path("phone");

    boolean shouldHavePhone();

    boolean hasPhone(Player player);

    ItemStack getPhone();

    void setPhone(ItemStack phone);

    void setPlayedCaveNoise(int playedCaveNoise);

    void addPlayedCaveNoise();

    void resetPlayedCaveNoise();

    int getPlayedCaveNoise();

    ScaringStage geScaringStage();

    void setScaringStage(ScaringStage scaringStage);

    float getSignal();

    void setSignal(float signal);
}
