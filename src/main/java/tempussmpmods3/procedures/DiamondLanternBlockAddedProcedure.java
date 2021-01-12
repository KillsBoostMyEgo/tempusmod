package tempussmpmods3.procedures;

import tempussmpmods3.block.DiamondLanternExtraBlock;

import tempussmpmods3.TempusModElements;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Map;

@TempusModElements.ModElement.Tag
public class DiamondLanternBlockAddedProcedure extends TempusModElements.ModElement {
	public DiamondLanternBlockAddedProcedure(TempusModElements instance) {
		super(instance, 18);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure DiamondLanternBlockAdded!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure DiamondLanternBlockAdded!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure DiamondLanternBlockAdded!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		world.setBlockState(new BlockPos((int) x, (int) 1, (int) z), DiamondLanternExtraBlock.block.getDefaultState(), 3);
	}
}
