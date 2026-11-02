package net.frailgue.diversityoverhaul.block;

import net.frailgue.diversityoverhaul.DiversityOverhaul;
import net.frailgue.diversityoverhaul.block.custom.CosmicBlock;
import net.frailgue.diversityoverhaul.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(DiversityOverhaul.MOD_ID);

public static final DeferredBlock<Block> CELESTBLOCK = registerBlock( "celestial_block",
        () -> new Block(BlockBehaviour.Properties.of()
                .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)                 ));


    public static final DeferredBlock<Block> HELLBLOCK = registerBlock( "hell_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).sound(SoundType.AMETHYST)                 ));

    public static final DeferredBlock<Block> COSMICBLOCK = registerBlock( "cosmic_block",
            () -> new CosmicBlock(BlockBehaviour.Properties.of().strength(2f)));




private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
    DeferredBlock<T> toReturn = BLOCKS.register(name, block);
    registerBlockItem(name, toReturn);
    return toReturn;
}


    private static <T extends Block > void registerBlockItem(String name, DeferredBlock<T> block) {
       ModItems.ITEMS.register(name,() -> new BlockItem(block.get(), new Item.Properties() ));
    }




    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }


}
