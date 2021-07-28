package tempussmpmods3;

import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Direction;
import net.minecraft.network.PacketBuffer;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;

public class TempusModVariables {
	public TempusModVariables(TempusModElements elements) {
		elements.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new,
				PlayerVariablesSyncMessage::handler);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
	}

	private void init(FMLCommonSetupEvent event) {
		CapabilityManager.INSTANCE.register(PlayerVariables.class, new PlayerVariablesStorage(), PlayerVariables::new);
	}
	@CapabilityInject(PlayerVariables.class)
	public static Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = null;
	@SubscribeEvent
	public void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof PlayerEntity && !(event.getObject() instanceof FakePlayer))
			event.addCapability(new ResourceLocation("tempus", "player_variables"), new PlayerVariablesProvider());
	}
	private static class PlayerVariablesProvider implements ICapabilitySerializable<INBT> {
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(PLAYER_VARIABLES_CAPABILITY::getDefaultInstance);
		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public INBT serializeNBT() {
			return PLAYER_VARIABLES_CAPABILITY.getStorage().writeNBT(PLAYER_VARIABLES_CAPABILITY, this.instance.orElseThrow(RuntimeException::new),
					null);
		}

		@Override
		public void deserializeNBT(INBT nbt) {
			PLAYER_VARIABLES_CAPABILITY.getStorage().readNBT(PLAYER_VARIABLES_CAPABILITY, this.instance.orElseThrow(RuntimeException::new), null,
					nbt);
		}
	}

	private static class PlayerVariablesStorage implements Capability.IStorage<PlayerVariables> {
		@Override
		public INBT writeNBT(Capability<PlayerVariables> capability, PlayerVariables instance, Direction side) {
			CompoundNBT nbt = new CompoundNBT();
			nbt.putDouble("ultSwordAbility", instance.ultSwordAbility);
			nbt.putDouble("ultimateswordability", instance.ultimateswordability);
			nbt.put("helmet", instance.helmet.write(new CompoundNBT()));
			nbt.put("chestplate", instance.chestplate.write(new CompoundNBT()));
			nbt.put("leggings", instance.leggings.write(new CompoundNBT()));
			nbt.put("boots", instance.boots.write(new CompoundNBT()));
			nbt.putDouble("abilityTimerSec", instance.abilityTimerSec);
			nbt.putDouble("warudox", instance.warudox);
			nbt.putDouble("warudoy", instance.warudoy);
			nbt.putDouble("warudoz", instance.warudoz);
			nbt.putDouble("EndionTimer", instance.EndionTimer);
			nbt.put("slot1", instance.slot1.write(new CompoundNBT()));
			nbt.put("slot2", instance.slot2.write(new CompoundNBT()));
			nbt.put("slot3", instance.slot3.write(new CompoundNBT()));
			nbt.put("slot4", instance.slot4.write(new CompoundNBT()));
			nbt.put("slot5", instance.slot5.write(new CompoundNBT()));
			nbt.put("slot6", instance.slot6.write(new CompoundNBT()));
			nbt.put("slot7", instance.slot7.write(new CompoundNBT()));
			nbt.put("slot8", instance.slot8.write(new CompoundNBT()));
			nbt.put("slot9", instance.slot9.write(new CompoundNBT()));
			return nbt;
		}

		@Override
		public void readNBT(Capability<PlayerVariables> capability, PlayerVariables instance, Direction side, INBT inbt) {
			CompoundNBT nbt = (CompoundNBT) inbt;
			instance.ultSwordAbility = nbt.getDouble("ultSwordAbility");
			instance.ultimateswordability = nbt.getDouble("ultimateswordability");
			instance.helmet = ItemStack.read(nbt.getCompound("helmet"));
			instance.chestplate = ItemStack.read(nbt.getCompound("chestplate"));
			instance.leggings = ItemStack.read(nbt.getCompound("leggings"));
			instance.boots = ItemStack.read(nbt.getCompound("boots"));
			instance.abilityTimerSec = nbt.getDouble("abilityTimerSec");
			instance.warudox = nbt.getDouble("warudox");
			instance.warudoy = nbt.getDouble("warudoy");
			instance.warudoz = nbt.getDouble("warudoz");
			instance.EndionTimer = nbt.getDouble("EndionTimer");
			instance.slot1 = ItemStack.read(nbt.getCompound("slot1"));
			instance.slot2 = ItemStack.read(nbt.getCompound("slot2"));
			instance.slot3 = ItemStack.read(nbt.getCompound("slot3"));
			instance.slot4 = ItemStack.read(nbt.getCompound("slot4"));
			instance.slot5 = ItemStack.read(nbt.getCompound("slot5"));
			instance.slot6 = ItemStack.read(nbt.getCompound("slot6"));
			instance.slot7 = ItemStack.read(nbt.getCompound("slot7"));
			instance.slot8 = ItemStack.read(nbt.getCompound("slot8"));
			instance.slot9 = ItemStack.read(nbt.getCompound("slot9"));
		}
	}

	public static class PlayerVariables {
		public double ultSwordAbility = 0;
		public double ultimateswordability = 0;
		public ItemStack helmet = ItemStack.EMPTY;
		public ItemStack chestplate = ItemStack.EMPTY;
		public ItemStack leggings = ItemStack.EMPTY;
		public ItemStack boots = ItemStack.EMPTY;
		public double abilityTimerSec = 1.0;
		public double warudox = 0;
		public double warudoy = 0;
		public double warudoz = 0.0;
		public double EndionTimer = 600.0;
		public ItemStack slot1 = ItemStack.EMPTY;
		public ItemStack slot2 = ItemStack.EMPTY;
		public ItemStack slot3 = ItemStack.EMPTY;
		public ItemStack slot4 = ItemStack.EMPTY;
		public ItemStack slot5 = ItemStack.EMPTY;
		public ItemStack slot6 = ItemStack.EMPTY;
		public ItemStack slot7 = ItemStack.EMPTY;
		public ItemStack slot8 = ItemStack.EMPTY;
		public ItemStack slot9 = ItemStack.EMPTY;
		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayerEntity)
				TempusMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) entity), new PlayerVariablesSyncMessage(this));
		}
	}
	@SubscribeEvent
	public void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void clonePlayer(PlayerEvent.Clone event) {
		PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new PlayerVariables()));
		PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
		clone.ultSwordAbility = original.ultSwordAbility;
		clone.ultimateswordability = original.ultimateswordability;
		clone.slot1 = original.slot1;
		clone.slot2 = original.slot2;
		clone.slot3 = original.slot3;
		clone.slot4 = original.slot4;
		clone.slot5 = original.slot5;
		clone.slot6 = original.slot6;
		clone.slot7 = original.slot7;
		clone.slot8 = original.slot8;
		clone.slot9 = original.slot9;
		if (!event.isWasDeath()) {
			clone.helmet = original.helmet;
			clone.chestplate = original.chestplate;
			clone.leggings = original.leggings;
			clone.boots = original.boots;
			clone.abilityTimerSec = original.abilityTimerSec;
			clone.warudox = original.warudox;
			clone.warudoy = original.warudoy;
			clone.warudoz = original.warudoz;
			clone.EndionTimer = original.EndionTimer;
		}
	}
	public static class PlayerVariablesSyncMessage {
		public PlayerVariables data;
		public PlayerVariablesSyncMessage(PacketBuffer buffer) {
			this.data = new PlayerVariables();
			new PlayerVariablesStorage().readNBT(null, this.data, null, buffer.readCompoundTag());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, PacketBuffer buffer) {
			buffer.writeCompoundTag((CompoundNBT) new PlayerVariablesStorage().writeNBT(null, message.data, null));
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new PlayerVariables()));
					variables.ultSwordAbility = message.data.ultSwordAbility;
					variables.ultimateswordability = message.data.ultimateswordability;
					variables.helmet = message.data.helmet;
					variables.chestplate = message.data.chestplate;
					variables.leggings = message.data.leggings;
					variables.boots = message.data.boots;
					variables.abilityTimerSec = message.data.abilityTimerSec;
					variables.warudox = message.data.warudox;
					variables.warudoy = message.data.warudoy;
					variables.warudoz = message.data.warudoz;
					variables.EndionTimer = message.data.EndionTimer;
					variables.slot1 = message.data.slot1;
					variables.slot2 = message.data.slot2;
					variables.slot3 = message.data.slot3;
					variables.slot4 = message.data.slot4;
					variables.slot5 = message.data.slot5;
					variables.slot6 = message.data.slot6;
					variables.slot7 = message.data.slot7;
					variables.slot8 = message.data.slot8;
					variables.slot9 = message.data.slot9;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
