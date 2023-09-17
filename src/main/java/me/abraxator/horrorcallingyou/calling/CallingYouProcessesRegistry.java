package me.abraxator.horrorcallingyou.calling;

import me.abraxator.horrorcallingyou.HorrorCallingYou;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

public class CallingYouProcessesRegistry {
    public static final ResourceKey<Registry<CallingYouProcess>> PROCESSES_KEY = ResourceKey.createRegistryKey(HorrorCallingYou.path("processes"));
    public static final DeferredRegister<CallingYouProcess> PROCESSES = DeferredRegister.create(CallingYouProcessesRegistry.PROCESSES_KEY, HorrorCallingYou.MOD_ID);
    public static final Supplier<IForgeRegistry<CallingYouProcess>> PROCESSES_REGISTRY = PROCESSES.makeRegistry(RegistryBuilder::new);
}
