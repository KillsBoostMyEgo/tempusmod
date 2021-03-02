package tempussmpmods3.procedures;

@TempusModElements.ModElement.Tag
public class FoodCumSandwichFoodEatenProcedure extends TempusModElements.ModElement {

	public FoodCumSandwichFoodEatenProcedure(TempusModElements instance) {
		super(instance, 92);

	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TempusMod.LOGGER.warn("Failed to load dependency entity for procedure FoodCumSandwichFoodEaten!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");

		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(DiseasePotion.potion, (int) 60, (int) 1, (false), (true)));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int) 120, (int) 2, (false), (true)));
		if (entity instanceof PlayerEntity && !entity.world.isRemote) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("You Feel Gooey.... Ew"), (true));
		}

	}

}
