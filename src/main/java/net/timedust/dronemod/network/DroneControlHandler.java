package net.timedust.dronemod.network;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.timedust.dronemod.entity.custom.AbstractDroneEntity;

public class DroneControlHandler {
    // Храним начальную позицию игрока для возврата
    private static double originalX;
    private static double originalY;
    private static double originalZ;
    private static float originalYaw;
    private static float originalPitch;
    private static boolean isControllingDrone = false;
    private static int currentDroneId = -1;
    // Добавляем флаг, чтобы предотвратить телепортацию игрока обратно
    private static boolean isCameraLocked = false;

    public static void activateDroneControl(int droneId) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) return;

        Entity entity = minecraft.level.getEntity(droneId);
        if (!(entity instanceof AbstractDroneEntity drone)) return;

        // Сохраняем позицию игрока только один раз при активации
        if (!isControllingDrone) {
            originalX = minecraft.player.getX();
            originalY = minecraft.player.getY();
            originalZ = minecraft.player.getZ();
            originalYaw = minecraft.player.getYRot();
            originalPitch = minecraft.player.getXRot();
        }

        // Активируем режим FPV
        isControllingDrone = true;
        currentDroneId = droneId;
        isCameraLocked = true;
    }

    public static void deactivateDroneControl() {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) return;

        // Сбрасываем флаги
        isControllingDrone = false;
        currentDroneId = -1;
        isCameraLocked = false;

        // Отправляем пакет на сервер для возврата игрока
        ModNetwork.sendToServer(new DroneExitControlPacket());
    }

    public static boolean isControllingDrone() {
        return isControllingDrone;
    }

    public static int getCurrentDroneId() {
        return currentDroneId;
    }

    public static boolean isCameraLocked() {
        return isCameraLocked;
    }
}