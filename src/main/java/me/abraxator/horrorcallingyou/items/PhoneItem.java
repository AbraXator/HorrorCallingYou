package me.abraxator.horrorcallingyou.items;

import me.abraxator.horrorcallingyou.calling.ScaringYouStage;
import me.abraxator.horrorcallingyou.init.ModCapabilities;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class PhoneItem extends Item {
    public PhoneItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        pPlayer.getCapability(ModCapabilities.PHONE).ifPresent(phoneCapHandler -> {
            ScaringYouStage scaringYouStage = Arrays.stream(ScaringYouStage.values()).toList().get(phoneCapHandler.geScaringStage().ordinal() + 1 == 5 ? 0 : phoneCapHandler.geScaringStage().ordinal() + 1);
            phoneCapHandler.setScaringStage(scaringYouStage);
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

            if(phoneCapHandler.geScaringStage() == ScaringYouStage.OFF) {
                phoneCapHandler.setScaringStage(ScaringYouStage.NOTIFY);
                stack.getOrCreateTag().putInt("stage", phoneCapHandler.geScaringStage().ordinal());
            }
        });
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
