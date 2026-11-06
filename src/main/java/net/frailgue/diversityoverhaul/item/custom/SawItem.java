package net.frailgue.diversityoverhaul.item.custom;

import net.frailgue.diversityoverhaul.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;

import java.util.Map;

public class SawItem extends Item {

    // 1. Map pour transformer le bloc cliqué en un autre bloc (ex: Minerai -> Pierre)
    private static final Map<Block, Block> CHISEL_MAP = Map.ofEntries(
            Map.entry(Blocks.COAL_ORE, Blocks.STONE),
            Map.entry(Blocks.COPPER_ORE, Blocks.STONE),
            Map.entry(Blocks.IRON_ORE, Blocks.STONE),
            Map.entry(Blocks.LAPIS_ORE, Blocks.STONE),
            Map.entry(Blocks.GOLD_ORE, Blocks.STONE),
            Map.entry(Blocks.DEEPSLATE_COAL_ORE, Blocks.DEEPSLATE),
            Map.entry(Blocks.DEEPSLATE_COPPER_ORE, Blocks.DEEPSLATE),
            Map.entry(Blocks.DEEPSLATE_IRON_ORE, Blocks.DEEPSLATE),
            Map.entry(Blocks.DEEPSLATE_LAPIS_ORE, Blocks.DEEPSLATE),
            Map.entry(Blocks.DEEPSLATE_GOLD_ORE, Blocks.DEEPSLATE)
    );

    // 2. Map pour les drops classiques (ex: Minerai -> Charbon)
    private static final Map<Block, ItemStack> CHISEL_DROPS_MAP = Map.ofEntries(
            Map.entry(Blocks.COAL_ORE, new ItemStack(Items.COAL, 2)),
            Map.entry(Blocks.COPPER_ORE, new ItemStack(Items.RAW_COPPER, 3)),
            Map.entry(Blocks.IRON_ORE, new ItemStack(Items.RAW_IRON)),
            Map.entry(Blocks.GOLD_ORE, new ItemStack(Items.RAW_GOLD)),
            Map.entry(Blocks.LAPIS_ORE, new ItemStack(Items.LAPIS_LAZULI, 4)),
            Map.entry(Blocks.DEEPSLATE_COAL_ORE, new ItemStack(Items.COAL, 2)),
            Map.entry(Blocks.DEEPSLATE_COPPER_ORE, new ItemStack(Items.RAW_COPPER, 3)),
            Map.entry(Blocks.DEEPSLATE_IRON_ORE, new ItemStack(Items.RAW_IRON)),
            Map.entry(Blocks.DEEPSLATE_GOLD_ORE, new ItemStack(Items.RAW_GOLD)),
            Map.entry(Blocks.DEEPSLATE_LAPIS_ORE, new ItemStack(Items.LAPIS_LAZULI, 4))
    );

    // =====================================================================
    // 3. NOUVELLE MAP : Blocs qui drop des BÂTONS (STICK)
    // Ajoutez ici tous les blocs qui doivent donner des sticks
    // =====================================================================
    private static final Map<Block, ItemStack> WOOD_DROPS_MAP = Map.ofEntries(
            // Exemple : Votre bloc de bois doré donne 2 bâtons
            Map.entry(ModBlocks.GOlDENWOOD.get(), new ItemStack(Items.STICK, 2)),

            // Exemple : Si vous voulez que le bois de chêne donne des bâtons
            Map.entry(Blocks.OAK_LOG, new ItemStack(Items.STICK, 4)),
            Map.entry(Blocks.SPRUCE_LOG, new ItemStack(Items.STICK, 4))
    );


    public SawItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        Block clickedBlock = state.getBlock();

        // On vérifie si le bloc est dans l'une de nos deux Maps de drops
        boolean isChiselable = CHISEL_DROPS_MAP.containsKey(clickedBlock);
        boolean isWood = WOOD_DROPS_MAP.containsKey(clickedBlock);

        if (!isChiselable && !isWood) {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;

            // On récupère le drop correspondant (soit dans la map classique, soit dans celle du bois)
            ItemStack drop = isChiselable ? CHISEL_DROPS_MAP.get(clickedBlock) : WOOD_DROPS_MAP.get(clickedBlock);

            // Si le bloc est dans CHISEL_MAP, on le transforme (ex: Minerai -> Pierre)
            // Sinon (pour le bois), on le casse complètement (transforme en AIR)
            if (CHISEL_MAP.containsKey(clickedBlock)) {
                level.setBlockAndUpdate(pos, CHISEL_MAP.get(clickedBlock).defaultBlockState());
            } else {
                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            }

            // Apparition du drop (bâtons ou minerais)
            if (drop != null) {
                Block.popResource(level, pos, drop.copy());
            }

            // Durabilité de la scie
            context.getItemInHand().hurtAndBreak(
                    1,
                    serverLevel,
                    context.getPlayer(),
                    item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND)
            );

            // Son
            level.playSound(null, pos, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(3, attacker, EquipmentSlot.MAINHAND);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.neoforged.neoforge.common.ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_AXE_ACTIONS.contains(itemAbility);
    }
}