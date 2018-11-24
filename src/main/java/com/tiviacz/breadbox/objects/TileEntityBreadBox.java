package com.tiviacz.breadbox.objects;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityBreadBox extends TileEntity
{
	public int breadCount = 0;
	public int maxBreadCount = 4;

	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
	    return new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
	    handleUpdateTag(pkt.getNbtCompound());
	}

	@Override
	public NBTTagCompound getUpdateTag()
	{
	    NBTTagCompound tag = super.getUpdateTag();
	    
	    tag.setInteger("BreadCount", this.breadCount);
	    
	    return tag;
	}
	
	@Override
	public void handleUpdateTag(NBTTagCompound tag)
    {
		super.handleUpdateTag(tag);
		this.breadCount = tag.getInteger("BreadCount");
    } 
	
	public boolean addBread()
	{
		if(breadCount < maxBreadCount)
		{
			breadCount++;
	//		System.out.println("Bread has been added, current bread count is " + this.breadCount);
			this.markDirty();
			return true;
		}
		return false;
	}
	
	public boolean removeBread()
	{
		if(breadCount > 0)
		{
			breadCount--;
	//		System.out.println("Bread has been taken, current bread count is " + this.breadCount);
			this.markDirty();
			return true;
		}
		return false;
	}
	
	public boolean isFull()
	{
		if(breadCount == maxBreadCount)
		{
	//		System.out.println("Bread box is full! Current count is " + this.breadCount);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean isEmpty()
	{
		if(breadCount == 0)
		{
	//		System.out.println("Bread box is empty!");
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate)
    {
        return (oldState.getBlock() != newSate.getBlock());
    }
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setInteger("BreadCount", this.breadCount);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.breadCount = compound.getInteger("BreadCount");
	}
	
	private void notifyBlockUpdate() 
	{
		final IBlockState state = getWorld().getBlockState(getPos());
		getWorld().notifyBlockUpdate(getPos(), state, state, 3);
	}
	
	@Override
	public void markDirty() 
	{
		super.markDirty();
		notifyBlockUpdate();
	} 
}