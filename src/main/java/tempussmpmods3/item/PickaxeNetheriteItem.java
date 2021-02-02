
package tempussmpmods3.item;

import tempussmpmods3.TempusModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

@TempusModElements.ModElement.Tag
public class PickaxeNetheriteItem extends TempusModElements.ModElement {
	@ObjectHolder("tempus:pickaxe_netherite")
	public static final Item block = null;
	public PickaxeNetheriteItem(TempusModElements instance) {
		super(instance, 45);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new PickaxeItem(new IItemTier() {
			public int getMaxUses() {
				return 2031;
			}

			public float getEfficiency() {
				return 6f;
			}

			public float getAttackDamage() {
				return 4f;
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
		}, 1, -2.8f, new Item.Properties().group(ItemGroup.TOOLS)) {
		}.setRegistryName("pickaxe_netherite"));
	}
}
