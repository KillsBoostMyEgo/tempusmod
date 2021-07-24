
package tempussmpmods3.block;

import tempussmpmods3.procedures.DiamondLanternBlockAddedProcedure;

import tempussmpmods3.itemgroup.TempusItemGroup;

import tempussmpmods3.TempusModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;

@TempusModElements.ModElement.Tag
public class DiamondLanternBlock extends TempusModElements.ModElement {
	@ObjectHolder("tempus:diamond_lantern")
	public static final Block block = null;
	public DiamondLanternBlock(TempusModElements instance) {
		super(instance, 14);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(TempusItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.GLASS).sound(SoundType.GLASS).hardnessAndResistance(1f, 10f).setLightLevel(s -> 15).harvestLevel(1)
					.harvestTool(ToolType.PICKAXE).setRequiresTool().setNeedsPostProcessing((bs, br, bp) -> true)
					.setEmmisiveRendering((bs, br, bp) -> true));
			setRegistryName("diamond_lantern");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}

		@Override
		public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean moving) {
			super.onBlockAdded(state, world, pos, oldState, moving);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				DiamondLanternBlockAddedProcedure.executeProcedure($_dependencies);
			}
		}
	}
}
