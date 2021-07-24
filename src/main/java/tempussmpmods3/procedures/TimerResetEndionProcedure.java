package tempussmpmods3.procedures;

import tempussmpmods3.TempusModVariables;

import tempussmpmods3.TempusModElements;

import tempussmpmods3.TempusMod;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

@TempusModElements.ModElement.Tag
public class TimerResetEndionProcedure extends TempusModElements.ModElement {
	public TimerResetEndionProcedure(TempusModElements instance) {
		super(instance, 128);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TempusMod.LOGGER.warn("Failed to load dependency entity for procedure TimerResetEndion!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			double _setval = (double) 600;
			entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.EndionTimer = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		Entity entity = event.getPlayer();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("x", entity.getPosX());
		dependencies.put("y", entity.getPosY());
		dependencies.put("z", entity.getPosZ());
		dependencies.put("world", entity.world);
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
