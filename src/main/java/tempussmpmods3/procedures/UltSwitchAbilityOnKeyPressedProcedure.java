package tempussmpmods3.procedures;

import tempussmpmods3.TempusModVariables;

import tempussmpmods3.TempusModElements;

import tempussmpmods3.TempusMod;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

@TempusModElements.ModElement.Tag
public class UltSwitchAbilityOnKeyPressedProcedure extends TempusModElements.ModElement {
	public UltSwitchAbilityOnKeyPressedProcedure(TempusModElements instance) {
		super(instance, 40);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TempusMod.LOGGER.warn("Failed to load dependency entity for procedure UltSwitchAbilityOnKeyPressed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			double _setval = (double) (((entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new TempusModVariables.PlayerVariables())).ultSwordAbility) + 1);
			entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.ultSwordAbility = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if ((((entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new TempusModVariables.PlayerVariables())).ultSwordAbility) > 2)) {
			{
				double _setval = (double) 0;
				entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ultSwordAbility = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((((entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new TempusModVariables.PlayerVariables())).ultSwordAbility) == 0)) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Temporium Ability Selected!"), (true));
			}
		} else if ((((entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new TempusModVariables.PlayerVariables())).ultSwordAbility) == 1)) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Netherite Ability Selected!"), (true));
			}
		} else if ((((entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new TempusModVariables.PlayerVariables())).ultSwordAbility) == 2)) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Endion Ability Selected!"), (true));
			}
		}
	}
}
