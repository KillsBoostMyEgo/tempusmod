
package tempussmpmods3.item;

@TempusModElements.ModElement.Tag
public class FoodCumSandwichItem extends TempusModElements.ModElement {

	@ObjectHolder("tempus:food_cum_sandwich")
	public static final Item block = null;

	public FoodCumSandwichItem(TempusModElements instance) {
		super(instance, 92);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}

	public static class FoodItemCustom extends Item {

		public FoodItemCustom() {
			super(new Item.Properties().group(ItemGroup.FOOD).maxStackSize(64).rarity(Rarity.UNCOMMON)
					.food((new Food.Builder()).hunger(8).saturation(1f)

							.build()));
			setRegistryName("food_cum_sandwich");
		}

		@Override
		public int getUseDuration(ItemStack stack) {
			return 64;
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.EAT;
		}

		@Override
		public ItemStack onItemUseFinish(ItemStack itemstack, World world, LivingEntity entity) {
			ItemStack retval = super.onItemUseFinish(itemstack, world, entity);

			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();

				$_dependencies.put("entity", entity);

				FoodCumSandwichFoodEatenProcedure.executeProcedure($_dependencies);
			}

			return retval;
		}

	}

}
