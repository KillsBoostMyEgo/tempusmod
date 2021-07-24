package tempussmpmods3.procedures;

import tempussmpmods3.TempusModVariables;

import tempussmpmods3.TempusModElements;

import tempussmpmods3.TempusMod;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

@TempusModElements.ModElement.Tag
public class EndionTimerProcedure extends TempusModElements.ModElement {
	public EndionTimerProcedure(TempusModElements instance) {
		super(instance, 127);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TempusMod.LOGGER.warn("Failed to load dependency entity for procedure EndionTimer!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new TempusModVariables.PlayerVariables())).EndionTimer) < 600)) {
			{
				double _setval = (double) (((entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new TempusModVariables.PlayerVariables())).EndionTimer) + 1);
				entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.EndionTimer = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			World world = entity.world;
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
