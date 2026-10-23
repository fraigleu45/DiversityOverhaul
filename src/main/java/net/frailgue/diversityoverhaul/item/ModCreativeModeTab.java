package net.frailgue.diversityoverhaul.item;

import net.frailgue.diversityoverhaul.DiversityOverhaul;
import net.frailgue.diversityoverhaul.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTab {


    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
       DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DiversityOverhaul.MOD_ID);


    public static final Supplier<CreativeModeTab> DIVERSITYTABC = CREATIVE_MODE_TAB.register("diverstity_overhaul_tabc",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CELESTINGOT.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(DiversityOverhaul.MOD_ID, "diverstity_overhaul_tabh"))
                    .title(Component.translatable("creativetab.diversityoverhaul.celestial_items"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModItems.CELESTINGOT);
                        output.accept(ModBlocks.CELESTBLOCK);


                    }).build());


    public static final Supplier<CreativeModeTab> DIVERSITYTABH = CREATIVE_MODE_TAB.register("diverstity_overhaul_tabh",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.HELLINGOT.get()))
                    .title(Component.translatable("creativetab.diversityoverhaul.hell_items"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModItems.HELLINGOT);
                        output.accept(ModBlocks.HELLBLOCK);


                    }).build());






    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }



}
