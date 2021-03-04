package tempussmpmods3.procedures;

import tempussmpmods3.TempusModElements;

import tempussmpmods3.TempusMod;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.Collections;

@TempusModElements.ModElement.Tag
public class EndoriumAbilityProcedure extends TempusModElements.ModElement {
	public EndoriumAbilityProcedure(TempusModElements instance) {
		super(instance, 55);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TempusMod.LOGGER.warn("Failed to load dependency entity for procedure EndoriumAbility!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TempusMod.LOGGER.warn("Failed to load dependency world for procedure EndoriumAbility!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		double x = 0;
		double y = 0;
		double z = 0;
		double wait = 0;
		if ((((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).experienceLevel : 0) >= 1.5)) {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).addExperienceLevel(-((int) 1.5));
			x = (double) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
					entity.getEyePosition(1f).add(entity.getLook(1f).x * 50, entity.getLook(1f).y * 50, entity.getLook(1f).z * 50),
					RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getX());
			y = (double) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
					entity.getEyePosition(1f).add(entity.getLook(1f).x * 50, entity.getLook(1f).y * 50, entity.getLook(1f).z * 50),
					RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getY());
			z = (double) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
					entity.getEyePosition(1f).add(entity.getLook(1f).x * 50, entity.getLook(1f).y * 50, entity.getLook(1f).z * 50),
					RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getZ());
			if (((world.isAirBlock(new BlockPos((int) (x), (int) (y), (int) (z)))) == (false))) {
				{
					Entity _ent = entity;
					_ent.setPositionAndUpdate(((((x) - (entity.getPosX())) - 1) + (entity.getPosX())),
							((((y) - (entity.getPosY())) + 1) + (entity.getPosY())), ((((z) - (entity.getPosZ())) - 1) + (entity.getPosZ())));
					if (_ent instanceof ServerPlayerEntity) {
						((ServerPlayerEntity) _ent).connection.setPlayerLocation(((((x) - (entity.getPosX())) - 1) + (entity.getPosX())),
								((((y) - (entity.getPosY())) + 1) + (entity.getPosY())), ((((z) - (entity.getPosZ())) - 1) + (entity.getPosZ())),
								_ent.rotationYaw, _ent.rotationPitch, Collections.emptySet());
					}
				}
			} else if ((world.isAirBlock(new BlockPos((int) (x), (int) (y), (int) (z))))) {
				if (entity instanceof PlayerEntity && !entity.world.isRemote) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Can't teleport to air dumbass!"), (true));
				}
			}
		}
	}
}
