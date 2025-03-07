package net.timedust.dronemod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.timedust.dronemod.client.DroneCameraController;
import java.util.function.Supplier;

public class DroneControlPacket {
    private final int droneId;
    private final boolean activate;

    public DroneControlPacket(int droneId, boolean activate) {
        this.droneId = droneId;
        this.activate = activate;
    }

    public static void encode(DroneControlPacket packet, FriendlyByteBuf buffer) {
        buffer.writeInt(packet.droneId);
        buffer.writeBoolean(packet.activate);
    }

    public static DroneControlPacket decode(FriendlyByteBuf buffer) {
        return new DroneControlPacket(buffer.readInt(), buffer.readBoolean());
    }

    public static void handle(DroneControlPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if (packet.activate) {
                DroneCameraController.activateDroneCamera(packet.droneId);
            } else {
                DroneCameraController.deactivateDroneCamera();
            }
        });
        context.setPacketHandled(true);
    }
}