
package tempussmpmods3.block;

import net.minecraft.block.material.Material;

@TempusModElements.ModElement.Tag
public class BlockEndionBlock extends TempusModElements.ModElement {

	@ObjectHolder("tempus:block_endion")
	public static final Block block = null;

	public BlockEndionBlock(TempusModElements instance) {
		super(instance, 123);

	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(TempusItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	public static class CustomBlock extends Block {

		public CustomBlock() {
			super(Block.Properties.create(Material.ROCK).sound(SoundType.GROUND).hardnessAndResistance(30f, 200f).lightValue(0));

			setRegistryName("block_endion");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {

			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}

	}

}
