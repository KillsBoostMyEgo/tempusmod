
package tempussmpmods3.item;

@TempusModElements.ModElement.Tag
public class MymomItem extends TempusModElements.ModElement {

	@ObjectHolder("tempus:mymom")
	public static final Item block = null;

	public MymomItem(TempusModElements instance) {
		super(instance, 22);

	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {

		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("mymom");
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
