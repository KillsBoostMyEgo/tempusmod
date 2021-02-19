
package tempussmpmods3.enchantment;

import tempussmpmods3.item.ShovelNetheriteItem;
import tempussmpmods3.item.PickaxeNetheriteItem;
import tempussmpmods3.item.HoeNetheriteItem;
import tempussmpmods3.item.AxeNetheriteItem;

import tempussmpmods3.TempusModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantment;

@TempusModElements.ModElement.Tag
public class SmeltingEnchantment extends TempusModElements.ModElement {
	@ObjectHolder("tempus:smelting")
	public static final Enchantment enchantment = null;
	public SmeltingEnchantment(TempusModElements instance) {
		super(instance, 73);
	}

	@Override
	public void initElements() {
		elements.enchantments.add(() -> new CustomEnchantment(EquipmentSlotType.MAINHAND).setRegistryName("smelting"));
	}
	public static class CustomEnchantment extends Enchantment {
		public CustomEnchantment(EquipmentSlotType... slots) {
			super(Enchantment.Rarity.COMMON, EnchantmentType.ALL, slots);
		}

		@Override
		public int getMinLevel() {
			return 1;
		}

		@Override
		public int getMaxLevel() {
			return 1;
		}

		@Override
		public boolean canApplyAtEnchantingTable(ItemStack stack) {
			if (stack.getItem() == new ItemStack(PickaxeNetheriteItem.block, (int) (1)).getItem())
				return true;
			if (stack.getItem() == new ItemStack(AxeNetheriteItem.block, (int) (1)).getItem())
				return true;
			if (stack.getItem() == new ItemStack(ShovelNetheriteItem.block, (int) (1)).getItem())
				return true;
			if (stack.getItem() == new ItemStack(HoeNetheriteItem.block, (int) (1)).getItem())
				return true;
			return false;
		}

		@Override
		public boolean isTreasureEnchantment() {
			return false;
		}

		@Override
		public boolean isCurse() {
			return false;
		}

		@Override
		public boolean isAllowedOnBooks() {
			return true;
		}
	}
}
