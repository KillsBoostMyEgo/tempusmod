
package tempussmpmods3.item;

import tempussmpmods3.TempusModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

@TempusModElements.ModElement.Tag
public class ShovelNetheriteItem extends TempusModElements.ModElement {
	@ObjectHolder("tempus:shovel_netherite")
	public static final Item block = null;
	public ShovelNetheriteItem(TempusModElements instance) {
		super(instance, 47);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ShovelItem(new IItemTier() {
			public int getMaxUses() {
				return 2031;
			}

			public float getEfficiency() {
				return 6f;
			}

			public float getAttackDamage() {
				return 4.5f;
			}

			public int getHarvestLevel() {
				return 69420;
			}

			public int getEnchantability() {
				return 15;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(ItemNetheriteIngotItem.block, (int) (1)),
						new ItemStack(ItemNetheriteScrapItem.block, (int) (1)));
			}
		}, 1, -3f, new Item.Properties().group(ItemGroup.TOOLS)) {
		}.setRegistryName("shovel_netherite"));
	}
}
