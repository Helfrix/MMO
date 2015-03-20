package uk.co.animecraft.mmo.Menus;

import java.util.Collection;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class RenderMenu extends GuiScreen {

	private Minecraft mc;

	public RenderMenu(Minecraft mc) {
		super();

		// We need this to invoke the render engine.
		this.mc = mc;
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void renderMenu(GuiOpenEvent event) {
				
		if (event.isCancelable() && event.gui instanceof GuiIngameMenu) {
			event.gui = new IngameMenu();
		}
		else if(event.isCancelable() && event.gui instanceof GuiContainer){
			//event.gui = new PlayerContainer(new PlayerInventory(mc.thePlayer.inventory));
		
		}
		
	}
	
}
