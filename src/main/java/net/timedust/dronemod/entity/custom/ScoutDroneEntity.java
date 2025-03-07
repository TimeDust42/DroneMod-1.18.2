package net.timedust.dronemod.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ScoutDroneEntity extends AbstractDroneEntity {

    public ScoutDroneEntity(EntityType<? extends AbstractDroneEntity> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier createAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)  // Здоровье дрона
                .add(Attributes.MOVEMENT_SPEED, 0.4D)  // Скорость передвижения
                .add(Attributes.FLYING_SPEED, 0.6D)  // Скорость полёта
                .add(Attributes.ATTACK_DAMAGE, 2.0D)  // Урон при столкновении
                .build();
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if (hand == InteractionHand.MAIN_HAND && player.getItemInHand(hand).isEmpty()) {
            InteractionResult result = super.interact(player, hand);
            if (result == InteractionResult.SUCCESS) {
                return InteractionResult.SUCCESS;
            }
        }
        return super.interact(player, hand);
    }

    @Override
    public void tick() {
        super.tick();
        // Логика обновления дрона
    }
}