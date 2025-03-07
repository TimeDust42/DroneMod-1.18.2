package net.timedust.dronemod;

import com.mojang.logging.LogUtils;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.timedust.dronemod.entity.ModEntities;
import net.timedust.dronemod.entity.client.model.ScoutDroneModel;
import net.timedust.dronemod.entity.client.renderer.ScoutDroneRenderer;
import net.timedust.dronemod.entity.custom.ScoutDroneEntity;
import net.timedust.dronemod.item.ModItems;
import org.slf4j.Logger;

@Mod(DroneMod.MOD_ID)
public class DroneMod {

    public static final String MOD_ID = "dronemod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public DroneMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModEntities.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::registerRenderers); // Добавлено
        eventBus.addListener(this::registerLayerDefinitions);
        eventBus.addListener(this::registerEntityAttributes);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.SCOUT_DRONE.get(), ScoutDroneEntity.createAttributes());
    }

    private void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.SCOUT_DRONE.get(), ScoutDroneRenderer::new);
    }

    // Регистрация слоя модели
    private void registerLayerDefinitions(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ScoutDroneModel.LAYER_LOCATION, ScoutDroneModel::createBodyLayer);
    }
}
