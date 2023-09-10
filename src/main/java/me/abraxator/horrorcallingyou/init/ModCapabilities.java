package me.abraxator.horrorcallingyou.init;

import me.abraxator.horrorcallingyou.capabilities.CallingYouCap;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModCapabilities {
    public static final Capability<CallingYouCap> PHONE = CapabilityManager.get(new CapabilityToken<>() {});

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(CallingYouCap.class);
    }

    public static void attachEntityCapability(AttachCapabilitiesEvent<Entity> event) {
        if(!(event.getObject() instanceof LivingEntity entity)) {
            return;
        }

        event.addCapability(CallingYouCap.ID, new ICapabilitySerializable<CompoundTag>() {
            final LazyOptional<CallingYouCap> instance = LazyOptional.of(CallingYouCap::new);

            @Override
            public CompoundTag serializeNBT() {
                return instance.orElseThrow(NullPointerException::new).serializeNBT();
            }

            @Override
            public void deserializeNBT(CompoundTag nbt) {
                instance.orElseThrow(NullPointerException::new).deserializeNBT(nbt);
            }

            @Override
            public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                return PHONE.orEmpty(cap, instance.cast());
            }
        });
    }
}
