package net.timedust.dronemod.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.timedust.dronemod.DroneMod;
import net.timedust.dronemod.entity.client.model.ScoutDroneModel;
import net.timedust.dronemod.entity.custom.ScoutDroneEntity;

public class ScoutDroneRenderer extends LivingEntityRenderer<ScoutDroneEntity, ScoutDroneModel<ScoutDroneEntity>> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(DroneMod.MOD_ID, "textures/entity/scout_drone.png");

    public ScoutDroneRenderer(EntityRendererProvider.Context context) {
        super(context, new ScoutDroneModel<>(context.bakeLayer(ScoutDroneModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(ScoutDroneEntity entity) { // Исправлен тип параметра
        return TEXTURE;
    }

    @Override
    public void render(ScoutDroneEntity entity, float yaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight) {
        // Удалите дублирующий код, если используете стандартный рендер LivingEntity
        super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
    }
}