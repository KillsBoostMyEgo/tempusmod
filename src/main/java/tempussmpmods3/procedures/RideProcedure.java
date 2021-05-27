package tempussmpmods3.procedures;

import tempussmpmods3.TempusModElements;

import tempussmpmods3.TempusMod;

import net.minecraft.entity.Entity;

import java.util.Map;

@TempusModElements.ModElement.Tag
public class RideProcedure extends TempusModElements.ModElement {
	public RideProcedure(TempusModElements instance) {
		super(instance, 116);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TempusMod.LOGGER.warn("Failed to load dependency entity for procedure Ride!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				TempusMod.LOGGER.warn("Failed to load dependency sourceentity for procedure Ride!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if (((entity.isBeingRidden()) == (false))) {
			sourceentity.startRiding(entity);
		}
	}
}
