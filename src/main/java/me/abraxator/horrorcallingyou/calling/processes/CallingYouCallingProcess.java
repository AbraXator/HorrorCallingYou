package me.abraxator.horrorcallingyou.calling.processes;

import me.abraxator.horrorcallingyou.calling.CallingYouProcess;
import me.abraxator.horrorcallingyou.calling.ScaringYouStage;
import me.abraxator.horrorcallingyou.items.PhoneItem;
import me.abraxator.horrorcallingyou.networking.JumpscarePacket;
import me.abraxator.horrorcallingyou.networking.ModPacketHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PacketDistributor;

public class CallingYouCallingProcess extends CallingYouProcess {
    private int mediaPlayerId;

    public CallingYouCallingProcess() {
        super(ScaringYouStage.CALLING_YOU);
    }

    @Override
    public void onTriggerFired(Player player, Level level, ItemStack phoneStack) {
        PhoneItem phoneItem = (PhoneItem) phoneStack.getItem().asItem();
        ModPacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> ((ServerPlayer) player)), new JumpscarePacket());
    }
}
