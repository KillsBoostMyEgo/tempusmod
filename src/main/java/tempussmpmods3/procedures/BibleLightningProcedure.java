package tempussmpmods3.procedures;

import tempussmpmods3.TempusModElements;

import tempussmpmods3.TempusMod;

import net.minecraft.entity.Entity;

import java.util.Map;

@TempusModElements.ModElement.Tag
public class BibleLightningProcedure extends TempusModElements.ModElement {
	public BibleLightningProcedure(TempusModElements instance) {
		super(instance, 13);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TempusMod.LOGGER.warn("Failed to load dependency entity for procedure BibleLightning!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			Entity _ent = entity;
			if (!_ent.world.isRemote && _ent.world.getServer() != null) {
				_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
						"/summon lightning_bolt ^ ^ ^5");
			}
		}
	}
}
