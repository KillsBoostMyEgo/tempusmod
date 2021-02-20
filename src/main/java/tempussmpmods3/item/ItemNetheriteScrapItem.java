
package tempussmpmods3.item;

import tempussmpmods3.procedures.NoFireProcedure;

import tempussmpmods3.itemgroup.TempusItemGroup;

import tempussmpmods3.TempusModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.BlockState;

import java.util.Map;
import java.util.HashMap;

@TempusModElements.ModElement.Tag
public class ItemNetheriteScrapItem extends TempusModElements.ModElement {
	@ObjectHolder("tempus:item_netherite_scrap")
	public static final Item block = null;
	public ItemNetheriteScrapItem(TempusModElements instance) {
		super(instance, 26);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(TempusItemGroup.tab).maxStackSize(64).rarity(Rarity.UNCOMMON));
			setRegistryName("item_netherite_scrap");
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

		@Override
		public boolean onDroppedByPlayer(ItemStack itemstack, PlayerEntity entity) {
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			World world = entity.world;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				NoFireProcedure.executeProcedure($_dependencies);
			}
			return true;
		}
	}
}
