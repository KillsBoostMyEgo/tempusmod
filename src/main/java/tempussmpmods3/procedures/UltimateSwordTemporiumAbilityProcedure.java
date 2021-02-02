package tempussmpmods3.procedures;

import tempussmpmods3.TempusModElements;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.function.Function;
import java.util.Map;
import java.util.Comparator;

@TempusModElements.ModElement.Tag
public class UltimateSwordTemporiumAbilityProcedure extends TempusModElements.ModElement {
	public UltimateSwordTemporiumAbilityProcedure(TempusModElements instance) {
		super(instance, 39);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure UltimateSwordTemporiumAbility!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure UltimateSwordTemporiumAbility!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure UltimateSwordTemporiumAbility!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure UltimateSwordTemporiumAbility!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure UltimateSwordTemporiumAbility!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((((Entity) world
				.getEntitiesWithinAABB(PlayerEntity.class,
						new AxisAlignedBB(x - (5 / 2d), y - (5 / 2d), z - (5 / 2d), x + (5 / 2d), y + (5 / 2d), z + (5 / 2d)), null)
				.stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)) != null) == (true))) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Time is slowing, but yours increases!"), (true));
			}
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int) 100, (int) 5, (false), (false)));
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Time is getting slow.."), (true));
			}
		} else {
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("There are no players nearby"), (true));
			}
		}
		if (entity instanceof LivingEntity) {
			((LivingEntity) entity).removePotionEffect(Effects.SLOWNESS);
		}
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SPEED, (int) 100, (int) 4, (false), (false)));
	}
}
