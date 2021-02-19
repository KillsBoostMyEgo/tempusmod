
package tempussmpmods3.gui;

import tempussmpmods3.TempusModElements;

import tempussmpmods3.TempusMod;

import org.lwjgl.opengl.GL11;

import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

@TempusModElements.ModElement.Tag
public class HugeCrateGui extends TempusModElements.ModElement {
	public static HashMap guistate = new HashMap();
	private static ContainerType<GuiContainerMod> containerType = null;
	public HugeCrateGui(TempusModElements instance) {
		super(instance, 65);
		elements.addNetworkMessage(ButtonPressedMessage.class, ButtonPressedMessage::buffer, ButtonPressedMessage::new,
				ButtonPressedMessage::handler);
		elements.addNetworkMessage(GUISlotChangedMessage.class, GUISlotChangedMessage::buffer, GUISlotChangedMessage::new,
				GUISlotChangedMessage::handler);
		containerType = new ContainerType<>(new GuiContainerModFactory());
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@OnlyIn(Dist.CLIENT)
	public void initElements() {
		DeferredWorkQueue.runLater(() -> ScreenManager.registerFactory(containerType, GuiWindow::new));
	}

	@SubscribeEvent
	public void registerContainer(RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry().register(containerType.setRegistryName("huge_crate"));
	}
	public static class GuiContainerModFactory implements IContainerFactory {
		public GuiContainerMod create(int id, PlayerInventory inv, PacketBuffer extraData) {
			return new GuiContainerMod(id, inv, extraData);
		}
	}

	public static class GuiContainerMod extends Container implements Supplier<Map<Integer, Slot>> {
		private World world;
		private PlayerEntity entity;
		private int x, y, z;
		private IItemHandler internal;
		private Map<Integer, Slot> customSlots = new HashMap<>();
		private boolean bound = false;
		public GuiContainerMod(int id, PlayerInventory inv, PacketBuffer extraData) {
			super(containerType, id);
			this.entity = inv.player;
			this.world = inv.player.world;
			this.internal = new ItemStackHandler(216);
			BlockPos pos = null;
			if (extraData != null) {
				pos = extraData.readBlockPos();
				this.x = pos.getX();
				this.y = pos.getY();
				this.z = pos.getZ();
			}
			if (pos != null) {
				if (extraData.readableBytes() == 1) { // bound to item
					byte hand = extraData.readByte();
					ItemStack itemstack;
					if (hand == 0)
						itemstack = this.entity.getHeldItemMainhand();
					else
						itemstack = this.entity.getHeldItemOffhand();
					itemstack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						this.internal = capability;
						this.bound = true;
					});
				} else if (extraData.readableBytes() > 1) {
					extraData.readByte(); // drop padding
					Entity entity = world.getEntityByID(extraData.readVarInt());
					if (entity != null)
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
							this.internal = capability;
							this.bound = true;
						});
				} else { // might be bound to block
					TileEntity ent = inv.player != null ? inv.player.world.getTileEntity(pos) : null;
					if (ent != null) {
						ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
							this.internal = capability;
							this.bound = true;
						});
					}
				}
			}
			this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 3, 4) {
			}));
			this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 21, 4) {
			}));
			this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, 39, 4) {
			}));
			this.customSlots.put(3, this.addSlot(new SlotItemHandler(internal, 3, 57, 4) {
			}));
			this.customSlots.put(4, this.addSlot(new SlotItemHandler(internal, 4, 75, 4) {
			}));
			this.customSlots.put(5, this.addSlot(new SlotItemHandler(internal, 5, 93, 4) {
			}));
			this.customSlots.put(6, this.addSlot(new SlotItemHandler(internal, 6, 111, 4) {
			}));
			this.customSlots.put(7, this.addSlot(new SlotItemHandler(internal, 7, 129, 4) {
			}));
			this.customSlots.put(8, this.addSlot(new SlotItemHandler(internal, 8, 147, 4) {
			}));
			this.customSlots.put(9, this.addSlot(new SlotItemHandler(internal, 9, 165, 4) {
			}));
			this.customSlots.put(10, this.addSlot(new SlotItemHandler(internal, 10, 183, 4) {
			}));
			this.customSlots.put(11, this.addSlot(new SlotItemHandler(internal, 11, 201, 4) {
			}));
			this.customSlots.put(12, this.addSlot(new SlotItemHandler(internal, 12, 219, 4) {
			}));
			this.customSlots.put(13, this.addSlot(new SlotItemHandler(internal, 13, 237, 4) {
			}));
			this.customSlots.put(14, this.addSlot(new SlotItemHandler(internal, 14, 255, 4) {
			}));
			this.customSlots.put(15, this.addSlot(new SlotItemHandler(internal, 15, 273, 4) {
			}));
			this.customSlots.put(16, this.addSlot(new SlotItemHandler(internal, 16, 291, 4) {
			}));
			this.customSlots.put(17, this.addSlot(new SlotItemHandler(internal, 17, 309, 4) {
			}));
			this.customSlots.put(18, this.addSlot(new SlotItemHandler(internal, 18, 327, 4) {
			}));
			this.customSlots.put(19, this.addSlot(new SlotItemHandler(internal, 19, 345, 4) {
			}));
			this.customSlots.put(20, this.addSlot(new SlotItemHandler(internal, 20, 363, 4) {
			}));
			this.customSlots.put(21, this.addSlot(new SlotItemHandler(internal, 21, 381, 4) {
			}));
			this.customSlots.put(22, this.addSlot(new SlotItemHandler(internal, 22, 399, 4) {
			}));
			this.customSlots.put(23, this.addSlot(new SlotItemHandler(internal, 23, 3, 22) {
			}));
			this.customSlots.put(24, this.addSlot(new SlotItemHandler(internal, 24, 21, 22) {
			}));
			this.customSlots.put(25, this.addSlot(new SlotItemHandler(internal, 25, 39, 22) {
			}));
			this.customSlots.put(26, this.addSlot(new SlotItemHandler(internal, 26, 57, 22) {
			}));
			this.customSlots.put(27, this.addSlot(new SlotItemHandler(internal, 27, 75, 22) {
			}));
			this.customSlots.put(28, this.addSlot(new SlotItemHandler(internal, 28, 93, 22) {
			}));
			this.customSlots.put(29, this.addSlot(new SlotItemHandler(internal, 29, 111, 22) {
			}));
			this.customSlots.put(30, this.addSlot(new SlotItemHandler(internal, 30, 129, 22) {
			}));
			this.customSlots.put(31, this.addSlot(new SlotItemHandler(internal, 31, 147, 22) {
			}));
			this.customSlots.put(32, this.addSlot(new SlotItemHandler(internal, 32, 165, 22) {
			}));
			this.customSlots.put(33, this.addSlot(new SlotItemHandler(internal, 33, 183, 22) {
			}));
			this.customSlots.put(34, this.addSlot(new SlotItemHandler(internal, 34, 201, 22) {
			}));
			this.customSlots.put(35, this.addSlot(new SlotItemHandler(internal, 35, 219, 22) {
			}));
			this.customSlots.put(36, this.addSlot(new SlotItemHandler(internal, 36, 237, 22) {
			}));
			this.customSlots.put(37, this.addSlot(new SlotItemHandler(internal, 37, 255, 22) {
			}));
			this.customSlots.put(38, this.addSlot(new SlotItemHandler(internal, 38, 273, 22) {
			}));
			this.customSlots.put(39, this.addSlot(new SlotItemHandler(internal, 39, 291, 22) {
			}));
			this.customSlots.put(40, this.addSlot(new SlotItemHandler(internal, 40, 309, 22) {
			}));
			this.customSlots.put(41, this.addSlot(new SlotItemHandler(internal, 41, 327, 22) {
			}));
			this.customSlots.put(42, this.addSlot(new SlotItemHandler(internal, 42, 345, 22) {
			}));
			this.customSlots.put(43, this.addSlot(new SlotItemHandler(internal, 43, 363, 22) {
			}));
			this.customSlots.put(44, this.addSlot(new SlotItemHandler(internal, 44, 381, 22) {
			}));
			this.customSlots.put(45, this.addSlot(new SlotItemHandler(internal, 45, 399, 22) {
			}));
			this.customSlots.put(46, this.addSlot(new SlotItemHandler(internal, 46, 3, 40) {
			}));
			this.customSlots.put(47, this.addSlot(new SlotItemHandler(internal, 47, 21, 40) {
			}));
			this.customSlots.put(48, this.addSlot(new SlotItemHandler(internal, 48, 39, 40) {
			}));
			this.customSlots.put(49, this.addSlot(new SlotItemHandler(internal, 49, 57, 40) {
			}));
			this.customSlots.put(50, this.addSlot(new SlotItemHandler(internal, 50, 75, 40) {
			}));
			this.customSlots.put(51, this.addSlot(new SlotItemHandler(internal, 51, 93, 40) {
			}));
			this.customSlots.put(52, this.addSlot(new SlotItemHandler(internal, 52, 111, 40) {
			}));
			this.customSlots.put(53, this.addSlot(new SlotItemHandler(internal, 53, 129, 40) {
			}));
			this.customSlots.put(54, this.addSlot(new SlotItemHandler(internal, 54, 147, 40) {
			}));
			this.customSlots.put(55, this.addSlot(new SlotItemHandler(internal, 55, 165, 40) {
			}));
			this.customSlots.put(56, this.addSlot(new SlotItemHandler(internal, 56, 183, 40) {
			}));
			this.customSlots.put(57, this.addSlot(new SlotItemHandler(internal, 57, 201, 40) {
			}));
			this.customSlots.put(58, this.addSlot(new SlotItemHandler(internal, 58, 219, 40) {
			}));
			this.customSlots.put(59, this.addSlot(new SlotItemHandler(internal, 59, 237, 40) {
			}));
			this.customSlots.put(60, this.addSlot(new SlotItemHandler(internal, 60, 255, 40) {
			}));
			this.customSlots.put(61, this.addSlot(new SlotItemHandler(internal, 61, 273, 40) {
			}));
			this.customSlots.put(62, this.addSlot(new SlotItemHandler(internal, 62, 291, 40) {
			}));
			this.customSlots.put(63, this.addSlot(new SlotItemHandler(internal, 63, 309, 40) {
			}));
			this.customSlots.put(64, this.addSlot(new SlotItemHandler(internal, 64, 327, 40) {
			}));
			this.customSlots.put(65, this.addSlot(new SlotItemHandler(internal, 65, 345, 40) {
			}));
			this.customSlots.put(66, this.addSlot(new SlotItemHandler(internal, 66, 363, 40) {
			}));
			this.customSlots.put(67, this.addSlot(new SlotItemHandler(internal, 67, 381, 40) {
			}));
			this.customSlots.put(68, this.addSlot(new SlotItemHandler(internal, 68, 399, 40) {
			}));
			this.customSlots.put(69, this.addSlot(new SlotItemHandler(internal, 69, 3, 58) {
			}));
			this.customSlots.put(70, this.addSlot(new SlotItemHandler(internal, 70, 21, 58) {
			}));
			this.customSlots.put(71, this.addSlot(new SlotItemHandler(internal, 71, 39, 58) {
			}));
			this.customSlots.put(72, this.addSlot(new SlotItemHandler(internal, 72, 57, 58) {
			}));
			this.customSlots.put(73, this.addSlot(new SlotItemHandler(internal, 73, 75, 58) {
			}));
			this.customSlots.put(74, this.addSlot(new SlotItemHandler(internal, 74, 93, 58) {
			}));
			this.customSlots.put(75, this.addSlot(new SlotItemHandler(internal, 75, 111, 58) {
			}));
			this.customSlots.put(76, this.addSlot(new SlotItemHandler(internal, 76, 129, 58) {
			}));
			this.customSlots.put(77, this.addSlot(new SlotItemHandler(internal, 77, 147, 58) {
			}));
			this.customSlots.put(78, this.addSlot(new SlotItemHandler(internal, 78, 165, 58) {
			}));
			this.customSlots.put(79, this.addSlot(new SlotItemHandler(internal, 79, 183, 58) {
			}));
			this.customSlots.put(80, this.addSlot(new SlotItemHandler(internal, 80, 201, 58) {
			}));
			this.customSlots.put(81, this.addSlot(new SlotItemHandler(internal, 81, 219, 58) {
			}));
			this.customSlots.put(82, this.addSlot(new SlotItemHandler(internal, 82, 237, 58) {
			}));
			this.customSlots.put(83, this.addSlot(new SlotItemHandler(internal, 83, 255, 58) {
			}));
			this.customSlots.put(84, this.addSlot(new SlotItemHandler(internal, 84, 273, 58) {
			}));
			this.customSlots.put(85, this.addSlot(new SlotItemHandler(internal, 85, 291, 58) {
			}));
			this.customSlots.put(86, this.addSlot(new SlotItemHandler(internal, 86, 309, 58) {
			}));
			this.customSlots.put(87, this.addSlot(new SlotItemHandler(internal, 87, 327, 58) {
			}));
			this.customSlots.put(88, this.addSlot(new SlotItemHandler(internal, 88, 345, 58) {
			}));
			this.customSlots.put(89, this.addSlot(new SlotItemHandler(internal, 89, 363, 58) {
			}));
			this.customSlots.put(90, this.addSlot(new SlotItemHandler(internal, 90, 381, 58) {
			}));
			this.customSlots.put(91, this.addSlot(new SlotItemHandler(internal, 91, 399, 58) {
			}));
			this.customSlots.put(92, this.addSlot(new SlotItemHandler(internal, 92, 3, 76) {
			}));
			this.customSlots.put(93, this.addSlot(new SlotItemHandler(internal, 93, 21, 76) {
			}));
			this.customSlots.put(94, this.addSlot(new SlotItemHandler(internal, 94, 39, 76) {
			}));
			this.customSlots.put(95, this.addSlot(new SlotItemHandler(internal, 95, 57, 76) {
			}));
			this.customSlots.put(96, this.addSlot(new SlotItemHandler(internal, 96, 75, 76) {
			}));
			this.customSlots.put(97, this.addSlot(new SlotItemHandler(internal, 97, 93, 76) {
			}));
			this.customSlots.put(98, this.addSlot(new SlotItemHandler(internal, 98, 111, 76) {
			}));
			this.customSlots.put(99, this.addSlot(new SlotItemHandler(internal, 99, 129, 76) {
			}));
			this.customSlots.put(100, this.addSlot(new SlotItemHandler(internal, 100, 147, 76) {
			}));
			this.customSlots.put(101, this.addSlot(new SlotItemHandler(internal, 101, 165, 76) {
			}));
			this.customSlots.put(102, this.addSlot(new SlotItemHandler(internal, 102, 183, 76) {
			}));
			this.customSlots.put(103, this.addSlot(new SlotItemHandler(internal, 103, 201, 76) {
			}));
			this.customSlots.put(104, this.addSlot(new SlotItemHandler(internal, 104, 219, 76) {
			}));
			this.customSlots.put(105, this.addSlot(new SlotItemHandler(internal, 105, 237, 76) {
			}));
			this.customSlots.put(106, this.addSlot(new SlotItemHandler(internal, 106, 255, 76) {
			}));
			this.customSlots.put(107, this.addSlot(new SlotItemHandler(internal, 107, 273, 76) {
			}));
			this.customSlots.put(108, this.addSlot(new SlotItemHandler(internal, 108, 291, 76) {
			}));
			this.customSlots.put(109, this.addSlot(new SlotItemHandler(internal, 109, 309, 76) {
			}));
			this.customSlots.put(110, this.addSlot(new SlotItemHandler(internal, 110, 327, 76) {
			}));
			this.customSlots.put(111, this.addSlot(new SlotItemHandler(internal, 111, 345, 76) {
			}));
			this.customSlots.put(112, this.addSlot(new SlotItemHandler(internal, 112, 363, 76) {
			}));
			this.customSlots.put(113, this.addSlot(new SlotItemHandler(internal, 113, 381, 76) {
			}));
			this.customSlots.put(114, this.addSlot(new SlotItemHandler(internal, 114, 399, 76) {
			}));
			this.customSlots.put(115, this.addSlot(new SlotItemHandler(internal, 115, 3, 94) {
			}));
			this.customSlots.put(116, this.addSlot(new SlotItemHandler(internal, 116, 21, 94) {
			}));
			this.customSlots.put(117, this.addSlot(new SlotItemHandler(internal, 117, 39, 94) {
			}));
			this.customSlots.put(118, this.addSlot(new SlotItemHandler(internal, 118, 57, 94) {
			}));
			this.customSlots.put(119, this.addSlot(new SlotItemHandler(internal, 119, 75, 94) {
			}));
			this.customSlots.put(120, this.addSlot(new SlotItemHandler(internal, 120, 93, 94) {
			}));
			this.customSlots.put(121, this.addSlot(new SlotItemHandler(internal, 121, 111, 94) {
			}));
			this.customSlots.put(122, this.addSlot(new SlotItemHandler(internal, 122, 129, 94) {
			}));
			this.customSlots.put(123, this.addSlot(new SlotItemHandler(internal, 123, 147, 94) {
			}));
			this.customSlots.put(124, this.addSlot(new SlotItemHandler(internal, 124, 165, 94) {
			}));
			this.customSlots.put(125, this.addSlot(new SlotItemHandler(internal, 125, 183, 94) {
			}));
			this.customSlots.put(126, this.addSlot(new SlotItemHandler(internal, 126, 201, 94) {
			}));
			this.customSlots.put(127, this.addSlot(new SlotItemHandler(internal, 127, 219, 94) {
			}));
			this.customSlots.put(128, this.addSlot(new SlotItemHandler(internal, 128, 237, 94) {
			}));
			this.customSlots.put(129, this.addSlot(new SlotItemHandler(internal, 129, 255, 94) {
			}));
			this.customSlots.put(130, this.addSlot(new SlotItemHandler(internal, 130, 273, 94) {
			}));
			this.customSlots.put(131, this.addSlot(new SlotItemHandler(internal, 131, 291, 94) {
			}));
			this.customSlots.put(132, this.addSlot(new SlotItemHandler(internal, 132, 309, 94) {
			}));
			this.customSlots.put(133, this.addSlot(new SlotItemHandler(internal, 133, 327, 94) {
			}));
			this.customSlots.put(134, this.addSlot(new SlotItemHandler(internal, 134, 345, 94) {
			}));
			this.customSlots.put(135, this.addSlot(new SlotItemHandler(internal, 135, 363, 94) {
			}));
			this.customSlots.put(136, this.addSlot(new SlotItemHandler(internal, 136, 381, 94) {
			}));
			this.customSlots.put(137, this.addSlot(new SlotItemHandler(internal, 137, 399, 94) {
			}));
			this.customSlots.put(138, this.addSlot(new SlotItemHandler(internal, 138, 3, 112) {
			}));
			this.customSlots.put(139, this.addSlot(new SlotItemHandler(internal, 139, 21, 112) {
			}));
			this.customSlots.put(140, this.addSlot(new SlotItemHandler(internal, 140, 39, 112) {
			}));
			this.customSlots.put(141, this.addSlot(new SlotItemHandler(internal, 141, 57, 112) {
			}));
			this.customSlots.put(142, this.addSlot(new SlotItemHandler(internal, 142, 75, 112) {
			}));
			this.customSlots.put(143, this.addSlot(new SlotItemHandler(internal, 143, 93, 112) {
			}));
			this.customSlots.put(144, this.addSlot(new SlotItemHandler(internal, 144, 111, 112) {
			}));
			this.customSlots.put(145, this.addSlot(new SlotItemHandler(internal, 145, 129, 112) {
			}));
			this.customSlots.put(146, this.addSlot(new SlotItemHandler(internal, 146, 147, 112) {
			}));
			this.customSlots.put(147, this.addSlot(new SlotItemHandler(internal, 147, 165, 112) {
			}));
			this.customSlots.put(148, this.addSlot(new SlotItemHandler(internal, 148, 183, 112) {
			}));
			this.customSlots.put(149, this.addSlot(new SlotItemHandler(internal, 149, 201, 112) {
			}));
			this.customSlots.put(150, this.addSlot(new SlotItemHandler(internal, 150, 219, 112) {
			}));
			this.customSlots.put(151, this.addSlot(new SlotItemHandler(internal, 151, 237, 112) {
			}));
			this.customSlots.put(152, this.addSlot(new SlotItemHandler(internal, 152, 255, 112) {
			}));
			this.customSlots.put(153, this.addSlot(new SlotItemHandler(internal, 153, 273, 112) {
			}));
			this.customSlots.put(154, this.addSlot(new SlotItemHandler(internal, 154, 291, 112) {
			}));
			this.customSlots.put(155, this.addSlot(new SlotItemHandler(internal, 155, 309, 112) {
			}));
			this.customSlots.put(156, this.addSlot(new SlotItemHandler(internal, 156, 327, 112) {
			}));
			this.customSlots.put(157, this.addSlot(new SlotItemHandler(internal, 157, 345, 112) {
			}));
			this.customSlots.put(158, this.addSlot(new SlotItemHandler(internal, 158, 363, 112) {
			}));
			this.customSlots.put(159, this.addSlot(new SlotItemHandler(internal, 159, 381, 112) {
			}));
			this.customSlots.put(160, this.addSlot(new SlotItemHandler(internal, 160, 399, 112) {
			}));
			this.customSlots.put(161, this.addSlot(new SlotItemHandler(internal, 161, 3, 130) {
			}));
			this.customSlots.put(162, this.addSlot(new SlotItemHandler(internal, 162, 21, 130) {
			}));
			this.customSlots.put(163, this.addSlot(new SlotItemHandler(internal, 163, 39, 130) {
			}));
			this.customSlots.put(164, this.addSlot(new SlotItemHandler(internal, 164, 57, 130) {
			}));
			this.customSlots.put(165, this.addSlot(new SlotItemHandler(internal, 165, 75, 130) {
			}));
			this.customSlots.put(166, this.addSlot(new SlotItemHandler(internal, 166, 93, 130) {
			}));
			this.customSlots.put(167, this.addSlot(new SlotItemHandler(internal, 167, 3, 148) {
			}));
			this.customSlots.put(168, this.addSlot(new SlotItemHandler(internal, 168, 21, 148) {
			}));
			this.customSlots.put(169, this.addSlot(new SlotItemHandler(internal, 169, 39, 148) {
			}));
			this.customSlots.put(170, this.addSlot(new SlotItemHandler(internal, 170, 57, 148) {
			}));
			this.customSlots.put(171, this.addSlot(new SlotItemHandler(internal, 171, 75, 148) {
			}));
			this.customSlots.put(172, this.addSlot(new SlotItemHandler(internal, 172, 93, 148) {
			}));
			this.customSlots.put(173, this.addSlot(new SlotItemHandler(internal, 173, 3, 166) {
			}));
			this.customSlots.put(174, this.addSlot(new SlotItemHandler(internal, 174, 21, 166) {
			}));
			this.customSlots.put(175, this.addSlot(new SlotItemHandler(internal, 175, 39, 166) {
			}));
			this.customSlots.put(176, this.addSlot(new SlotItemHandler(internal, 176, 57, 166) {
			}));
			this.customSlots.put(177, this.addSlot(new SlotItemHandler(internal, 177, 75, 166) {
			}));
			this.customSlots.put(178, this.addSlot(new SlotItemHandler(internal, 178, 93, 166) {
			}));
			this.customSlots.put(179, this.addSlot(new SlotItemHandler(internal, 179, 3, 184) {
			}));
			this.customSlots.put(180, this.addSlot(new SlotItemHandler(internal, 180, 21, 184) {
			}));
			this.customSlots.put(181, this.addSlot(new SlotItemHandler(internal, 181, 39, 184) {
			}));
			this.customSlots.put(182, this.addSlot(new SlotItemHandler(internal, 182, 57, 184) {
			}));
			this.customSlots.put(183, this.addSlot(new SlotItemHandler(internal, 183, 75, 184) {
			}));
			this.customSlots.put(184, this.addSlot(new SlotItemHandler(internal, 184, 93, 184) {
			}));
			this.customSlots.put(185, this.addSlot(new SlotItemHandler(internal, 185, 3, 202) {
			}));
			this.customSlots.put(186, this.addSlot(new SlotItemHandler(internal, 186, 21, 202) {
			}));
			this.customSlots.put(187, this.addSlot(new SlotItemHandler(internal, 187, 39, 202) {
			}));
			this.customSlots.put(188, this.addSlot(new SlotItemHandler(internal, 188, 57, 202) {
			}));
			this.customSlots.put(189, this.addSlot(new SlotItemHandler(internal, 189, 75, 202) {
			}));
			this.customSlots.put(190, this.addSlot(new SlotItemHandler(internal, 190, 93, 202) {
			}));
			this.customSlots.put(191, this.addSlot(new SlotItemHandler(internal, 191, 309, 130) {
			}));
			this.customSlots.put(192, this.addSlot(new SlotItemHandler(internal, 192, 327, 130) {
			}));
			this.customSlots.put(193, this.addSlot(new SlotItemHandler(internal, 193, 345, 130) {
			}));
			this.customSlots.put(194, this.addSlot(new SlotItemHandler(internal, 194, 363, 130) {
			}));
			this.customSlots.put(195, this.addSlot(new SlotItemHandler(internal, 195, 381, 130) {
			}));
			this.customSlots.put(196, this.addSlot(new SlotItemHandler(internal, 196, 399, 130) {
			}));
			this.customSlots.put(197, this.addSlot(new SlotItemHandler(internal, 197, 309, 148) {
			}));
			this.customSlots.put(198, this.addSlot(new SlotItemHandler(internal, 198, 327, 148) {
			}));
			this.customSlots.put(199, this.addSlot(new SlotItemHandler(internal, 199, 345, 148) {
			}));
			this.customSlots.put(200, this.addSlot(new SlotItemHandler(internal, 200, 363, 148) {
			}));
			this.customSlots.put(201, this.addSlot(new SlotItemHandler(internal, 201, 381, 148) {
			}));
			this.customSlots.put(202, this.addSlot(new SlotItemHandler(internal, 202, 399, 148) {
			}));
			this.customSlots.put(203, this.addSlot(new SlotItemHandler(internal, 203, 309, 166) {
			}));
			this.customSlots.put(204, this.addSlot(new SlotItemHandler(internal, 204, 327, 166) {
			}));
			this.customSlots.put(205, this.addSlot(new SlotItemHandler(internal, 205, 345, 166) {
			}));
			this.customSlots.put(206, this.addSlot(new SlotItemHandler(internal, 206, 363, 166) {
			}));
			this.customSlots.put(207, this.addSlot(new SlotItemHandler(internal, 207, 381, 166) {
			}));
			this.customSlots.put(208, this.addSlot(new SlotItemHandler(internal, 208, 399, 166) {
			}));
			this.customSlots.put(209, this.addSlot(new SlotItemHandler(internal, 209, 309, 184) {
			}));
			this.customSlots.put(210, this.addSlot(new SlotItemHandler(internal, 210, 327, 184) {
			}));
			this.customSlots.put(211, this.addSlot(new SlotItemHandler(internal, 211, 345, 184) {
			}));
			this.customSlots.put(212, this.addSlot(new SlotItemHandler(internal, 212, 363, 184) {
			}));
			this.customSlots.put(213, this.addSlot(new SlotItemHandler(internal, 213, 381, 184) {
			}));
			this.customSlots.put(214, this.addSlot(new SlotItemHandler(internal, 214, 399, 184) {
			}));
			this.customSlots.put(215, this.addSlot(new SlotItemHandler(internal, 215, 309, 202) {
				@Override
				public boolean canTakeStack(PlayerEntity player) {
					return false;
				}

				@Override
				public boolean isItemValid(ItemStack stack) {
					return false;
				}
			}));
			int si;
			int sj;
			for (si = 0; si < 3; ++si)
				for (sj = 0; sj < 9; ++sj)
					this.addSlot(new Slot(inv, sj + (si + 1) * 9, 122 + 8 + sj * 18, 65 + 84 + si * 18));
			for (si = 0; si < 9; ++si)
				this.addSlot(new Slot(inv, si, 122 + 8 + si * 18, 65 + 142));
		}

		public Map<Integer, Slot> get() {
			return customSlots;
		}

		@Override
		public boolean canInteractWith(PlayerEntity player) {
			return true;
		}

		@Override
		public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
			ItemStack itemstack = ItemStack.EMPTY;
			Slot slot = (Slot) this.inventorySlots.get(index);
			if (slot != null && slot.getHasStack()) {
				ItemStack itemstack1 = slot.getStack();
				itemstack = itemstack1.copy();
				if (index < 216) {
					if (!this.mergeItemStack(itemstack1, 216, this.inventorySlots.size(), true)) {
						return ItemStack.EMPTY;
					}
					slot.onSlotChange(itemstack1, itemstack);
				} else if (!this.mergeItemStack(itemstack1, 0, 216, false)) {
					if (index < 216 + 27) {
						if (!this.mergeItemStack(itemstack1, 216 + 27, this.inventorySlots.size(), true)) {
							return ItemStack.EMPTY;
						}
					} else {
						if (!this.mergeItemStack(itemstack1, 216, 216 + 27, false)) {
							return ItemStack.EMPTY;
						}
					}
					return ItemStack.EMPTY;
				}
				if (itemstack1.getCount() == 0) {
					slot.putStack(ItemStack.EMPTY);
				} else {
					slot.onSlotChanged();
				}
				if (itemstack1.getCount() == itemstack.getCount()) {
					return ItemStack.EMPTY;
				}
				slot.onTake(playerIn, itemstack1);
			}
			return itemstack;
		}

		@Override /**
					 * Merges provided ItemStack with the first avaliable one in the
					 * container/player inventor between minIndex (included) and maxIndex
					 * (excluded). Args : stack, minIndex, maxIndex, negativDirection. /!\ the
					 * Container implementation do not check if the item is valid for the slot
					 */
		protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {
			boolean flag = false;
			int i = startIndex;
			if (reverseDirection) {
				i = endIndex - 1;
			}
			if (stack.isStackable()) {
				while (!stack.isEmpty()) {
					if (reverseDirection) {
						if (i < startIndex) {
							break;
						}
					} else if (i >= endIndex) {
						break;
					}
					Slot slot = this.inventorySlots.get(i);
					ItemStack itemstack = slot.getStack();
					if (slot.isItemValid(itemstack) && !itemstack.isEmpty() && areItemsAndTagsEqual(stack, itemstack)) {
						int j = itemstack.getCount() + stack.getCount();
						int maxSize = Math.min(slot.getSlotStackLimit(), stack.getMaxStackSize());
						if (j <= maxSize) {
							stack.setCount(0);
							itemstack.setCount(j);
							slot.putStack(itemstack);
							flag = true;
						} else if (itemstack.getCount() < maxSize) {
							stack.shrink(maxSize - itemstack.getCount());
							itemstack.setCount(maxSize);
							slot.putStack(itemstack);
							flag = true;
						}
					}
					if (reverseDirection) {
						--i;
					} else {
						++i;
					}
				}
			}
			if (!stack.isEmpty()) {
				if (reverseDirection) {
					i = endIndex - 1;
				} else {
					i = startIndex;
				}
				while (true) {
					if (reverseDirection) {
						if (i < startIndex) {
							break;
						}
					} else if (i >= endIndex) {
						break;
					}
					Slot slot1 = this.inventorySlots.get(i);
					ItemStack itemstack1 = slot1.getStack();
					if (itemstack1.isEmpty() && slot1.isItemValid(stack)) {
						if (stack.getCount() > slot1.getSlotStackLimit()) {
							slot1.putStack(stack.split(slot1.getSlotStackLimit()));
						} else {
							slot1.putStack(stack.split(stack.getCount()));
						}
						slot1.onSlotChanged();
						flag = true;
						break;
					}
					if (reverseDirection) {
						--i;
					} else {
						++i;
					}
				}
			}
			return flag;
		}

		@Override
		public void onContainerClosed(PlayerEntity playerIn) {
			super.onContainerClosed(playerIn);
			if (!bound && (playerIn instanceof ServerPlayerEntity)) {
				if (!playerIn.isAlive() || playerIn instanceof ServerPlayerEntity && ((ServerPlayerEntity) playerIn).hasDisconnected()) {
					for (int j = 0; j < internal.getSlots(); ++j) {
						playerIn.dropItem(internal.extractItem(j, internal.getStackInSlot(j).getCount(), false), false);
					}
				} else {
					for (int i = 0; i < internal.getSlots(); ++i) {
						playerIn.inventory.placeItemBackInInventory(playerIn.world,
								internal.extractItem(i, internal.getStackInSlot(i).getCount(), false));
					}
				}
			}
		}

		private void slotChanged(int slotid, int ctype, int meta) {
			if (this.world != null && this.world.isRemote) {
				TempusMod.PACKET_HANDLER.sendToServer(new GUISlotChangedMessage(slotid, x, y, z, ctype, meta));
				handleSlotAction(entity, slotid, ctype, meta, x, y, z);
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class GuiWindow extends ContainerScreen<GuiContainerMod> {
		private World world;
		private int x, y, z;
		private PlayerEntity entity;
		public GuiWindow(GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
			super(container, inventory, text);
			this.world = container.world;
			this.x = container.x;
			this.y = container.y;
			this.z = container.z;
			this.entity = container.entity;
			this.xSize = 420;
			this.ySize = 230;
		}
		private static final ResourceLocation texture = new ResourceLocation("tempus:textures/huge_crate.png");
		@Override
		public void render(int mouseX, int mouseY, float partialTicks) {
			this.renderBackground();
			super.render(mouseX, mouseY, partialTicks);
			this.renderHoveredToolTip(mouseX, mouseY);
		}

		@Override
		protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
			GL11.glColor4f(1, 1, 1, 1);
			Minecraft.getInstance().getTextureManager().bindTexture(texture);
			int k = (this.width - this.xSize) / 2;
			int l = (this.height - this.ySize) / 2;
			this.blit(k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
		}

		@Override
		public boolean keyPressed(int key, int b, int c) {
			if (key == 256) {
				this.minecraft.player.closeScreen();
				return true;
			}
			return super.keyPressed(key, b, c);
		}

		@Override
		public void tick() {
			super.tick();
		}

		@Override
		protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		}

		@Override
		public void removed() {
			super.removed();
			Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
		}

		@Override
		public void init(Minecraft minecraft, int width, int height) {
			super.init(minecraft, width, height);
			minecraft.keyboardListener.enableRepeatEvents(true);
		}
	}

	public static class ButtonPressedMessage {
		int buttonID, x, y, z;
		public ButtonPressedMessage(PacketBuffer buffer) {
			this.buttonID = buffer.readInt();
			this.x = buffer.readInt();
			this.y = buffer.readInt();
			this.z = buffer.readInt();
		}

		public ButtonPressedMessage(int buttonID, int x, int y, int z) {
			this.buttonID = buttonID;
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public static void buffer(ButtonPressedMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.buttonID);
			buffer.writeInt(message.x);
			buffer.writeInt(message.y);
			buffer.writeInt(message.z);
		}

		public static void handler(ButtonPressedMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				PlayerEntity entity = context.getSender();
				int buttonID = message.buttonID;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleButtonAction(entity, buttonID, x, y, z);
			});
			context.setPacketHandled(true);
		}
	}

	public static class GUISlotChangedMessage {
		int slotID, x, y, z, changeType, meta;
		public GUISlotChangedMessage(int slotID, int x, int y, int z, int changeType, int meta) {
			this.slotID = slotID;
			this.x = x;
			this.y = y;
			this.z = z;
			this.changeType = changeType;
			this.meta = meta;
		}

		public GUISlotChangedMessage(PacketBuffer buffer) {
			this.slotID = buffer.readInt();
			this.x = buffer.readInt();
			this.y = buffer.readInt();
			this.z = buffer.readInt();
			this.changeType = buffer.readInt();
			this.meta = buffer.readInt();
		}

		public static void buffer(GUISlotChangedMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.slotID);
			buffer.writeInt(message.x);
			buffer.writeInt(message.y);
			buffer.writeInt(message.z);
			buffer.writeInt(message.changeType);
			buffer.writeInt(message.meta);
		}

		public static void handler(GUISlotChangedMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				PlayerEntity entity = context.getSender();
				int slotID = message.slotID;
				int changeType = message.changeType;
				int meta = message.meta;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleSlotAction(entity, slotID, changeType, meta, x, y, z);
			});
			context.setPacketHandled(true);
		}
	}
	private static void handleButtonAction(PlayerEntity entity, int buttonID, int x, int y, int z) {
		World world = entity.world;
		// security measure to prevent arbitrary chunk generation
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
	}

	private static void handleSlotAction(PlayerEntity entity, int slotID, int changeType, int meta, int x, int y, int z) {
		World world = entity.world;
		// security measure to prevent arbitrary chunk generation
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
	}
}
