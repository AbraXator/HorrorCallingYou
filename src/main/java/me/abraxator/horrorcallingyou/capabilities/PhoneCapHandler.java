package me.abraxator.horrorcallingyou.capabilities;

import me.abraxator.horrorcallingyou.calling.CallingYouProcess;
import me.abraxator.horrorcallingyou.calling.ScaringYouStage;
import me.abraxator.horrorcallingyou.init.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class PhoneCapHandler implements PhoneCap {
    public ItemStack phone;
    public CallingYouProcess callingYouProcess;

    @Override
    public boolean shouldHavePhone() {
        return this.callingYouProcess.getStage() != ScaringYouStage.OFF;
    }

    @Override
    public boolean hasPhone(Player player) {
        return phone.is(ModItems.PHONE.get());
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.put("phone", this.phone.serializeNBT());
        tag.put("callingProcess", this.callingYouProcess.serializeNBT());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.phone = ItemStack.of(nbt.getCompound("phone"));
        this.callingYouProcess = CallingYouProcess.deserializeNBT(nbt.getCompound("callingProcess"));
    }
}
