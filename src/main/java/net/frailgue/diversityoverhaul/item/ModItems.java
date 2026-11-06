package net.frailgue.diversityoverhaul.item;

import  net.frailgue.diversityoverhaul.DiversityOverhaul;
import net.frailgue.diversityoverhaul.item.custom.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(DiversityOverhaul.MOD_ID);


    public static final DeferredItem<Item> CELESTINGOT = ITEMS.register("celestial_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> HELLINGOT = ITEMS.register("hell_ingot",
            () -> new Item(new Item.Properties()));






    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties()
                    .durability(1024).attributes(SwordItem.createAttributes(Tiers.IRON, 3, -2.4f))));

    public static final DeferredItem<Item> DCHISEL = ITEMS.register("diamond_chisel",
            () -> new DiamChiselItem(new Item.Properties()
                    .durability(8024).attributes(SwordItem.createAttributes(Tiers.DIAMOND, 3, -2.4f))));






    public static final DeferredItem<Item> RADISH = ITEMS.register("radish",
            () -> new Item(new Item.Properties().food(ModFoodProperties.RADISH)){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.diversity.radish"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<Item> ULTRA_COAL = ITEMS.register("ultra_coal",
            () -> new FuelItem(new Item.Properties(),800));

    public static final DeferredItem<Item> SHARDCOAL = ITEMS.register("shardcoal",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<SwordItem> CELEST_SWORD = ITEMS.register("celest_sword",
            () -> new SwordItem(ModToolTiers.CELEST, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.CELEST, 5, -2.4f))));
    public static final DeferredItem<PickaxeItem> CELEST_PICKAXE = ITEMS.register("celest_pickaxe",
            () -> new PickaxeItem(ModToolTiers.CELEST, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.CELEST, 1.0F, -2.8f))));
    public static final DeferredItem<ShovelItem> CELEST_SHOVEL = ITEMS.register("celest_shovel",
            () -> new ShovelItem(ModToolTiers.CELEST, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.CELEST, 1.5F, -3.0f))));
    public static final DeferredItem<AxeItem> CELEST_AXE = ITEMS.register("celest_axe",
            () -> new AxeItem(ModToolTiers.CELEST, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.CELEST, 6.0F, -3.2f))));
    public static final DeferredItem<HoeItem> CELEST_HOE = ITEMS.register("celest_hoe",
            () -> new HoeItem(ModToolTiers.CELEST, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.CELEST, 0F, -3.0f))));

    public static final DeferredItem<SawItem> SAW = ITEMS.register("saw",
            () -> new SawItem(new Item.Properties()
                    .durability(256).attributes(SwordItem.createAttributes(Tiers.IRON, 1, -0.5f))));


    public static final DeferredItem<SawItem> DSAW = ITEMS.register("diamond_saw",
            () -> new SawItem(new Item.Properties()
                    .durability(256).attributes(SwordItem.createAttributes(Tiers.DIAMOND, 1, -0.5f))));



    public static final DeferredItem<Item> GOLDWAXE = ITEMS.register("gold_waxe",
            () -> new GoldWaxe(new Item.Properties()));











    public static void register(IEventBus eventbus) {
        ITEMS.register(eventbus);
    }

}
