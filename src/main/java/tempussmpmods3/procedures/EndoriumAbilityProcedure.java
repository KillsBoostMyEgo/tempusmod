package tempussmpmods3.procedures;

import tempussmpmods3.TempusModElements;

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
				System.err.println("Failed to load dependency entity for procedure EndoriumAbility!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure EndoriumAbility!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		double x = 0;
		double y = 0;
		double z = 0;
		x = (double) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
				entity.getEyePosition(1f).add(entity.getLook(1f).x * 150, entity.getLook(1f).y * 150, entity.getLook(1f).z * 150),
				RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getX());
		y = (double) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
				entity.getEyePosition(1f).add(entity.getLook(1f).x * 150, entity.getLook(1f).y * 150, entity.getLook(1f).z * 150),
				RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getY());
		z = (double) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
				entity.getEyePosition(1f).add(entity.getLook(1f).x * 150, entity.getLook(1f).y * 150, entity.getLook(1f).z * 150),
				RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getZ());
		if ((world.isAirBlock(new BlockPos((int) (x), (int) (y), (int) (z))))) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("You can't teleport to air, Bakka!"), (true));
			}
		} else {
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
		}
	}
}