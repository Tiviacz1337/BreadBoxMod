package com.tiviacz.breadbox.client;

import com.tiviacz.breadbox.BreadBox;
import com.tiviacz.breadbox.objects.container.ContainerBreadBox;
import com.tiviacz.breadbox.objects.tileentity.TileEntityBreadBox;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiBreadBox extends GuiContainer
{
	private static final ResourceLocation GUI_CHEST = new ResourceLocation(BreadBox.MODID + ":textures/gui/bread_box.png");
	private final InventoryPlayer playerInventory;
	private final TileEntityBreadBox te;
	
	public GuiBreadBox(InventoryPlayer playerInventory, TileEntityBreadBox chestInventory) 
	{
		super(new ContainerBreadBox(playerInventory, chestInventory));
		this.playerInventory = playerInventory;
		this.te = chestInventory;
		
		this.xSize = 176;
		this.ySize = 127;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
//		this.fontRenderer.drawString(this.te.getDisplayName().getUnformattedText(), 8, 6, 4210752);
//		this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 92, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(GUI_CHEST);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
}