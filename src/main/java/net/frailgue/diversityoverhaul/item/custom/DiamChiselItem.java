package net.frailgue.diversityoverhaul.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Map;


public class DiamChiselItem extends Item {
    private static final Map<Block, Block> DCHISEL_MAP =
            Map.ofEntries(

                    Map.entry(Blocks.COAL_ORE, Blocks.STONE),
                    Map.entry(Blocks.COPPER_ORE, Blocks.STONE),
                    Map.entry(Blocks.IRON_ORE, Blocks.STONE),
                    Map.entry(Blocks.LAPIS_ORE, Blocks.STONE),
                    Map.entry(Blocks.GOLD_ORE, Blocks.STONE),
                    Map.entry(Blocks.REDSTONE_ORE, Blocks.STONE),
                    Map.entry(Blocks.EMERALD_ORE, Blocks.STONE),
                    Map.entry(Blocks.DIAMOND_ORE, Blocks.STONE),
                    Map.entry(Blocks.DEEPSLATE_COAL_ORE, Blocks.DEEPSLATE),
                    Map.entry(Blocks.DEEPSLATE_COPPER_ORE, Blocks.DEEPSLATE),
                    Map.entry(Blocks.DEEPSLATE_IRON_ORE, Blocks.DEEPSLATE),
                    Map.entry(Blocks.DEEPSLATE_LAPIS_ORE, Blocks.DEEPSLATE),
                    Map.entry(Blocks.DEEPSLATE_GOLD_ORE, Blocks.DEEPSLATE),
                    Map.entry(Blocks.DEEPSLATE_REDSTONE_ORE, Blocks.DEEPSLATE),
                    Map.entry(Blocks.DEEPSLATE_EMERALD_ORE, Blocks.DEEPSLATE),
                    Map.entry(Blocks.DEEPSLATE_DIAMOND_ORE, Blocks.DEEPSLATE)


            );



    private static final Map<Block, ItemStack> DCHISEL_DROPS_MAP =
            Map.ofEntries(
                    Map.entry(Blocks.COAL_ORE, new ItemStack(Items.COAL, 2)),
                    Map.entry(Blocks.COPPER_ORE, new ItemStack(Items.RAW_COPPER, 3)),
                    Map.entry(Blocks.IRON_ORE, new ItemStack(Items.RAW_IRON)),
                    Map.entry(Blocks.GOLD_ORE, new ItemStack(Items.RAW_GOLD)),
                    Map.entry(Blocks.LAPIS_ORE, new ItemStack(Items.LAPIS_LAZULI, 4)),
                    Map.entry(Blocks.REDSTONE_ORE, new ItemStack(Items.REDSTONE, 4)),
                    Map.entry(Blocks.EMERALD_ORE, new ItemStack(Items.EMERALD)),
                    Map.entry(Blocks.DIAMOND_ORE, new ItemStack(Items.DIAMOND)),
                    Map.entry(Blocks.DEEPSLATE_COAL_ORE, new ItemStack(Items.COAL, 2)),
                    Map.entry(Blocks.DEEPSLATE_COPPER_ORE, new ItemStack(Items.RAW_COPPER, 3)),
                    Map.entry(Blocks.DEEPSLATE_IRON_ORE, new ItemStack(Items.RAW_IRON)),
                    Map.entry(Blocks.DEEPSLATE_GOLD_ORE, new ItemStack(Items.RAW_GOLD)),
                    Map.entry(Blocks.DEEPSLATE_LAPIS_ORE, new ItemStack(Items.LAPIS_LAZULI, 4)),
                    Map.entry(Blocks.DEEPSLATE_REDSTONE_ORE, new ItemStack(Items.REDSTONE, 4)),
                    Map.entry(Blocks.DEEPSLATE_EMERALD_ORE, new ItemStack(Items.EMERALD)),
                    Map.entry(Blocks.DEEPSLATE_DIAMOND_ORE, new ItemStack(Items.DIAMOND))
            );


    public DiamChiselItem(Properties properties) {
        super(properties);
    }






    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        BlockState state = level.getBlockState(pos);
        Block clickedBlock = state.getBlock();

        if (!DCHISEL_MAP.containsKey(clickedBlock)) {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide()) {

            ServerLevel serverLevel = (ServerLevel) level;

            // Récupération du drop correspondant au minerai
            ItemStack drop = DCHISEL_DROPS_MAP.get(clickedBlock);

// Transformation du minerai
            level.setBlockAndUpdate(pos,
                    DCHISEL_MAP.get(clickedBlock).defaultBlockState());

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
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.diversity.chisel.shift_down"));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.diversity.chisel"));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.neoforged.neoforge.common.ItemAbility itemAbility) {
        return net.neoforged.neoforge.common.ItemAbilities.DEFAULT_SWORD_ACTIONS.contains(itemAbility);
    }



}

