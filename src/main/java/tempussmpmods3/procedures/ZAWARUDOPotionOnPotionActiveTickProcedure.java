package tempussmpmods3.procedures;

import tempussmpmods3.TempusModVariables;

import tempussmpmods3.TempusModElements;

import tempussmpmods3.TempusMod;

import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.Collections;

@TempusModElements.ModElement.Tag
public class ZAWARUDOPotionOnPotionActiveTickProcedure extends TempusModElements.ModElement {
	public ZAWARUDOPotionOnPotionActiveTickProcedure(TempusModElements instance) {
		super(instance, 119);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TempusMod.LOGGER.warn("Failed to load dependency entity for procedure ZAWARUDOPotionOnPotionActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			double _setval = (double) (entity.getPosX());
			entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.warudox = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) (entity.getPosY());
			entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.warudoy = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = (double) (entity.getPosZ());
			entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.warudoz = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		for (int index0 = 0; index0 < (int) (5); index0++) {
			{
				Entity _ent = entity;
				_ent.setPositionAndUpdate(
						((entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new TempusModVariables.PlayerVariables())).warudox),
						((entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new TempusModVariables.PlayerVariables())).warudoy),
						((entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new TempusModVariables.PlayerVariables())).warudoz));
				if (_ent instanceof ServerPlayerEntity) {
					((ServerPlayerEntity) _ent).connection.setPlayerLocation(
							((entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new TempusModVariables.PlayerVariables())).warudox),
							((entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new TempusModVariables.PlayerVariables())).warudoy),
							((entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new TempusModVariables.PlayerVariables())).warudoz),
							_ent.rotationYaw, _ent.rotationPitch, Collections.emptySet());
				}
			}
			entity.setMotionMultiplier(null, new Vec3d(0.25D, (double) 0.05F, 0.25D));
		}
	}
}
