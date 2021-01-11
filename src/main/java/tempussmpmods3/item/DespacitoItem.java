
package tempussmpmods3.item;

import tempussmpmods3.itemgroup.TempusItemGroup;

import tempussmpmods3.TempusModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Rarity;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.Item;

@TempusModElements.ModElement.Tag
public class DespacitoItem extends TempusModElements.ModElement {
	@ObjectHolder("tempus:despacito")
	public static final Item block = null;
	public DespacitoItem(TempusModElements instance) {
		super(instance, 9);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new MusicDiscItemCustom());
	}
	public static class MusicDiscItemCustom extends MusicDiscItem {
		public MusicDiscItemCustom() {
			super(0, TempusModElements.sounds.get(new ResourceLocation("tempus:despacito")),
					new Item.Properties().group(TempusItemGroup.tab).maxStackSize(1).rarity(Rarity.RARE));
			setRegistryName("despacito");
		}
	}
}
