package tempussmpmods3.procedures;

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

		if ((((EnchantmentHelper.getEnchantmentLevel(AutosmeltenchEnchantment.enchantment, (itemstack)) != 0)) == (false))) {
			((itemstack)).addEnchantment(AutosmeltenchEnchantment.enchantment, (int) 1);
		}

	}

}
