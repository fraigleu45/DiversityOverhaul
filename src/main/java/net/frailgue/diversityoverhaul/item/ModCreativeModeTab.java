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
                        output.accept(ModBlocks.COSMICBLOCK);
                        output.accept(ModBlocks.CELESTBLOCK_STAIRS);
                        output.accept(ModBlocks.CELESTBLOCK_SLAB);
                        output.accept(ModBlocks.CELESTBLOCK_PRESSURE_PLATE);
                        output.accept(ModBlocks.CELESTBLOCK_BUTTON);
                        output.accept(ModBlocks.CELESTBLOCK_FENCE);
                        output.accept(ModBlocks.CELESTBLOCK_FENCE_GATE);
                        output.accept(ModBlocks.CELESTBLOCK_WALL);
                        output.accept(ModBlocks.CELESTBLOCK_DOOR);
                        output.accept(ModBlocks.CELESTBLOCK_TRAPDOOR);
                        output.accept(ModItems.CELEST_SWORD);
                        output.accept(ModItems.CELEST_AXE);
                        output.accept(ModItems.CELEST_PICKAXE);
                        output.accept(ModItems.CELEST_SHOVEL);
                        output.accept(ModItems.CELEST_HOE);


                    }).build());


    public static final Supplier<CreativeModeTab> DIVERSITYTABH = CREATIVE_MODE_TAB.register("diverstity_overhaul_tabh",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.HELLINGOT.get()))
                    .title(Component.translatable("creativetab.diversityoverhaul.hell_items"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModItems.HELLINGOT);
                        output.accept(ModBlocks.HELLBLOCK);
                        output.accept(ModItems.RADISH);
                        output.accept(ModItems.ULTRA_COAL);
                        output.accept(ModItems.SHARDCOAL);
              

                    }).build());


    public static final Supplier<CreativeModeTab> DIVERSITYTABE = CREATIVE_MODE_TAB.register("diverstity_overhaul_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CHISEL.get()))
                    .title(Component.translatable("creativetab.diversityoverhaul.items"))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(DiversityOverhaul.MOD_ID, "diverstity_overhaul_tabc"))
                    .displayItems((itemDisplayParameters, output) -> {


                        output.accept(ModItems.DCHISEL);
                        output.accept(ModItems.CHISEL);
                        output.accept(ModItems.SAW);
                        output.accept(ModItems.GOLDWAXE);
                        output.accept(ModBlocks.GOlDENWOOD);
                        output.accept(ModItems.DSAW);


                    }).build());






    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }



}
