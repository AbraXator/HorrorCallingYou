package me.abraxator.horrorcallingyou.items;

import me.abraxator.horrorcallingyou.calling.ScaringStage;
import me.abraxator.horrorcallingyou.capabilities.PhoneCap;
import me.abraxator.horrorcallingyou.capabilities.PhoneCapHandler;
import me.abraxator.horrorcallingyou.init.ModCapabilities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Arrays;

public class PhoneItem extends Item {
    public PhoneItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        pPlayer.getCapability(ModCapabilities.PHONE).ifPresent(phoneCapHandler -> {
            ScaringStage scaringStage = Arrays.stream(ScaringStage.values()).toList().get(phoneCapHandler.geScaringStage().ordinal() + 1 == 5 ? 0 : phoneCapHandler.geScaringStage().ordinal() + 1);
            phoneCapHandler.setScaringStage(scaringStage);
        });
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
        player.getCapability(ModCapabilities.PHONE).ifPresent(phoneCapHandler -> {
            if(phoneCapHandler.shouldHavePhone() && !phoneCapHandler.hasPhone(player)) {
                phoneCapHandler.setPhone(stack);
            }

            if(phoneCapHandler.geScaringStage() == ScaringStage.OFF) {
                phoneCapHandler.setScaringStage(ScaringStage.NOTIFY);
                stack.getOrCreateTag().putInt("stage", phoneCapHandler.geScaringStage().ordinal());
            }
        });
    }
}
