
package tempussmpmods3.item;

import tempussmpmods3.itemgroup.TempusItemGroup;

import tempussmpmods3.TempusModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.BipedModel;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@TempusModElements.ModElement.Tag
public class EndionBootsItem extends TempusModElements.ModElement {
	@ObjectHolder("tempus:endion_boots_helmet")
	public static final Item helmet = null;
	@ObjectHolder("tempus:endion_boots_chestplate")
	public static final Item body = null;
	@ObjectHolder("tempus:endion_boots_leggings")
	public static final Item legs = null;
	@ObjectHolder("tempus:endion_boots_boots")
	public static final Item boots = null;
	public EndionBootsItem(TempusModElements instance) {
		super(instance, 126);
	}

	@Override
	public void initElements() {
		IArmorMaterial armormaterial = new IArmorMaterial() {
			@Override
			public int getDurability(EquipmentSlotType slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 40;
			}

			@Override
			public int getDamageReductionAmount(EquipmentSlotType slot) {
				return new int[]{2, 5, 6, 2}[slot.getIndex()];
			}

			@Override
			public int getEnchantability() {
				return 10;
			}

			@Override
			public net.minecraft.util.SoundEvent getSoundEvent() {
				return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_generic"));
			}

			@Override
			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(EndionNuggetItem.block, (int) (1)));
			}

			@OnlyIn(Dist.CLIENT)
			@Override
			public String getName() {
				return "endion_boots";
			}

			@Override
			public float getToughness() {
				return 2f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0f;
			}
		};
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.FEET, new Item.Properties().group(TempusItemGroup.tab)) {
			@Override
			@OnlyIn(Dist.CLIENT)
			public BipedModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlotType slot, BipedModel defaultModel) {
				BipedModel armorModel = new BipedModel(1);
				armorModel.bipedLeftLeg = new ModelEndionBoots().L;
				armorModel.bipedRightLeg = new ModelEndionBoots().R;
				armorModel.isSneak = living.isSneaking();
				armorModel.isSitting = defaultModel.isSitting;
				armorModel.isChild = living.isChild();
				return armorModel;
			}

			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "tempus:textures/texture.png";
			}
		}.setRegistryName("endion_boots_boots"));
	}
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
		public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			// previously the render function, render code was moved to a method below
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			R.render(matrixStack, buffer, packedLight, packedOverlay);
			L.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}
}
