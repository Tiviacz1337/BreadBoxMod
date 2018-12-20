package com.tiviacz.breadbox.objects.tileentity;

import javax.annotation.Nullable;

import com.tiviacz.breadbox.BreadBox;
import com.tiviacz.breadbox.objects.container.ContainerBreadBox;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityBreadBox extends TileEntity
{	
	public ItemStackHandler inventory = new ItemStackHandler(4);
	
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) 
	{
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) 
	{
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability, facing);
	}
	
	 @Override
	 public NBTTagCompound writeToNBT(NBTTagCompound compound)
	 {
		 super.writeToNBT(compound);
		 compound.setTag("inventory", inventory.serializeNBT());
		 return compound;
	} 

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		inventory.deserializeNBT(compound.getCompoundTag("inventory"));
	}

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
	    
	    tag.setTag("inventory", inventory.serializeNBT());
	    
	    return tag;
	}
	
	@Override
	public void handleUpdateTag(NBTTagCompound tag)
    {
		super.handleUpdateTag(tag);
		inventory.deserializeNBT(tag.getCompoundTag("inventory"));
    } 
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate)
    {
        return (oldState.getBlock() != newSate.getBlock());
    }
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() 
	{
		return new AxisAlignedBB(getPos(), getPos().add(1, 1, 1));
	}
	/*
	
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
	} */
}