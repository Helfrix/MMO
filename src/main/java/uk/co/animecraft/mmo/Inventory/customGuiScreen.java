package uk.co.animecraft.mmo.Inventory;

import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.GuiScreenEvent.*;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class customGuiScreen extends GuiScreen
{

	protected static customItemRender itemRender = new customItemRender();

	/**
	 * Draws the screen and all the components in it.
	 */
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
	{
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}

	@Override
	public void drawDefaultBackground()
	{
		this.drawWorldBackground(0);
	}

	@Override
	public void drawWorldBackground(int p_146270_1_)
	{
		if (this.mc.theWorld != null)
		{
			//this.drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680);
		}
		else
		{
			this.drawBackground(p_146270_1_);
		}
	}

	@Override
	protected void renderToolTip(ItemStack p_146285_1_, int p_146285_2_, int p_146285_3_)
	{
		List list = p_146285_1_.getTooltip(this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips);

		for (int k = 0; k < list.size(); ++k)
		{
			if (k == 0)
			{
				list.set(k, p_146285_1_.getRarity().rarityColor + (String)list.get(k));
			}
			else
			{
				list.set(k, EnumChatFormatting.GRAY + (String)list.get(k));
			}
		}

		FontRenderer font = p_146285_1_.getItem().getFontRenderer(p_146285_1_);
		drawHoveringText(list, p_146285_2_, p_146285_3_, (font == null ? fontRendererObj : font));
	}
	
	protected void renderToolTipXP(FontRenderer fontRender,String str, int p_146285_2_, int p_146285_3_)
	{
		 //p_146285_1_.getTooltip(this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips);
		 List list = new ArrayList();
		 list.add(EnumChatFormatting.AQUA + str);
		 
		FontRenderer font = fontRender;
		drawHoveringText(list, p_146285_2_, p_146285_3_, (font == null ? fontRendererObj : font));
	}

}
