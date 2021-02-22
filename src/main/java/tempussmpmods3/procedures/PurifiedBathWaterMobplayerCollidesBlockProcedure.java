package tempussmpmods3.procedures;

import tempussmpmods3.TempusModElements;

import tempussmpmods3.TempusMod;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

@TempusModElements.ModElement.Tag
public class PurifiedBathWaterMobplayerCollidesBlockProcedure extends TempusModElements.ModElement {
	public PurifiedBathWaterMobplayerCollidesBlockProcedure(TempusModElements instance) {
		super(instance, 16);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TempusMod.LOGGER.warn("Failed to load dependency entity for procedure PurifiedBathWaterMobplayerCollidesBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.REGENERATION, (int) 600, (int) 1, (false), (false)));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SPEED, (int) 600, (int) 1, (false), (false)));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SATURATION, (int) 600, (int) 1, (false), (false)));
	}
}
