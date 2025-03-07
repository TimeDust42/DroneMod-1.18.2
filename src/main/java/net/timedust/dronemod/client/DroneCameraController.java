package net.timedust.dronemod.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.CameraType;
import net.minecraft.world.entity.Entity;
import net.timedust.dronemod.entity.custom.AbstractDroneEntity;
import net.minecraft.world.phys.Vec3;

public class DroneCameraController {
    private static boolean isActive = false;
    private static int droneCameraEntityId = -1;
    private static CameraType originalCameraType;
    private static Vec3 originalPlayerPos;
    private static float originalYaw;
    private static float originalPitch;

    public static void activateDroneCamera(int droneId) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null || minecraft.level == null) return;

        Entity entity = minecraft.level.getEntity(droneId);
        if (!(entity instanceof AbstractDroneEntity)) return;

        // Сохраняем оригинальные данные
        originalCameraType = minecraft.options.getCameraType();
        originalPlayerPos = minecraft.player.position();
        originalYaw = minecraft.player.getYRot();
        originalPitch = minecraft.player.getXRot();

        // Устанавливаем камеру от третьего лица чтобы вообще не использовать модель игрока
        minecraft.options.setCameraType(CameraType.THIRD_PERSON_BACK);

        droneCameraEntityId = droneId;
        isActive = true;
    }

    public static void deactivateDroneCamera() {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) return;

        // Восстанавливаем настройки
        minecraft.options.setCameraType(originalCameraType);

        // Сбрасываем флаги
        droneCameraEntityId = -1;
        isActive = false;
    }

    public static boolean isActive() {
        return isActive;
    }

    public static int getDroneEntityId() {
        return droneCameraEntityId;
    }

    // Обновление камеры без перемещения игрока
    public static void updateCamera() {
        Minecraft minecraft = Minecraft.getInstance();
        if (!isActive || minecraft.player == null || minecraft.level == null) return;

        Entity entity = minecraft.level.getEntity(droneCameraEntityId);
        if (!(entity instanceof AbstractDroneEntity drone)) {
            deactivateDroneCamera();
            return;
        }

        // Принудительно устанавливаем камеру на дрон
        minecraft.setCameraEntity(drone);
    }
}