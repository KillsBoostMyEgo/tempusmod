package tempussmpmods3.procedures;

import tempussmpmods3.enchantment.SmeltingEnchantment;

import tempussmpmods3.TempusModElements;

import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.EnchantmentHelper;

import java.util.Map;

@TempusModElements.ModElement.Tag
public class AutosmeltAttacherProcedure extends TempusModElements.ModElement {
	public AutosmeltAttacherProcedure(TempusModElements instance) {
		super(instance, 62);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				System.err.println("Failed to load dependency itemstack for procedure AutosmeltAttacher!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((((EnchantmentHelper.getEnchantmentLevel(SmeltingEnchantment.enchantment, (itemstack)) != 0)) == (false))) {
			((itemstack)).addEnchantment(SmeltingEnchantment.enchantment, (int) 1);
		}
	}
}
