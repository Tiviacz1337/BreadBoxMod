package com.tiviacz.breadbox.init;

import java.util.ArrayList;
import java.util.List;

import com.tiviacz.breadbox.objects.BlockBreadBox;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();

	public static final Block BREAD_BOX_OAK = new BlockBreadBox("bread_box_oak", Material.WOOD);
	public static final Block BREAD_BOX_SPRUCE = new BlockBreadBox("bread_box_spruce", Material.WOOD);
	public static final Block BREAD_BOX_BIRCH = new BlockBreadBox("bread_box_birch", Material.WOOD);
	public static final Block BREAD_BOX_JUNGLE = new BlockBreadBox("bread_box_jungle", Material.WOOD);
	public static final Block BREAD_BOX_ACACIA = new BlockBreadBox("bread_box_acacia", Material.WOOD);
	public static final Block BREAD_BOX_DARK = new BlockBreadBox("bread_box_dark", Material.WOOD); 
}