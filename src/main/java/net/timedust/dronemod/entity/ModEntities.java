package net.timedust.dronemod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.timedust.dronemod.DroneMod;
import net.timedust.dronemod.entity.custom.ScoutDroneEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, DroneMod.MOD_ID);

    public static final RegistryObject<EntityType<ScoutDroneEntity>> SCOUT_DRONE = ENTITIES.register(
            "scout_drone",
            () -> EntityType.Builder.of(ScoutDroneEntity::new, MobCategory.MISC)
                    .sized(1.0f, 1.0f)
                    .clientTrackingRange(8)
                    .build("scout_drone")
    );

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
