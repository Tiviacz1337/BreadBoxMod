package com.tiviacz.breadbox;

import com.tiviacz.breadbox.init.ModBlocks;
import com.tiviacz.breadbox.proxy.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = BreadBox.MODID, name = BreadBox.NAME, version = BreadBox.VERSION, updateJSON = "https://gist.githubusercontent.com/Tiviacz1337/b916e3981957f1e6f2de99ea0aa328fa/raw/4a1b4e905a3a8fd10922e9ccd97abac3f282e222/PizzaCraftUpdateJSON.json")
@EventBusSubscriber
public class BreadBox
{
    public static final String MODID = "breadbox";
    public static final String NAME = "Bread Box";
    public static final String VERSION = "1.2";
    
    @Instance
	public static BreadBox INSTANCE;
    
    @SidedProxy(clientSide = "com.tiviacz.breadbox.proxy.ClientProxy", serverSide = "com.tiviacz.breadbox.proxy.CommonProxy")
	public static CommonProxy proxy;
    
    @SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
    	event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
	}
    
    @SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
    	for(Block block : ModBlocks.BLOCKS)
    	{
    		event.getRegistry().register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    	}
	}
    
    @SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
    	for(Block block : ModBlocks.BLOCKS)
    	{
    		proxy.registerModel(Item.getItemFromBlock(block), 0);
    	}
	}
    
    @SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if(event.getModID().equals(BreadBox.MODID))
        {
            ConfigManager.sync(BreadBox.MODID, Config.Type.INSTANCE);
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init(event);
    }
}