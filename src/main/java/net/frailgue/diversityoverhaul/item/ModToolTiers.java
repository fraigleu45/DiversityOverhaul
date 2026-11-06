package net.frailgue.diversityoverhaul.item;

import net.frailgue.diversityoverhaul.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier CELEST = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_CELEST_TOOL,
            1400, 4f, 3f, 28, () -> Ingredient.of(ModItems.CELESTINGOT));

}
