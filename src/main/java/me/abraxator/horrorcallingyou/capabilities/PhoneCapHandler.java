package me.abraxator.horrorcallingyou.capabilities;

import me.abraxator.horrorcallingyou.calling.ScaringStage;
import me.abraxator.horrorcallingyou.init.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class PhoneCapHandler implements PhoneCap {
    private ItemStack phone;
    private int playedCaveNoise;
    private ScaringStage scaringStage;
    private float signal;

    @Override
    public boolean shouldHavePhone() {
        return this.scaringStage != ScaringStage.OFF;
    }

    @Override
    public boolean hasPhone(Player player) {
        return phone.is(ModItems.PHONE.get());
    }

    @Override
    public ItemStack getPhone() {
        return phone;
    }

    @Override
    public void setPhone(ItemStack phone) {
        this.phone = phone;
    }

    @Override
    public void setPlayedCaveNoise(int playedCaveNoise) {
        this.playedCaveNoise = playedCaveNoise;
    }

    @Override
    public void addPlayedCaveNoise() {
        this.playedCaveNoise++;
    }

    @Override
    public void resetPlayedCaveNoise() {
        this.playedCaveNoise = 0;
    }

    @Override
    public int getPlayedCaveNoise() {
        return this.playedCaveNoise;
    }

    @Override
    public ScaringStage geScaringStage() {
        return this.scaringStage;
    }

    @Override
    public void setScaringStage(ScaringStage scaringStage) {
        this.scaringStage = scaringStage;
    }

    @Override
    public float getSignal() {
        return this.signal;
    }

    @Override
    public void setSignal(float signal) {
        this.signal = signal;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.put("phone", this.phone.serializeNBT());
        tag.putInt("playedCaveNoise", this.playedCaveNoise);
        tag.putString("scaringStage", this.scaringStage != null ? this.scaringStage.getSerializedName() : ScaringStage.OFF.getSerializedName());
        tag.putFloat("signal", this.signal);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.phone = ItemStack.of(nbt.getCompound("phone"));
        this.playedCaveNoise = nbt.getInt("playedCaveNoise");
        var s = nbt.getString("scaringStage");
        this.scaringStage = ScaringStage.byName(s, ScaringStage.OFF);
        this.signal = nbt.getFloat("signal");
    }
}
