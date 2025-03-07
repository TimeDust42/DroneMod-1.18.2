package net.timedust.dronemod.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.timedust.dronemod.DroneMod;

public class ModNetwork {
    private static final String PROTOCOL_VERSION = "1.0";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(DroneMod.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int packetId = 0;

    public static void register() {
        INSTANCE.registerMessage(
                packetId++,
                DroneControlPacket.class,
                DroneControlPacket::encode,
                DroneControlPacket::decode,
                DroneControlPacket::handle
        );

        INSTANCE.registerMessage(
                packetId++,
                DroneExitControlPacket.class,
                DroneExitControlPacket::encode,
                DroneExitControlPacket::decode,
                DroneExitControlPacket::handle
        );
    }

    public static void sendToPlayer(Object packet, Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            INSTANCE.sendTo(packet, serverPlayer.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    public static void sendToServer(Object packet) {
        INSTANCE.sendToServer(packet);
    }
}