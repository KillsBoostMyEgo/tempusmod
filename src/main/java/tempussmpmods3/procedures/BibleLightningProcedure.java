package tempussmpmods3.procedures;

import tempussmpmods3.block.PurifiedBathWaterBlock;
import tempussmpmods3.block.BathWaterBlock;

import tempussmpmods3.TempusModVariables;

import tempussmpmods3.TempusModElements;

import tempussmpmods3.TempusMod;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.state.Property;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import java.util.Map;

@TempusModElements.ModElement.Tag
public class BibleLightningProcedure extends TempusModElements.ModElement {
	public BibleLightningProcedure(TempusModElements instance) {
		super(instance, 13);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				TempusMod.LOGGER.warn("Failed to load dependency entity for procedure BibleLightning!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				TempusMod.LOGGER.warn("Failed to load dependency world for procedure BibleLightning!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.getBlockState(new BlockPos(
				(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
						entity.getEyePosition(1f).add(entity.getLook(1f).x * 5, entity.getLook(1f).y * 5, entity.getLook(1f).z * 5),
						RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, entity)).getPos().getX()),
				(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
						entity.getEyePosition(1f).add(entity.getLook(1f).x * 5, entity.getLook(1f).y * 5, entity.getLook(1f).z * 5),
						RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, entity)).getPos().getY()),
				(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
						entity.getEyePosition(1f).add(entity.getLook(1f).x * 5, entity.getLook(1f).y * 5, entity.getLook(1f).z * 5),
						RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, entity)).getPos().getZ()))))
								.getBlock() == BathWaterBlock.block.getDefaultState().getBlock())) {
			{
				BlockPos _bp = new BlockPos(
						(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
								entity.getEyePosition(1f).add(entity.getLook(1f).x * 5, entity.getLook(1f).y * 5, entity.getLook(1f).z * 5),
								RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, entity)).getPos().getX()),
						(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
								entity.getEyePosition(1f).add(entity.getLook(1f).x * 5, entity.getLook(1f).y * 5, entity.getLook(1f).z * 5),
								RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, entity)).getPos().getY()),
						(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
								entity.getEyePosition(1f).add(entity.getLook(1f).x * 5, entity.getLook(1f).y * 5, entity.getLook(1f).z * 5),
								RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, entity)).getPos().getZ()));
				BlockState _bs = PurifiedBathWaterBlock.block.getDefaultState();
				BlockState _bso = world.getBlockState(_bp);
				for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
					Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
					if (_property != null && _bs.get(_property) != null)
						try {
							_bs = _bs.with(_property, (Comparable) entry.getValue());
						} catch (Exception e) {
						}
				}
				TileEntity _te = world.getTileEntity(_bp);
				CompoundNBT _bnbt = null;
				if (_te != null) {
					_bnbt = _te.write(new CompoundNBT());
					_te.remove();
				}
				world.setBlockState(_bp, _bs, 3);
				if (_bnbt != null) {
					_te = world.getTileEntity(_bp);
					if (_te != null) {
						try {
							_te.read(_bso, _bnbt);
						} catch (Exception ignored) {
						}
					}
				}
			}
			if (world instanceof World)
				((World) world)
						.notifyNeighborsOfStateChange(
								new BlockPos(
										(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
												entity.getEyePosition(1f).add(entity.getLook(1f).x * 5, entity.getLook(1f).y * 5,
														entity.getLook(1f).z * 5),
												RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, entity)).getPos().getX()),
										(int) (entity.world
												.rayTraceBlocks(
														new RayTraceContext(entity.getEyePosition(1f),
																entity.getEyePosition(1f).add(entity.getLook(1f).x * 5, entity.getLook(1f).y * 5,
																		entity.getLook(1f).z * 5),
																RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, entity))
												.getPos().getY()),
										(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
												entity.getEyePosition(1f)
														.add(entity.getLook(1f).x * 5, entity.getLook(1f).y * 5, entity.getLook(1f).z * 5),
												RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, entity)).getPos().getZ())),
								((World) world).getBlockState(new BlockPos(
										(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
												entity.getEyePosition(1f).add(entity.getLook(1f).x * 5, entity.getLook(1f).y * 5,
														entity.getLook(1f).z * 5),
												RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, entity)).getPos().getX()),
										(int) (entity.world
												.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
														entity.getEyePosition(1f).add(entity.getLook(1f).x * 5, entity.getLook(1f).y * 5,
																entity.getLook(1f).z * 5),
														RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, entity))
												.getPos().getY()),
										(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
												entity.getEyePosition(1f).add(entity.getLook(1f).x * 5, entity.getLook(1f).y * 5,
														entity.getLook(1f).z * 5),
												RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, entity)).getPos().getZ())))
										.getBlock());
		} else if ((((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).experienceLevel : 0) >= 5)) {
			if (world instanceof ServerWorld) {
				LightningBoltEntity _ent = EntityType.LIGHTNING_BOLT.create((World) world);
				_ent.moveForced(Vector3d.copyCenteredHorizontally(
						new BlockPos(
								(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
										entity.getEyePosition(1f).add(entity.getLook(1f).x * 10, entity.getLook(1f).y * 10,
												entity.getLook(1f).z * 10),
										RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getX()),
								(int) (entity.world
										.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
												entity.getEyePosition(1f).add(entity.getLook(1f).x * 10, entity.getLook(1f).y * 10,
														entity.getLook(1f).z * 10),
												RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity))
										.getPos().getY()),
								(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
										entity.getEyePosition(1f).add(entity.getLook(1f).x * 10, entity.getLook(1f).y * 10,
												entity.getLook(1f).z * 10),
										RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getZ()))));
				_ent.setEffectOnly(false);
				((World) world).addEntity(_ent);
			}
			{
				double _setval = (double) 5;
				entity.getCapability(TempusModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.abilityTimerSec = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).addExperienceLevel(-((int) 5));
		}
	}
}
