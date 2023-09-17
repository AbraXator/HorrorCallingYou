package me.abraxator.horrorcallingyou.init;

import me.abraxator.horrorcallingyou.HorrorCallingYou;
import me.abraxator.horrorcallingyou.items.MysteriousNoteItem;
import me.abraxator.horrorcallingyou.items.PhoneItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HorrorCallingYou.MOD_ID);

    public static final RegistryObject<Item> PHONE = ITEMS.register("phone", () -> new PhoneItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MYSTERIOUS_NOTE = ITEMS.register("mysterious_note", () -> new MysteriousNoteItem(new Item.Properties().stacksTo(1)));
}