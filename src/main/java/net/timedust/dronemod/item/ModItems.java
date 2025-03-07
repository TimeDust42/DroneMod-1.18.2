package net.timedust.dronemod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.timedust.dronemod.DroneMod;
import net.timedust.dronemod.item.custom.DroneController;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DroneMod.MOD_ID);

    public static final RegistryObject<Item> ALUMINIUM_INGOT = ITEMS.register("aluminium_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DRONE_TAB)));

    public static final RegistryObject<Item> DRONE_CONTROLLER = ITEMS.register("drone_controller",
            () -> new DroneController(new Item.Properties().tab(ModCreativeModeTab.DRONE_TAB).stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
