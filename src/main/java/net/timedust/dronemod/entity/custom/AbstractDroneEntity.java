package net.timedust.dronemod.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.timedust.dronemod.network.DroneControlPacket;
import net.timedust.dronemod.network.ModNetwork;

import java.util.Collections;
import java.util.UUID;

public abstract class AbstractDroneEntity extends LivingEntity {

    private UUID droneId;
    private boolean isBeingControlled = false;
    private UUID controllingPlayer = null;

    protected AbstractDroneEntity(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
        this.droneId = UUID.randomUUID();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);

    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if (hand == InteractionHand.MAIN_HAND && !this.level.isClientSide) {
            // Если дрон уже управляется, игнорируем взаимодействие
            if (isBeingControlled) {
                return InteractionResult.FAIL;
            }

            // Активируем режим управления
            isBeingControlled = true;
            controllingPlayer = player.getUUID();

            // Отправляем пакет клиенту для активации камеры
            if (player instanceof ServerPlayer serverPlayer) {
                ModNetwork.sendToPlayer(new DroneControlPacket(this.getId(), true), serverPlayer);
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    // Геттеры и сеттеры
    public boolean isBeingControlled() {
        return isBeingControlled;
    }

    public void setBeingControlled(boolean controlled) {
        this.isBeingControlled = controlled;
    }

    public UUID getControllingPlayer() {
        return controllingPlayer;
    }

    public void setControllingPlayer(UUID playerId) {
        this.controllingPlayer = playerId;
    }

    @Override
    public boolean isNoGravity() {
        return true;  // Явно указываем, что гравитация отключена
    }
    @Override
    public boolean isPushable() {
        return false;  // Полностью отключает физическое взаимодействие
    }
    @Override
    public void push(Entity entity) {
        // Пустой метод, чтобы предотвратить толкание
    }
    @Override
    protected void pushEntities() {
        // Пустой метод, чтобы предотвратить толкание других сущностей
    }
    @Override
    public boolean canBeCollidedWith() {
        return true;  // Можно столкнуться, но не отталкивать
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        // Дрон не носит броню
        return Collections.emptyList();
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot slot) {
        // Возвращаем пустой стек для всех слотов
        return ItemStack.EMPTY;
    }

    @Override
    public void setItemSlot(EquipmentSlot slot, ItemStack stack) {
        // Полностью игнорируем попытки установить предметы
    }

    @Override
    public HumanoidArm getMainArm() {
        // Дрон не имеет рук, возвращаем значение по умолчанию
        return HumanoidArm.RIGHT;
    }
}