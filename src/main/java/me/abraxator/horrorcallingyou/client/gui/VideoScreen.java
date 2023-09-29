package me.abraxator.horrorcallingyou.client.gui;

import com.mojang.blaze3d.platform.MemoryTracker;
import me.srrapero720.watermedia.api.player.SyncVideoPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.inventory.AbstractContainerMenu;

import java.util.Objects;

public class VideoScreen extends AbstractContainerScreen<AbstractContainerMenu> {

    private final SyncVideoPlayer player;

    public VideoScreen() {
        super(new EmptyMenu(), Objects.requireNonNull(Minecraft.getInstance().player.getInventory()), Component.empty());
        minecraft.getSoundManager();

        this.player = new SyncVideoPlayer(null, minecraft, MemoryTracker::create);
        this.player.setVolume((int) (Minecraft.getInstance().options.getSoundSourceVolume(SoundSource.MASTER) * 100F));
        player.start();
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {

    }
}
