package net.timedust.dronemod.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.timedust.dronemod.DroneMod;
import net.timedust.dronemod.entity.custom.ScoutDroneEntity;

public class ScoutDroneModel<T extends ScoutDroneEntity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DroneMod.MOD_ID, "scout_drone"), "main");
	private final ModelPart antena;
	private final ModelPart camera;
	private final ModelPart krilo_two;
	private final ModelPart krilo_four;
	private final ModelPart corpus;
	private final ModelPart krilo_one;
	private final ModelPart krilo_three;

	public ScoutDroneModel(ModelPart root) {
		this.antena = root.getChild("antena");
		this.camera = root.getChild("camera");
		this.krilo_two = root.getChild("krilo_two");
		this.krilo_four = root.getChild("krilo_four");
		this.corpus = root.getChild("corpus");
		this.krilo_one = root.getChild("krilo_one");
		this.krilo_three = root.getChild("krilo_three");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition antena = partdefinition.addOrReplaceChild("antena", CubeListBuilder.create().texOffs(24, 44).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(44, 28).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, 4.0F));

		PartDefinition camera = partdefinition.addOrReplaceChild("camera", CubeListBuilder.create(), PartPose.offset(-7.0F, 17.0F, 0.0F));

		PartDefinition cube_r1 = camera.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 28).addBox(-2.0F, -2.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, -6.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition krilo_two = partdefinition.addOrReplaceChild("krilo_two", CubeListBuilder.create().texOffs(32, 4).addBox(-3.8284F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(12, 32).addBox(-2.4142F, -1.0F, 0.4142F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(28, 32).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.2426F, 16.0F, 8.6569F));

		PartDefinition cube_r2 = krilo_two.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(20, 32).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4142F, 0.0F, 1.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r3 = krilo_two.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(32, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4142F, 0.0F, -1.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r4 = krilo_two.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(28, 28).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4142F, 0.0F, -1.4142F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r5 = krilo_two.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(20, 28).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4142F, 0.0F, 1.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r6 = krilo_two.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(12, 28).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4142F, 0.0F, -1.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r7 = krilo_two.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 8).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1213F, 0.0F, -4.5355F, 0.0F, -0.7854F, 0.0F));

		PartDefinition krilo_four = partdefinition.addOrReplaceChild("krilo_four", CubeListBuilder.create().texOffs(8, 36).addBox(-2.4142F, -1.0F, 0.4142F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(24, 36).addBox(-1.0F, -1.0F, 1.8284F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(32, 36).addBox(0.4142F, -1.0F, 0.4142F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(8.6569F, 16.0F, 7.2426F));

		PartDefinition cube_r8 = krilo_four.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 38).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r9 = krilo_four.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(36, 32).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.4142F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r10 = krilo_four.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(36, 28).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 2.4142F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r11 = krilo_four.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(16, 36).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 2.4142F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r12 = krilo_four.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 34).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.4142F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r13 = krilo_four.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5355F, 0.0F, -3.1213F, 0.0F, 0.7854F, 0.0F));

		PartDefinition corpus = partdefinition.addOrReplaceChild("corpus", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -2.0F, -2.0F, 12.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition krilo_one = partdefinition.addOrReplaceChild("krilo_one", CubeListBuilder.create().texOffs(40, 8).addBox(-3.8284F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(16, 40).addBox(-2.4142F, -1.0F, -2.4142F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(40, 20).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.2426F, 16.0F, -8.6569F));

		PartDefinition cube_r14 = krilo_one.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4142F, 0.0F, -1.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r15 = krilo_one.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(40, 12).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4142F, 0.0F, -1.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r16 = krilo_one.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(8, 40).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4142F, 0.0F, 1.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r17 = krilo_one.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(40, 4).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4142F, 0.0F, 1.4142F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r18 = krilo_one.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(40, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4142F, 0.0F, 1.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r19 = krilo_one.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(20, 8).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1213F, 0.0F, 4.5355F, 0.0F, 0.7854F, 0.0F));

		PartDefinition krilo_three = partdefinition.addOrReplaceChild("krilo_three", CubeListBuilder.create().texOffs(24, 40).addBox(-1.4142F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(32, 40).addBox(0.0F, -1.0F, -3.4142F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(40, 40).addBox(1.4142F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(7.6569F, 16.0F, -7.6569F));

		PartDefinition cube_r20 = krilo_three.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(16, 44).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r21 = krilo_three.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(8, 44).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.4142F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r22 = krilo_three.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 42).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r23 = krilo_three.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(40, 36).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, -2.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r24 = krilo_three.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(40, 24).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r25 = krilo_three.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(20, 18).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5355F, 0.0F, 3.5355F, 0.0F, -0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		antena.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		camera.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		krilo_two.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		krilo_four.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		corpus.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		krilo_one.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		krilo_three.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(T t, float v, float v1, float v2, float v3, float v4) {

	}
}