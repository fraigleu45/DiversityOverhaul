package net.frailgue.diversityoverhaul.item.custom;

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


public class DiamSawItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP =
            Map.ofEntries(

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



    private static final Map<Block, ItemStack> CHISEL_DROPS_MAP =
            Map.ofEntries(
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


    public DiamSawItem(Properties properties) {
        super(properties);
    }






    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        BlockState state = level.getBlockState(pos);
        Block clickedBlock = state.getBlock();

        if (!CHISEL_MAP.containsKey(clickedBlock)) {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide()) {

            ServerLevel serverLevel = (ServerLevel) level;

            // Récupération du drop correspondant au minerai
            ItemStack drop = CHISEL_DROPS_MAP.get(clickedBlock);

// Transformation du minerai
            level.setBlockAndUpdate(pos,
                    CHISEL_MAP.get(clickedBlock).defaultBlockState());

// Apparition du drop
            if (drop != null) {
                Block.popResource(level, pos, drop.copy());
            }


            // Durabilité du Chisel
            context.getItemInHand().hurtAndBreak(
                    1,
                    serverLevel,
                    context.getPlayer(),
                    item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND)
            );

            // Son
            level.playSound(
                    null,
                    pos,
                    SoundEvents.GRINDSTONE_USE,
                    SoundSource.BLOCKS
            );
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

