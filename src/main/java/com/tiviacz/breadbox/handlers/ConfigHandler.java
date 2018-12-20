package com.tiviacz.breadbox.handlers;

import com.tiviacz.breadbox.BreadBox;

import net.minecraftforge.common.config.Config;

@Config(modid=BreadBox.MODID)
public class ConfigHandler
{
	@Config.Comment("Gui ID for Bread Box")
	public static int breadBoxGuiID = 43;
}