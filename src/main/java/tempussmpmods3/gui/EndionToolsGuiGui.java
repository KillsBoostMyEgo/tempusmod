
package tempussmpmods3.gui;

import tempussmpmods3.procedures.EndionShitNBTProcedure;

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
public class EndionToolsGuiGui extends TempusModElements.ModElement {
	public static HashMap guistate = new HashMap();
	private static ContainerType<GuiContainerMod> containerType = null;
	public EndionToolsGuiGui(TempusModElements instance) {
		super(instance, 59);
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
		event.getRegistry().register(containerType.setRegistryName("endion_tools_gui"));
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
			this.internal = new ItemStackHandler(36);
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
			this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 7, 8) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(0, 0, 0);
				}
			}));
			this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 25, 8) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(1, 0, 0);
				}
			}));
			this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, 43, 8) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(2, 0, 0);
				}
			}));
			this.customSlots.put(3, this.addSlot(new SlotItemHandler(internal, 3, 61, 8) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(3, 0, 0);
				}
			}));
			this.customSlots.put(4, this.addSlot(new SlotItemHandler(internal, 4, 79, 8) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(4, 0, 0);
				}
			}));
			this.customSlots.put(5, this.addSlot(new SlotItemHandler(internal, 5, 97, 8) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(5, 0, 0);
				}
			}));
			this.customSlots.put(6, this.addSlot(new SlotItemHandler(internal, 6, 115, 8) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(6, 0, 0);
				}
			}));
			this.customSlots.put(7, this.addSlot(new SlotItemHandler(internal, 7, 133, 8) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(7, 0, 0);
				}
			}));
			this.customSlots.put(8, this.addSlot(new SlotItemHandler(internal, 8, 151, 8) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(8, 0, 0);
				}
			}));
			this.customSlots.put(9, this.addSlot(new SlotItemHandler(internal, 9, 7, 26) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(9, 0, 0);
				}
			}));
			this.customSlots.put(10, this.addSlot(new SlotItemHandler(internal, 10, 25, 26) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(10, 0, 0);
				}
			}));
			this.customSlots.put(11, this.addSlot(new SlotItemHandler(internal, 11, 43, 26) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(11, 0, 0);
				}
			}));
			this.customSlots.put(12, this.addSlot(new SlotItemHandler(internal, 12, 61, 26) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(12, 0, 0);
				}
			}));
			this.customSlots.put(13, this.addSlot(new SlotItemHandler(internal, 13, 79, 26) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(13, 0, 0);
				}
			}));
			this.customSlots.put(14, this.addSlot(new SlotItemHandler(internal, 14, 97, 26) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(14, 0, 0);
				}
			}));
			this.customSlots.put(15, this.addSlot(new SlotItemHandler(internal, 15, 115, 26) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(15, 0, 0);
				}
			}));
			this.customSlots.put(16, this.addSlot(new SlotItemHandler(internal, 16, 133, 26) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(16, 0, 0);
				}
			}));
			this.customSlots.put(17, this.addSlot(new SlotItemHandler(internal, 17, 151, 26) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(17, 0, 0);
				}
			}));
			this.customSlots.put(18, this.addSlot(new SlotItemHandler(internal, 18, 7, 44) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(18, 0, 0);
				}
			}));
			this.customSlots.put(19, this.addSlot(new SlotItemHandler(internal, 19, 25, 44) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(19, 0, 0);
				}
			}));
			this.customSlots.put(20, this.addSlot(new SlotItemHandler(internal, 20, 43, 44) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(20, 0, 0);
				}
			}));
			this.customSlots.put(21, this.addSlot(new SlotItemHandler(internal, 21, 61, 44) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(21, 0, 0);
				}
			}));
			this.customSlots.put(22, this.addSlot(new SlotItemHandler(internal, 22, 79, 44) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(22, 0, 0);
				}
			}));
			this.customSlots.put(23, this.addSlot(new SlotItemHandler(internal, 23, 97, 44) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(23, 0, 0);
				}
			}));
			this.customSlots.put(24, this.addSlot(new SlotItemHandler(internal, 24, 115, 44) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(24, 0, 0);
				}
			}));
			this.customSlots.put(25, this.addSlot(new SlotItemHandler(internal, 25, 133, 44) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(25, 0, 0);
				}
			}));
			this.customSlots.put(26, this.addSlot(new SlotItemHandler(internal, 26, 151, 44) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(26, 0, 0);
				}
			}));
			this.customSlots.put(27, this.addSlot(new SlotItemHandler(internal, 27, 7, 62) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(27, 0, 0);
				}
			}));
			this.customSlots.put(28, this.addSlot(new SlotItemHandler(internal, 28, 25, 62) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(28, 0, 0);
				}
			}));
			this.customSlots.put(29, this.addSlot(new SlotItemHandler(internal, 29, 43, 62) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(29, 0, 0);
				}
			}));
			this.customSlots.put(30, this.addSlot(new SlotItemHandler(internal, 30, 61, 62) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(30, 0, 0);
				}
			}));
			this.customSlots.put(31, this.addSlot(new SlotItemHandler(internal, 31, 79, 62) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(31, 0, 0);
				}
			}));
			this.customSlots.put(32, this.addSlot(new SlotItemHandler(internal, 32, 97, 62) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(32, 0, 0);
				}
			}));
			this.customSlots.put(33, this.addSlot(new SlotItemHandler(internal, 33, 115, 62) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(33, 0, 0);
				}
			}));
			this.customSlots.put(34, this.addSlot(new SlotItemHandler(internal, 34, 133, 62) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(34, 0, 0);
				}
			}));
			this.customSlots.put(35, this.addSlot(new SlotItemHandler(internal, 35, 151, 62) {
				@Override
				public void onSlotChanged() {
					super.onSlotChanged();
					GuiContainerMod.this.slotChanged(35, 0, 0);
				}
			}));
			int si;
			int sj;
			for (si = 0; si < 3; ++si)
				for (sj = 0; sj < 9; ++sj)
					this.addSlot(new Slot(inv, sj + (si + 1) * 9, 0 + 8 + sj * 18, 0 + 84 + si * 18));
			for (si = 0; si < 9; ++si)
				this.addSlot(new Slot(inv, si, 0 + 8 + si * 18, 0 + 142));
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
				if (index < 36) {
					if (!this.mergeItemStack(itemstack1, 36, this.inventorySlots.size(), true)) {
						return ItemStack.EMPTY;
					}
					slot.onSlotChange(itemstack1, itemstack);
				} else if (!this.mergeItemStack(itemstack1, 0, 36, false)) {
					if (index < 36 + 27) {
						if (!this.mergeItemStack(itemstack1, 36 + 27, this.inventorySlots.size(), true)) {
							return ItemStack.EMPTY;
						}
					} else {
						if (!this.mergeItemStack(itemstack1, 36, 36 + 27, false)) {
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
						if (j == 0)
							continue;
						if (j == 1)
							continue;
						if (j == 2)
							continue;
						if (j == 3)
							continue;
						if (j == 4)
							continue;
						if (j == 5)
							continue;
						if (j == 6)
							continue;
						if (j == 7)
							continue;
						if (j == 8)
							continue;
						if (j == 9)
							continue;
						if (j == 10)
							continue;
						if (j == 11)
							continue;
						if (j == 12)
							continue;
						if (j == 13)
							continue;
						if (j == 14)
							continue;
						if (j == 15)
							continue;
						if (j == 16)
							continue;
						if (j == 17)
							continue;
						if (j == 18)
							continue;
						if (j == 19)
							continue;
						if (j == 20)
							continue;
						if (j == 21)
							continue;
						if (j == 22)
							continue;
						if (j == 23)
							continue;
						if (j == 24)
							continue;
						if (j == 25)
							continue;
						if (j == 26)
							continue;
						if (j == 27)
							continue;
						if (j == 28)
							continue;
						if (j == 29)
							continue;
						if (j == 30)
							continue;
						if (j == 31)
							continue;
						if (j == 32)
							continue;
						if (j == 33)
							continue;
						if (j == 34)
							continue;
						if (j == 35)
							continue;
						playerIn.dropItem(internal.extractItem(j, internal.getStackInSlot(j).getCount(), false), false);
					}
				} else {
					for (int i = 0; i < internal.getSlots(); ++i) {
						if (i == 0)
							continue;
						if (i == 1)
							continue;
						if (i == 2)
							continue;
						if (i == 3)
							continue;
						if (i == 4)
							continue;
						if (i == 5)
							continue;
						if (i == 6)
							continue;
						if (i == 7)
							continue;
						if (i == 8)
							continue;
						if (i == 9)
							continue;
						if (i == 10)
							continue;
						if (i == 11)
							continue;
						if (i == 12)
							continue;
						if (i == 13)
							continue;
						if (i == 14)
							continue;
						if (i == 15)
							continue;
						if (i == 16)
							continue;
						if (i == 17)
							continue;
						if (i == 18)
							continue;
						if (i == 19)
							continue;
						if (i == 20)
							continue;
						if (i == 21)
							continue;
						if (i == 22)
							continue;
						if (i == 23)
							continue;
						if (i == 24)
							continue;
						if (i == 25)
							continue;
						if (i == 26)
							continue;
						if (i == 27)
							continue;
						if (i == 28)
							continue;
						if (i == 29)
							continue;
						if (i == 30)
							continue;
						if (i == 31)
							continue;
						if (i == 32)
							continue;
						if (i == 33)
							continue;
						if (i == 34)
							continue;
						if (i == 35)
							continue;
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
			this.xSize = 176;
			this.ySize = 166;
		}
		private static final ResourceLocation texture = new ResourceLocation("tempus:textures/endion_tools_gui.png");
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
		if (slotID == 0 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 1 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 2 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 3 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 4 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 5 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 6 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 7 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 8 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 9 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 10 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 11 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 12 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 13 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 14 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 15 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 16 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 17 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 18 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 19 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 20 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 21 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 22 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 23 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 24 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 25 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 26 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 27 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 28 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 29 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 30 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 31 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 32 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 33 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 34 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
		if (slotID == 35 && changeType == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				EndionShitNBTProcedure.executeProcedure($_dependencies);
			}
		}
	}
}
