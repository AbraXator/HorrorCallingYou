package me.abraxator.horrorcallingyou.capabilities;

import me.abraxator.horrorcallingyou.HorrorCallingYou;
import me.abraxator.horrorcallingyou.calling.CallingYouProcess;
import me.abraxator.horrorcallingyou.calling.processes.OffCallingProcess;
import me.abraxator.horrorcallingyou.init.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.INBTSerializable;

public class CallingYouCap implements INBTSerializable<CompoundTag> {
    public static final ResourceLocation ID = HorrorCallingYou.path("phone");
    public CallingYouProcess callingYouProcess;
    public ItemStack phone;

    public boolean shouldHavePhone() {
        return this.callingYouProcess instanceof OffCallingProcess;
    }

    public boolean hasPhone() {
        return phone.is(ModItems.PHONE.get());
    }

    public CallingYouProcess getCallingYouProcess() {
        return callingYouProcess == null ? new OffCallingProcess() : callingYouProcess;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        if(this.callingYouProcess == null) this.callingYouProcess = new OffCallingProcess();
        if(this.phone == null) this.phone = ItemStack.EMPTY;
        tag.put("callingProcess", this.callingYouProcess.serializeNBT());
        tag.put("phone", this.phone.serializeNBT());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        if(this.callingYouProcess == null) this.callingYouProcess = new OffCallingProcess();
        this.callingYouProcess = callingYouProcess.deserializeNBT(nbt.getCompound("callingProcess"));
        this.phone = ItemStack.of(nbt.getCompound("phone"));
    }
}
