package net.frailgue.diversityoverhaul.item;

import net.frailgue.diversityoverhaul.DiversityOverhaul;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(DiversityOverhaul.MOD_ID);


    public static final DeferredItem<Item> CELESTINGOT = ITEMS.register("celestial_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> HELLINGOT = ITEMS.register("hell_ingot",
            () -> new Item(new Item.Properties()));





    public static void register(IEventBus eventbus) {
        ITEMS.register(eventbus);
    }

}
