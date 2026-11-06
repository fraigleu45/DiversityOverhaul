package net.frailgue.diversityoverhaul.block;

import net.frailgue.diversityoverhaul.DiversityOverhaul;
import net.frailgue.diversityoverhaul.block.custom.CosmicBlock;
import net.frailgue.diversityoverhaul.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
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



    public static final DeferredBlock<StairBlock> CELESTBLOCK_STAIRS = registerBlock("celestial_stairs",
            () -> new StairBlock(ModBlocks.CELESTBLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<SlabBlock> CELESTBLOCK_SLAB = registerBlock("celestial_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()));




    public static final DeferredBlock<PressurePlateBlock> CELESTBLOCK_PRESSURE_PLATE = registerBlock("celestial_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON,
                    BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<ButtonBlock> CELESTBLOCK_BUTTON = registerBlock("celestial_button",
            () -> new ButtonBlock(BlockSetType.IRON, 20,
                    BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().noCollission()));




    public static final DeferredBlock<FenceBlock> CELESTBLOCK_FENCE = registerBlock("celestial_fence",
            () -> new FenceBlock(
                    BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<FenceGateBlock> CELESTBLOCK_FENCE_GATE = registerBlock("celestial_fence_gate",
            () -> new FenceGateBlock(WoodType.ACACIA,
                    BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<WallBlock> CELESTBLOCK_WALL = registerBlock("celestial_wall",
            () -> new WallBlock(
                    BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()));




    public static final DeferredBlock<DoorBlock> CELESTBLOCK_DOOR = registerBlock("celestial_door",
            () -> new DoorBlock(BlockSetType.IRON,
                    BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().noOcclusion()));

    public static final DeferredBlock<TrapDoorBlock> CELESTBLOCK_TRAPDOOR = registerBlock("celestial_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.IRON,
                    BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().noOcclusion()));





    public static final DeferredBlock<Block> GOlDENWOOD = registerBlock( "golden_wood",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.WOOD)                 ));






















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
