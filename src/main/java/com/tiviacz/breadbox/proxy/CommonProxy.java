package com.tiviacz.breadbox.proxy;

import com.tiviacz.breadbox.BreadBox;
import com.tiviacz.breadbox.handlers.GuiHandler;
import com.tiviacz.breadbox.objects.tileentity.TileEntityBreadBox;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy 
{
	public void init(FMLInitializationEvent event)
	{
		GameRegistry.registerTileEntity(TileEntityBreadBox.class, new ResourceLocation(BreadBox.MODID + ":bread_box"));
		NetworkRegistry.INSTANCE.registerGuiHandler(BreadBox.INSTANCE, new GuiHandler());
	}
	
	public void registerModel(Item item, int metadata)
	{
		
	}
}