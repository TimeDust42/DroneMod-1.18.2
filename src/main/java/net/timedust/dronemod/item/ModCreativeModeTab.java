package net.timedust.dronemod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab DRONE_TAB = new CreativeModeTab("dronetab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.ALUMINIUM_INGOT.get());
        }
    };
}
