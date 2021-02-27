package tempussmpmods3.procedures;

import tempussmpmods3.TempusModElements;

import tempussmpmods3.TempusMod;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.function.Function;
import java.util.Map;
import java.util.Comparator;

@TempusModElements.ModElement.Tag
public class CHIKENProcedure extends TempusModElements.ModElement {
	public CHIKENProcedure(TempusModElements instance) {
		super(instance, 69);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TempusMod.LOGGER.warn("Failed to load dependency entity for procedure CHIKEN!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				TempusMod.LOGGER.warn("Failed to load dependency sourceentity for procedure CHIKEN!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				TempusMod.LOGGER.warn("Failed to load dependency x for procedure CHIKEN!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				TempusMod.LOGGER.warn("Failed to load dependency y for procedure CHIKEN!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				TempusMod.LOGGER.warn("Failed to load dependency z for procedure CHIKEN!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TempusMod.LOGGER.warn("Failed to load dependency world for procedure CHIKEN!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((sourceentity.isPassenger()) == (false))) {
			if (((((Entity) world
					.getEntitiesWithinAABB(PlayerEntity.class,
							new AxisAlignedBB(x - (2 / 2d), y - (2 / 2d), z - (2 / 2d), x + (2 / 2d), y + (2 / 2d), z + (2 / 2d)), null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) != null) == (true))) {
				sourceentity.startRiding(entity);
			}
		}
	}
}
