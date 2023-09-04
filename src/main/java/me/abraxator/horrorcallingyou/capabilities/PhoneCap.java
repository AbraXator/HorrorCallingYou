package me.abraxator.horrorcallingyou.capabilities;

import me.abraxator.horrorcallingyou.HorrorCallingYou;
import me.abraxator.horrorcallingyou.calling.ScaringYouStage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.INBTSerializable;

public interface PhoneCap extends INBTSerializable<CompoundTag> {
    ResourceLocation ID = HorrorCallingYou.path("phone");

    boolean shouldHavePhone();

    boolean hasPhone(Player player);
}
