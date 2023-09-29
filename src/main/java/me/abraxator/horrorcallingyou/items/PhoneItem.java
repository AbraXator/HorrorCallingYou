package me.abraxator.horrorcallingyou.items;

import me.abraxator.horrorcallingyou.calling.CallingYouProcess;
import me.abraxator.horrorcallingyou.capabilities.CallingYouCap;
import me.abraxator.horrorcallingyou.init.ModCapabilities;
import me.abraxator.horrorcallingyou.networking.CaveNoisePacket;
import me.abraxator.horrorcallingyou.networking.ModPacketHandler;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PhoneItem extends Item {
    public PhoneItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        if(entity instanceof Player player) {
            player.getCapability(ModCapabilities.PHONE).ifPresent(callingYouCap -> {
                CallingYouProcess callingYouProcess = callingYouCap.getCallingYouProcess();
                callingYouProcess.onTriggerFired(player, player.level(), callingYouCap.phone);
                callingYouCap.callingYouProcess = callingYouProcess;
            });
        }
        return super.onEntitySwing(stack, entity);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        pPlayer.getCapability(ModCapabilities.PHONE).ifPresent(callingYouCap -> {

        });
        ModPacketHandler.CHANNEL.sendToServer(new CaveNoisePacket());
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
        player.getCapability(ModCapabilities.PHONE).ifPresent(callingYouCap -> {
            if(callingYouCap.shouldHavePhone()) {
                setPhone(callingYouCap, stack);
                if(player instanceof ServerPlayer serverPlayer) dropAllNewPhoneStacks(serverPlayer);
            }
        });
    }

    private void dropAllNewPhoneStacks(ServerPlayer serverPlayer) {
        NonNullList<ItemStack> inventory = serverPlayer.getInventory().items;
        Stream<ItemStack> stream = inventory.stream().filter(stack -> stack.getItem() instanceof PhoneItem && !stack.getOrCreateTag().getBoolean("firstOne"));
        Map<ItemStack, Integer> map = stream.collect(Collectors.toMap(stack -> stack, inventory::indexOf));

        map.forEach((stack, integer) -> {
            serverPlayer.getInventory().removeItem(integer, 1);
            serverPlayer.drop(stack, true, true);
            serverPlayer.sendSystemMessage(Component.translatableWithFallback("horror_calling_you.phone.only_one", "You can carry only 1 phone"));
        });
    }

    private void setPhone(CallingYouCap callingYouCap, ItemStack stack) {
        if(!callingYouCap.hasPhone()) {
            callingYouCap.phone = stack;
            stack.getOrCreateTag().putBoolean("firstOne", true);
        } else {
            stack.getOrCreateTag().putBoolean("firstOne", false);
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
