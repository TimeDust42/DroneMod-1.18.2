package net.timedust.dronemod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.timedust.dronemod.DroneMod;

@Mod.EventBusSubscriber(modid = DroneMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DroneHud extends GuiComponent {
    private static final ResourceLocation HUD = new ResourceLocation(DroneMod.MOD_ID, "textures/gui/drone_hud.png");

    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) return;

        if (DroneCameraController.isActive()) {
            renderDroneHud(event.getMatrixStack());
        }
    }

    private static void renderDroneHud(PoseStack matrixStack) {
        Minecraft minecraft = Minecraft.getInstance();
        int width = minecraft.getWindow().getGuiScaledWidth();
        int height = minecraft.getWindow().getGuiScaledHeight();

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.7F);
        RenderSystem.setShaderTexture(0, HUD);

        // Рисуем HUD по центру экрана
        int hudWidth = 200;
        int hudHeight = 200;
        int x = (width - hudWidth) / 2;
        int y = (height - hudHeight) / 2;

        blit(matrixStack, x, y, 0, 0, hudWidth, hudHeight, 256, 256);

        // Добавляем текст с инструкциями
        minecraft.font.draw(matrixStack, "Drone Control Mode - Press E to exit", width / 2 - 100, 10, 0xFFFFFF);
    }
}