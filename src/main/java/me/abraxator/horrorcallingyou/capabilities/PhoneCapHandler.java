package me.abraxator.horrorcallingyou.capabilities;

import net.minecraft.nbt.CompoundTag;

public class PhoneCapHandler implements PhoneCap {
    private int playedCaveNoise;
    private ScaringStage scaringStage;

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
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("playedCaveNoise", this.playedCaveNoise);
        tag.putString("scaringStage", this.scaringStage != null ? this.scaringStage.getSerializedName() : ScaringStage.OFF.getSerializedName());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.playedCaveNoise = nbt.getInt("playedCaveNoise");
        var s = nbt.getString("scaringStage");
        this.scaringStage = ScaringStage.byName(s, ScaringStage.OFF);
    }
}
