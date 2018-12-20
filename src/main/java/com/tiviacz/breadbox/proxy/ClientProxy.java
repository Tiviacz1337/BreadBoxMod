package com.tiviacz.breadbox.proxy;

import com.tiviacz.breadbox.client.RenderBreadBox;
import com.tiviacz.breadbox.objects.tileentity.TileEntityBreadBox;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy
{
	@Override
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBreadBox.class, new RenderBreadBox());
	}
	
	@Override
	public void registerModel(Item item, int metadata)
	{
		ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}