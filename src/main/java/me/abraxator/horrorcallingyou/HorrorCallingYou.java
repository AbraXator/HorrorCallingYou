package me.abraxator.horrorcallingyou;

import com.mojang.logging.LogUtils;
import me.abraxator.horrorcallingyou.calling.CallingYouProcessesRegistry;
import me.abraxator.horrorcallingyou.init.ModCallingYouProcesses;
import me.abraxator.horrorcallingyou.init.ModCapabilities;
import me.abraxator.horrorcallingyou.init.ModItems;
import me.abraxator.horrorcallingyou.init.ModSounds;
import me.abraxator.horrorcallingyou.networking.ModPacketHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(HorrorCallingYou.MOD_ID)
public class HorrorCallingYou {
    public static final String MOD_ID = "horrorcallingyou";
    public static final Logger LOGGER = LogUtils.getLogger();

    public HorrorCallingYou() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, ModCapabilities::attachEntityCapability);

        ModItems.ITEMS.register(modEventBus);
        CallingYouProcessesRegistry.PROCESSES.register(modEventBus);
        ModCallingYouProcesses.register();
        ModSounds.SOUND_EVENTS.register(modEventBus);

        modEventBus.addListener(ModCapabilities::registerCapabilities);
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModPacketHandler.init();
        });
    }

    public static ResourceLocation path(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
