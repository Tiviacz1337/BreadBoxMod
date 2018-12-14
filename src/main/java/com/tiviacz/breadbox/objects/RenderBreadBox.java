package com.tiviacz.breadbox.objects;

import org.lwjgl.opengl.GL11;

import com.tiviacz.breadbox.BreadBox;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.ForgeHooksClient;

public class RenderBreadBox extends TileEntitySpecialRenderer<TileEntityBreadBox>
{
	private static final ItemStack stack = new ItemStack(Items.BREAD);

	@Override
    public void render(TileEntityBreadBox te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
		if(te.breadCount != 0)
		{
			IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, te.getWorld(), null);
			model = ForgeHooksClient.handleCameraTransforms(model, TransformType.NONE, false);
			
			Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			
			GlStateManager.enableRescaleNormal();
			GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f);
			GlStateManager.enableBlend();
			RenderHelper.enableStandardItemLighting();
			GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
			
			IBlockState state = te.getWorld().getBlockState(te.getPos());
			
			if(isBreadBox(state) && state.getValue(BlockBreadBox.FACING) == EnumFacing.NORTH)
			{
				if(te.breadCount >= 1)
				{
					GlStateManager.pushMatrix();
					GlStateManager.translate(x + 0.45, y + 0.032, z + 0.49);
					GlStateManager.rotate(90F, 1, 0, 0);
					GlStateManager.rotate(135F, 0, 0, 1);
					GlStateManager.scale(0.7, 0.7, 0.7);
					Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
					GlStateManager.popMatrix();
					
					if(te.breadCount >= 2)
					{
						GlStateManager.pushMatrix();
						GlStateManager.translate(x + 0.48, y + 0.0765, z + 0.5);
						GlStateManager.rotate(90F, 1, 0, 0);
						GlStateManager.rotate(135F, 0, 0, 1);
						GlStateManager.scale(0.7, 0.7, 0.7);
						Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
						GlStateManager.popMatrix();
						
						if(te.breadCount >= 3)
						{
							GlStateManager.pushMatrix();
							GlStateManager.translate(x + 0.46, y + 0.121, z + 0.52);
							GlStateManager.rotate(90F, 1, 0, 0);
							GlStateManager.rotate(128F, 0, 0, 1);
							GlStateManager.scale(0.7, 0.7, 0.7);	
							Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
							GlStateManager.popMatrix();
						
							if(te.breadCount == 4)
							{
								GlStateManager.pushMatrix();
								GlStateManager.translate(x + 0.46, y + 0.1646, z + 0.54);
								GlStateManager.rotate(90F, 1, 0, 0);
								GlStateManager.rotate(135F, 0, 0, 1);
								GlStateManager.scale(0.7, 0.7, 0.7);
								Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
								GlStateManager.popMatrix();
							}
						}
					}
				}
			}
			
			if(isBreadBox(state) && state.getValue(BlockBreadBox.FACING) == EnumFacing.SOUTH)
			{
				if(te.breadCount >= 1)
				{
					GlStateManager.pushMatrix();
					GlStateManager.translate(x + 0.54, y + 0.032, z + 0.52);
					GlStateManager.rotate(90F, 1, 0, 0);
					GlStateManager.rotate(320F, 0, 0, 1);
					GlStateManager.scale(0.7, 0.7, 0.7);
					Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
					GlStateManager.popMatrix();
					
					if(te.breadCount >= 2)
					{
						GlStateManager.pushMatrix();
						GlStateManager.translate(x + 0.51, y + 0.0765, z + 0.52);
						GlStateManager.rotate(90F, 1, 0, 0);
						GlStateManager.rotate(320F, 0, 0, 1);
						GlStateManager.scale(0.7, 0.7, 0.7);
						Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
						GlStateManager.popMatrix();
						
						if(te.breadCount >= 3)
						{
							GlStateManager.pushMatrix();
							GlStateManager.translate(x + 0.53, y + 0.121, z + 0.495);
							GlStateManager.rotate(90F, 1, 0, 0);
							GlStateManager.rotate(313F, 0, 0, 1);
							GlStateManager.scale(0.7, 0.7, 0.7);	
							Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
							GlStateManager.popMatrix();
						
							if(te.breadCount == 4)
							{
								GlStateManager.pushMatrix();
								GlStateManager.translate(x + 0.53, y + 0.1646, z + 0.47);
								GlStateManager.rotate(90F, 1, 0, 0);
								GlStateManager.rotate(320F, 0, 0, 1);
								GlStateManager.scale(0.7, 0.7, 0.7);
								Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
								GlStateManager.popMatrix();
							}
						}
					}
				}
			}
			
