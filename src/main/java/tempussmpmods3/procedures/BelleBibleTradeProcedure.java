package tempussmpmods3.procedures;

import tempussmpmods3.TempusModElements;

import tempussmpmods3.TempusMod;

import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

@TempusModElements.ModElement.Tag
public class BelleBibleTradeProcedure extends TempusModElements.ModElement {
	public BelleBibleTradeProcedure(TempusModElements instance) {
		super(instance, 7);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TempusMod.LOGGER.warn("Failed to load dependency entity for procedure BelleBibleTrade!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity) {
			ItemStack _stktoremove = new ItemStack(Items.DIAMOND, (int) (1));
			((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 32);
		}
		{
			Entity _ent = entity;
			if (!_ent.world.isRemote && _ent.world.getServer() != null) {
				_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
						"/give @p tempus:bible");
			}
		}
	}
}
