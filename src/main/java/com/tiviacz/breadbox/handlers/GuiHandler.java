package com.tiviacz.breadbox.handlers;

import com.tiviacz.breadbox.client.GuiBreadBox;
import com.tiviacz.breadbox.objects.container.ContainerBreadBox;
import com.tiviacz.breadbox.objects.tileentity.TileEntityBreadBox;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		final TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		
		if(ID == ConfigHandler.breadBoxGuiID) return new ContainerBreadBox(player.inventory, (TileEntityBreadBox)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if(ID == ConfigHandler.breadBoxGuiID) return new GuiBreadBox(player.inventory, (TileEntityBreadBox)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}
}