			if(isBreadBox(state) && state.getValue(BlockBreadBox.FACING) == EnumFacing.WEST)
			{
				if(te.breadCount >= 1)
				{
					GlStateManager.pushMatrix();
					GlStateManager.translate(x + 0.49, y + 0.032, z + 0.55);
					GlStateManager.rotate(90F, 1, 0, 0);
					GlStateManager.rotate(40F, 0, 0, 1);
					GlStateManager.scale(0.7, 0.7, 0.7);
					Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
					GlStateManager.popMatrix();
					
					if(te.breadCount >= 2)
					{
						GlStateManager.pushMatrix();
						GlStateManager.translate(x + 0.5, y + 0.0765, z + 0.57);
						GlStateManager.rotate(90F, 1, 0, 0);
						GlStateManager.rotate(40F, 0, 0, 1);
						GlStateManager.scale(0.7, 0.7, 0.7);
						Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
						GlStateManager.popMatrix();
						
						if(te.breadCount >= 3)
						{
							GlStateManager.pushMatrix();
							GlStateManager.translate(x + 0.5325, y + 0.121, z + 0.53);
							GlStateManager.rotate(90F, 1, 0, 0);
							GlStateManager.rotate(33F, 0, 0, 1);
							GlStateManager.scale(0.7, 0.7, 0.7);	
							Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
							GlStateManager.popMatrix();
						
							if(te.breadCount == 4)
							{
								GlStateManager.pushMatrix();
								GlStateManager.translate(x + 0.515, y + 0.1646, z + 0.54);
								GlStateManager.rotate(90F, 1, 0, 0);
								GlStateManager.rotate(40F, 0, 0, 1);
								GlStateManager.scale(0.7, 0.7, 0.7);
								Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
								GlStateManager.popMatrix();
							}
						}
					}
				}
			}
			
			if(isBreadBox(state) && state.getValue(BlockBreadBox.FACING) == EnumFacing.EAST)
			{
				if(te.breadCount >= 1)
				{
					GlStateManager.pushMatrix();
					GlStateManager.translate(x + 0.51, y + 0.032, z + 0.45);
					GlStateManager.rotate(90F, 1, 0, 0);
					GlStateManager.rotate(220F, 0, 0, 1);
					GlStateManager.scale(0.7, 0.7, 0.7);
					Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
					GlStateManager.popMatrix();
					
					if(te.breadCount >= 2)
					{
						GlStateManager.pushMatrix();
						GlStateManager.translate(x + 0.49, y + 0.0765, z + 0.47);
						GlStateManager.rotate(90F, 1, 0, 0);
						GlStateManager.rotate(223F, 0, 0, 1);
						GlStateManager.scale(0.7, 0.7, 0.7);
						Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
						GlStateManager.popMatrix();
						
						if(te.breadCount >= 3)
						{
							GlStateManager.pushMatrix();
							GlStateManager.translate(x + 0.4675, y + 0.121, z + 0.44);
							GlStateManager.rotate(90F, 1, 0, 0);
							GlStateManager.rotate(213F, 0, 0, 1);
							GlStateManager.scale(0.7, 0.7, 0.7);	
							Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
							GlStateManager.popMatrix();
						
							if(te.breadCount == 4)
							{
								GlStateManager.pushMatrix();
								GlStateManager.translate(x + 0.485, y + 0.1646, z + 0.45);
								GlStateManager.rotate(90F, 1, 0, 0);
								GlStateManager.rotate(220F, 0, 0, 1);
								GlStateManager.scale(0.7, 0.7, 0.7);
								Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
								GlStateManager.popMatrix();
							}
						}
					}
				}
			}
			
			GlStateManager.disableRescaleNormal();
			GlStateManager.disableBlend();
		}
	}
	
	private boolean isBreadBox(IBlockState state)
	{
		if(state.getBlock() == BreadBox.BREAD_BOX_OAK || state.getBlock() == BreadBox.BREAD_BOX_SPRUCE || state.getBlock() == BreadBox.BREAD_BOX_BIRCH || state.getBlock() == BreadBox.BREAD_BOX_JUNGLE || state.getBlock() == BreadBox.BREAD_BOX_ACACIA || state.getBlock() == BreadBox.BREAD_BOX_DARK)
		{
			return true;
		}
		return false;
	}
}