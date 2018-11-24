package com.tiviacz.breadbox;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.tiviacz.breadbox.objects.BlockBreadBox;
import com.tiviacz.breadbox.objects.RenderBreadBox;
import com.tiviacz.breadbox.objects.TileEntityBreadBox;

@Mod(modid = BreadBox.MODID, name = BreadBox.NAME, version = BreadBox.VERSION, updateJSON = "https://gist.githubusercontent.com/Tiviacz1337/b916e3981957f1e6f2de99ea0aa328fa/raw/4a1b4e905a3a8fd10922e9ccd97abac3f282e222/PizzaCraftUpdateJSON.json")
@EventBusSubscriber
public class BreadBox
{
    public static final String MODID = "breadbox";
    public static final String NAME = "Bread Box";
    public static final String VERSION = "1.0";
    
    public static final Block BREAD_BOX = new BlockBreadBox("bread_box", Material.WOOD);
    
    @SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(BREAD_BOX);
	}
    
    @SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().register(new ItemBlock(BREAD_BOX).setRegistryName(BREAD_BOX.getRegistryName()));
	}
    
    @SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
    	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BREAD_BOX), 0, new ModelResourceLocation(BREAD_BOX.getRegistryName(), "inventory"));	
	}

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	GameRegistry.registerTileEntity(TileEntityBreadBox.class, new ResourceLocation(BreadBox.MODID + ":bread_box"));
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBreadBox.class, new RenderBreadBox());
    }
}