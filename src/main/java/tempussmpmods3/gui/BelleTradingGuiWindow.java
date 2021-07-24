
package tempussmpmods3.gui;

import tempussmpmods3.TempusMod;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class BelleTradingGuiWindow extends ContainerScreen<BelleTradingGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	public BelleTradingGuiWindow(BelleTradingGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 176;
		this.ySize = 166;
	}
	private static final ResourceLocation texture = new ResourceLocation("tempus:textures/belle_trading.png");
	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.color4f(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.blit(ms, k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("tempus:textures/holierbible.png"));
		this.blit(ms, this.guiLeft + 15, this.guiTop + 79, 0, 0, 75, 75, 75, 75);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("tempus:textures/bellebathwater.png"));
		this.blit(ms, this.guiLeft + 33, this.guiTop + 16, 0, 0, 256, 256, 256, 256);
		RenderSystem.disableBlend();
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
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "Belle Delphine Store", 33, 5, -65281);
		this.font.drawString(ms, "1 Temporium Each", 20, 65, -10092442);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
	}

	@Override
	public void init(Minecraft minecraft, int width, int height) {
		super.init(minecraft, width, height);
		minecraft.keyboardListener.enableRepeatEvents(true);
		this.addButton(new Button(this.guiLeft + 105, this.guiTop + 25, 30, 20, new StringTextComponent("Buy"), e -> {
			if (true) {
				TempusMod.PACKET_HANDLER.sendToServer(new BelleTradingGui.ButtonPressedMessage(0, x, y, z));
				BelleTradingGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 105, this.guiTop + 106, 30, 20, new StringTextComponent("Buy"), e -> {
			if (true) {
				TempusMod.PACKET_HANDLER.sendToServer(new BelleTradingGui.ButtonPressedMessage(1, x, y, z));
				BelleTradingGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
	}
}
