package me.abraxator.horrorcallingyou.client;

import me.abraxator.horrorcallingyou.HorrorCallingYou;
import me.abraxator.horrorcallingyou.init.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;

public class ModItemProperties {
    public static void register() {
        ItemProperties.register(ModItems.MYSTERIOUS_NOTE.get(), HorrorCallingYou.path("solved"), (pStack, pLevel, pEntity, pSeed) ->
                pStack.getOrCreateTag().getBoolean("solved") ? 1.0F : 0.1F);
    }
}
