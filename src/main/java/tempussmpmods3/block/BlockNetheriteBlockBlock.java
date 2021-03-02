
package tempussmpmods3.block;

import net.minecraft.block.material.Material;

@TempusModElements.ModElement.Tag
public class BlockNetheriteBlockBlock extends TempusModElements.ModElement {

	@ObjectHolder("tempus:block_netherite_block")
	public static final Block block = null;

	public BlockNetheriteBlockBlock(TempusModElements instance) {
		super(instance, 89);

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

			setRegistryName("block_netherite_block");
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
