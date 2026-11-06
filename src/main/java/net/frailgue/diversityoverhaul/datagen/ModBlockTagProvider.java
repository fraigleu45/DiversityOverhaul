package net.frailgue.diversityoverhaul.datagen;

import net.frailgue.diversityoverhaul.DiversityOverhaul;
import net.frailgue.diversityoverhaul.block.ModBlocks;
import net.frailgue.diversityoverhaul.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, DiversityOverhaul.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.COSMICBLOCK.get())
                .add(ModBlocks.CELESTBLOCK.get())
                .add(ModBlocks.HELLBLOCK.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.COSMICBLOCK.get());

        tag(BlockTags.FENCES).add(ModBlocks.CELESTBLOCK_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(ModBlocks.CELESTBLOCK_FENCE_GATE.get());
        tag(BlockTags.WALLS).add(ModBlocks.CELESTBLOCK_WALL.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.GOlDENWOOD.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.GOlDENWOOD.get());


        tag(ModTags.Blocks.NEEDS_CELEST_TOOL)
                .add(ModBlocks.HELLBLOCK.get())
                .addTag(BlockTags.NEEDS_IRON_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_CELEST_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .remove(ModTags.Blocks.NEEDS_CELEST_TOOL);

    }
}