package tempussmpmods3.procedures;

import tempussmpmods3.TempusModElements;

import tempussmpmods3.TempusMod;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;

import java.util.Map;

@TempusModElements.ModElement.Tag
public class DiseaseOnPotionActiveTickProcedure extends TempusModElements.ModElement {
	public DiseaseOnPotionActiveTickProcedure(TempusModElements instance) {
		super(instance, 12);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TempusMod.LOGGER.warn("Failed to load dependency entity for procedure DiseaseOnPotionActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.attackEntityFrom(DamageSource.MAGIC, (float) 4);
	}
}
