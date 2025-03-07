package net.timedust.dronemod.event;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.timedust.dronemod.DroneMod;
import net.timedust.dronemod.client.DroneCameraController;
import net.timedust.dronemod.entity.custom.AbstractDroneEntity;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = DroneMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DroneClientEvents {
    private static final Minecraft minecraft = Minecraft.getInstance();
    private static double lastMouseX = 0;
    private static double lastMouseY = 0;
    private static boolean firstMove = true;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END || !DroneCameraController.isActive()) return;

        Entity entity = minecraft.level.getEntity(DroneCameraController.getDroneEntityId());
        if (!(entity instanceof AbstractDroneEntity drone)) return;

        // Обработка движения дрона
        handleDroneMovement(drone);

        // Обновление камеры
        DroneCameraController.updateCamera();
    }

    @SubscribeEvent
    public static void onMouseInput(InputEvent.MouseInputEvent event) {
        if (!DroneCameraController.isActive()) return;

        Entity entity = minecraft.level.getEntity(DroneCameraController.getDroneEntityId());
        if (!(entity instanceof AbstractDroneEntity drone)) return;

        // Получаем текущие координаты мыши
        double currentMouseX = minecraft.mouseHandler.xpos();
        double currentMouseY = minecraft.mouseHandler.ypos();

        // Пропускаем первое движение мыши
        if (firstMove) {
            firstMove = false;
            lastMouseX = currentMouseX;
            lastMouseY = currentMouseY;
            return;
        }

        // Вычисляем изменение положения мыши
        double deltaX = currentMouseX - lastMouseX;
        double deltaY = currentMouseY - lastMouseY;

        // Обновляем последние известные координаты мыши
        lastMouseX = currentMouseX;
        lastMouseY = currentMouseY;

        // Применяем чувствительность
        double sensitivity = minecraft.options.sensitivity * 0.5F;
        deltaX *= sensitivity;
        deltaY *= sensitivity;

        // Обновляем поворот дрона
        drone.setYRot((float) (drone.getYRot() + deltaX));
        float newXRot = (float) (drone.getXRot() + deltaY);
        // Ограничиваем вертикальный поворот
        newXRot = Math.max(-90.0F, Math.min(90.0F, newXRot));
        drone.setXRot(newXRot);
    }

    private static void handleDroneMovement(AbstractDroneEntity drone) {
        float moveSpeed = 0.5F;
        Vec3 movement = Vec3.ZERO;

        // Получаем направление взгляда дрона
        Vec3 lookVec = drone.getLookAngle();
        Vec3 rightVec = new Vec3(-lookVec.z, 0, lookVec.x).normalize();

        // Движение вперед/назад
        if (minecraft.options.keyUp.isDown()) {
            movement = movement.add(lookVec.multiply(moveSpeed, moveSpeed, moveSpeed));
        }
        if (minecraft.options.keyDown.isDown()) {
            movement = movement.add(lookVec.multiply(-moveSpeed, -moveSpeed, -moveSpeed));
        }

        // Движение влево/вправо
        if (minecraft.options.keyLeft.isDown()) {
            movement = movement.add(rightVec.multiply(-moveSpeed, -moveSpeed, -moveSpeed));
        }
        if (minecraft.options.keyRight.isDown()) {
            movement = movement.add(rightVec.multiply(moveSpeed, moveSpeed, moveSpeed));
        }

        // Движение вверх/вниз
        if (minecraft.options.keyJump.isDown()) {
            movement = movement.add(0, moveSpeed, 0);
        }
        if (minecraft.options.keyShift.isDown()) {
            movement = movement.add(0, -moveSpeed, 0);
        }

        // Применяем движение к дрону
        drone.setDeltaMovement(movement);
    }

    // Метод для сброса состояния при выходе из режима управления дроном
    public static void resetControl() {
        firstMove = true;
        lastMouseX = 0;
        lastMouseY = 0;
    }
}