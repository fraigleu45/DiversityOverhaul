package net.frailgue.diversityoverhaul.datagen;

import net.frailgue.diversityoverhaul.DiversityOverhaul;
import net.frailgue.diversityoverhaul.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.SlabBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.client.model.generators.ModelFile;



public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, DiversityOverhaul.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.CELESTBLOCK);

        blockWithItem(ModBlocks.COSMICBLOCK);
        blockWithItem(ModBlocks.HELLBLOCK);
        blockWithItem(ModBlocks.GOlDENWOOD);



    stairsBlock(ModBlocks.CELESTBLOCK_STAIRS.get(), blockTexture(ModBlocks.CELESTBLOCK.get()));
    slabBlock(ModBlocks.CELESTBLOCK_SLAB.get(), blockTexture(ModBlocks.CELESTBLOCK.get()), blockTexture(ModBlocks.CELESTBLOCK.get()));

    buttonBlock(ModBlocks.CELESTBLOCK_BUTTON.get(), blockTexture(ModBlocks.CELESTBLOCK.get()));
        pressurePlateBlock(ModBlocks.CELESTBLOCK_PRESSURE_PLATE.get(), blockTexture(ModBlocks.CELESTBLOCK.get()));

        fenceBlock(ModBlocks.CELESTBLOCK_FENCE.get(), blockTexture(ModBlocks.CELESTBLOCK.get()));
        fenceGateBlock(ModBlocks.CELESTBLOCK_FENCE_GATE.get(), blockTexture(ModBlocks.CELESTBLOCK.get()));
        wallBlock(ModBlocks.CELESTBLOCK_WALL.get(), blockTexture(ModBlocks.CELESTBLOCK.get()));

        doorBlockWithRenderType(ModBlocks.CELESTBLOCK_DOOR.get(), modLoc("block/celestial_door_bottom"), modLoc("block/celestial_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.CELESTBLOCK_TRAPDOOR.get(), modLoc("block/celestial_trapdoor"), true, "cutout");



        blockItem(ModBlocks.CELESTBLOCK_STAIRS);
        blockItem(ModBlocks.CELESTBLOCK_SLAB);
        blockItem(ModBlocks.CELESTBLOCK_PRESSURE_PLATE);
        blockItem(ModBlocks.CELESTBLOCK_FENCE_GATE);
        blockItem(ModBlocks.CELESTBLOCK_TRAPDOOR, "_bottom");


    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("diversityoverhaul:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("diversityoverhaul:block/" + deferredBlock.getId().getPath() + appendix));
    }

}