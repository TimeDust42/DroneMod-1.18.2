package net.timedust.dronemod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.timedust.dronemod.entity.custom.AbstractDroneEntity;

import java.util.function.Supplier;

public class DroneExitControlPacket {

    public DroneExitControlPacket() {
        // Пустой конструктор
    }

    public static void encode(DroneExitControlPacket packet, FriendlyByteBuf buffer) {
        // Ничего не кодируем, пакет пустой
    }

    public static DroneExitControlPacket decode(FriendlyByteBuf buffer) {
        return new DroneExitControlPacket();
    }

    public static void handle(DroneExitControlPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                // Находим дрон, которым управляет игрок, и отключаем управление
                player.getLevel().getEntities(player, player.getBoundingBox().inflate(20))
                        .stream()
                        .filter(entity -> entity instanceof AbstractDroneEntity)
                        .map(entity -> (AbstractDroneEntity)entity)
                        .filter(drone -> player.getUUID().equals(drone.getControllingPlayer()))
                        .forEach(drone -> drone.setBeingControlled(false));
            }
        });
        context.setPacketHandled(true);
    }
}