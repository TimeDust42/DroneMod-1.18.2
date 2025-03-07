package net.timedust.dronemod.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Collections;
import java.util.UUID;

public abstract class AbstractDroneEntity extends LivingEntity {

    private UUID droneId;

    protected AbstractDroneEntity(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
        this.droneId = UUID.randomUUID();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);

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