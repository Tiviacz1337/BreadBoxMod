package com.tiviacz.breadbox.objects.container.slot;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class BreadBoxSlot extends SlotItemHandler
{
	public BreadBoxSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) 
	{
		super(itemHandler, index, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(ItemStack stack)
    {
        if(stack.getItem() == Items.BREAD)
        {
        	return true;
        }
		return false;
    }
}