// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelEndionBoots extends EntityModel<Entity> {
	private final ModelRenderer R;
	private final ModelRenderer L;

	public ModelEndionBoots() {
		textureWidth = 64;
		textureHeight = 64;

		R = new ModelRenderer(this);
		R.setRotationPoint(-2.0F, 12.0F, 0.0F);
		R.setTextureOffset(19, 19).addBox(-2.5F, 6.0F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, false);
		R.setTextureOffset(0, 12).addBox(-3.0F, 6.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

		L = new ModelRenderer(this);
		L.setRotationPoint(2.0F, 12.0F, 0.0F);
		L.setTextureOffset(19, 19).addBox(-2.5F, 6.0F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, false);
		L.setTextureOffset(0, 12).addBox(-3.0F, 6.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		// previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		R.render(matrixStack, buffer, packedLight, packedOverlay);
		L.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}