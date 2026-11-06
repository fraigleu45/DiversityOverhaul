package net.frailgue.diversityoverhaul.datagen;

import net.frailgue.diversityoverhaul.DiversityOverhaul;
import net.frailgue.diversityoverhaul.block.ModBlocks;
import net.frailgue.diversityoverhaul.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, DiversityOverhaul.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.CELESTINGOT.get());
        basicItem(ModItems.HELLINGOT.get());

        basicItem(ModItems.RADISH.get());
        basicItem(ModItems.SHARDCOAL.get());
        basicItem(ModItems.ULTRA_COAL.get());

        basicItem(ModItems.GOLDWAXE.get());


        buttonItem(ModBlocks.CELESTBLOCK_BUTTON, ModBlocks.CELESTBLOCK);
        fenceItem(ModBlocks.CELESTBLOCK_FENCE, ModBlocks.CELESTBLOCK);
        wallItem(ModBlocks.CELESTBLOCK_WALL, ModBlocks.CELESTBLOCK);

        basicItem(ModBlocks.CELESTBLOCK_DOOR.asItem());




        handheldItem(ModItems.CELEST_SWORD);
        handheldItem(ModItems.CELEST_PICKAXE);
        handheldItem(ModItems.CELEST_SHOVEL);
        handheldItem(ModItems.CELEST_AXE);
        handheldItem(ModItems.CELEST_HOE);




    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(DiversityOverhaul.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(DiversityOverhaul.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", ResourceLocation.fromNamespaceAndPath(DiversityOverhaul.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));

    }


    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(DiversityOverhaul.MOD_ID,"item/" + item.getId().getPath()));
    }




}