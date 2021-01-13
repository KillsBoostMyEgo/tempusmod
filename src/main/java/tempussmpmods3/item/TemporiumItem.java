
package tempussmpmods3.item;

@TempusModElements.ModElement.Tag
public class TemporiumItem extends TempusModElements.ModElement {

	@ObjectHolder("tempus:temporium")
	public static final Item block = null;

	public TemporiumItem(TempusModElements instance) {
		super(instance, 22);

	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {

		public ItemCustom() {
			super(new Item.Properties().group(TempusItemGroup.tab).maxStackSize(64).rarity(Rarity.UNCOMMON));
			setRegistryName("temporium");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

	}

}
