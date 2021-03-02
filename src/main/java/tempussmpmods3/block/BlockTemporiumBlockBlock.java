
package tempussmpmods3.block;

import net.minecraft.block.material.Material;

@TempusModElements.ModElement.Tag
public class BlockTemporiumBlockBlock extends TempusModElements.ModElement {

	@ObjectHolder("tempus:block_temporium_block")
	public static final Block block = null;

	public BlockTemporiumBlockBlock(TempusModElements instance) {
		super(instance, 88);

	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(TempusItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	public static class CustomBlock extends Block {

		public CustomBlock() {
			super(

					Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2f, 50f).lightValue(0).harvestLevel(1)
							.harvestTool(ToolType.PICKAXE));

			setRegistryName("block_temporium_block");
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public boolean isEmissiveRendering(BlockState blockState) {
			return true;
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
