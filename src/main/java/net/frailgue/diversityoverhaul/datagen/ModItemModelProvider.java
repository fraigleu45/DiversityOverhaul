package net.frailgue.diversityoverhaul.datagen;

import net.frailgue.diversityoverhaul.DiversityOverhaul;
import net.frailgue.diversityoverhaul.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

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
        basicItem(ModItems.CHISEL.get());
    }
}