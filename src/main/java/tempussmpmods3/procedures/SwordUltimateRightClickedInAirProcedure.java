package tempussmpmods3.procedures;

import tempussmpmods3.TempusModVariables;

import tempussmpmods3.TempusModElements;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

@TempusModElements.ModElement.Tag
public class SwordUltimateRightClickedInAirProcedure extends TempusModElements.ModElement {
	public SwordUltimateRightClickedInAirProcedure(TempusModElements instance) {
		super(instance, 40);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure SwordUltimateRightClickedInAir!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure SwordUltimateRightClickedInAir!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure SwordUltimateRightClickedInAir!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure SwordUltimateRightClickedInAir!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure SwordUltimateRightClickedInAir!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new TempusModVariables.PlayerVariables())).ultSwordAbility) == 0)) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				UltimateSwordTemporiumAbilityProcedure.executeProcedure($_dependencies);
			}
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Temporium Ability Selected"), (false));
			}
		} else {
			if ((((entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new TempusModVariables.PlayerVariables())).ultSwordAbility) == 1)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("world", world);
					UltimateSwordNetheriteAbilityProcedure.executeProcedure($_dependencies);
				}
				if (entity instanceof PlayerEntity && !entity.world.isRemote) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Netherite Ability Selected"), (false));
				}
			} else {
				if ((((entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new TempusModVariables.PlayerVariables())).ultSwordAbility) == 2)) {
					{
						Map<String, Object> $_dependencies = new HashMap<>();
						$_dependencies.put("entity", entity);
						$_dependencies.put("world", world);
						EndoriumAbilityProcedure.executeProcedure($_dependencies);
					}
					if (entity instanceof PlayerEntity && !entity.world.isRemote) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Endion Ability Selected"), (false));
					}
				}
			}
		}
	}
}
