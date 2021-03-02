
package tempussmpmods3.block;

import net.minecraft.block.material.Material;

@TempusModElements.ModElement.Tag
public class BlockEndionBlocjkBlock extends TempusModElements.ModElement {

	@ObjectHolder("tempus:block_endion_blocjk")
	public static final Block block = null;

	public BlockEndionBlocjkBlock(TempusModElements instance) {
		super(instance, 90);

	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(TempusItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	public static class CustomBlock extends Block {

		public CustomBlock() {
			super(

					Block.Properties.create(Material.IRON).sound(SoundType.CLOTH).hardnessAndResistance(2f, 50f).lightValue(0).harvestLevel(1)
							.harvestTool(ToolType.PICKAXE));

			setRegistryName("block_endion_blocjk");
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
