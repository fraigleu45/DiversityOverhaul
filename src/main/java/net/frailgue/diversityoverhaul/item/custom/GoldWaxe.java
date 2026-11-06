package net.frailgue.diversityoverhaul.item.custom;

import net.frailgue.diversityoverhaul.block.ModBlocks;
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


public class GoldWaxe extends Item {
    private static final Map<Block, Block> GOLDWAXE_MAP =
            Map.ofEntries(

                    Map.entry(Blocks.STRIPPED_DARK_OAK_WOOD, ModBlocks.GOlDENWOOD.get())




            );






    public GoldWaxe(Properties properties) {
        super(properties);
    }






    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        BlockState state = level.getBlockState(pos);
        Block clickedBlock = state.getBlock();

        if (!GOLDWAXE_MAP.containsKey(clickedBlock)) {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide()) {

            ServerLevel serverLevel = (ServerLevel) level;


// Transformation du minerai
            level.setBlockAndUpdate(pos,
                    GOLDWAXE_MAP.get(clickedBlock).defaultBlockState());





            // Son
            level.playSound(
                    null,
                    pos,
                    SoundEvents.HONEYCOMB_WAX_ON,
                    SoundSource.BLOCKS
            );

            context.getItemInHand().shrink(1);

        }

        return InteractionResult.SUCCESS;
    }













}

