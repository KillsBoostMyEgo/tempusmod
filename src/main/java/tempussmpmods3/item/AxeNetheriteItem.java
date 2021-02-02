
package tempussmpmods3.item;

import tempussmpmods3.TempusModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.item.AxeItem;

@TempusModElements.ModElement.Tag
public class AxeNetheriteItem extends TempusModElements.ModElement {
	@ObjectHolder("tempus:axe_netherite")
	public static final Item block = null;
	public AxeNetheriteItem(TempusModElements instance) {
		super(instance, 46);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new AxeItem(new IItemTier() {
			public int getMaxUses() {
				return 2031;
			}

			public float getEfficiency() {
				return 9f;
			}

			public float getAttackDamage() {
				return 8f;
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
		}.setRegistryName("axe_netherite"));
	}
}
