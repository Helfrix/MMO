package uk.co.animecraft.mmo.HUD;

import java.util.Collection;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.FoodStats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import uk.co.animecraft.mmo.Player.ExtendedPlayer;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHUD extends Gui {
	private Minecraft mc;

	public RenderHUD(Minecraft mc) {
		super();

		// We need this to invoke the render engine.
		this.mc = mc;
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void renderHUD(RenderGameOverlayEvent event) {

		float HUD_SCALE = 0.6f;

		if (event.isCancelable()
				&& (event.type == ElementType.HEALTH
						|| event.type == ElementType.EXPERIENCE
						|| event.type == ElementType.FOOD 
						|| event.type == ElementType.HOTBAR
						|| event.type == ElementType.ARMOR
						|| event.type == ElementType.AIR
						|| event.type == ElementType.BOSSHEALTH)) {
			event.setCanceled(true);
		}

		if (event.isCancelable() && event.type == ElementType.HEALTH) {

			GL11.glPushMatrix();

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_LIGHTING);
			this.mc.getTextureManager().bindTexture(
					new ResourceLocation("mmo", "textures/gui/CustomHUD.png"));

			// this.drawTexturedModalRect(2, 2, 0, 0, 182, 26);

			// GL11.glScalef(HUD_SCALE, HUD_SCALE, HUD_SCALE);

			float plrHealth = this.mc.thePlayer.getHealth();
			float plrMaxHealth = this.mc.thePlayer.getMaxHealth();

			float plrHealthPerc = plrHealth / plrMaxHealth;

			int healthLen = Math.round((plrHealth / plrMaxHealth) * 182);

			this.drawTexturedModalRect(4, 14, 0, 0, 182, 6); // Health Blank
			if (plrHealthPerc >= 0.5) {
				this.drawTexturedModalRect(4, 14, 0, 6, healthLen, 6); // Health
																		// Green
			} else if (plrHealthPerc < 0.5) {
				if (plrHealthPerc > 0.15) {
					this.drawTexturedModalRect(4, 14, 0, 12, healthLen, 6); // Health
																			// Orange
				} else {
					this.drawTexturedModalRect(4, 14, 0, 18, healthLen, 6); // Health
																			// Red
				}
			}

			this.drawTexturedModalRect(4, 21, 0, 36, 182, 11); // Level Holder

			ExtendedPlayer props = ExtendedPlayer.get(this.mc.thePlayer);
			float plrFood = props.getCurrentMagic();
			float plrMaxFood = props.getMaxMagic();
			float plrFoodPerc = (plrFood / plrMaxFood);
			
			/*FoodStats plrFoodObj = this.mc.thePlayer.getFoodStats();
			float plrFood = plrFoodObj.getFoodLevel();
			float plrMaxFood = plrFoodObj.getPrevFoodLevel();
			float plrFoodPerc = (plrFood / plrMaxFood);*/
			int foodLen = Math.round((plrFood / plrMaxFood) * 124);

			this.drawTexturedModalRect(4 + 58, 26, 0, 24, 124, 6); // Magic
																	// Blank
			this.drawTexturedModalRect(4 + 58, 26, 0, 30, foodLen, 6); // Magic
																		// Blue

			String PLR_NAME = this.mc.thePlayer.getDisplayName();
			this.mc.fontRenderer.drawString(PLR_NAME, 5, 5, 0xff000000);
			this.mc.fontRenderer.drawString("Level " + String.valueOf(props.getMainLevel()), 6, 23, 0xffFFFFFF);

			GL11.glPopMatrix();
		}
		
		if (event.isCancelable() && event.type == ElementType.HOTBAR) {
			// Hot Bar
			
			GL11.glPushMatrix();

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_LIGHTING);
			
			this.mc.getTextureManager().bindTexture(
					new ResourceLocation("mmo", "textures/gui/CustomHUD.png"));
			ScaledResolution scaledresolution = new ScaledResolution(this.mc,
					this.mc.displayWidth, this.mc.displayHeight);
			int sw = scaledresolution.getScaledWidth();
			int sh = scaledresolution.getScaledHeight();
			InventoryPlayer inventoryplayer = this.mc.thePlayer.inventory;

			
			GL11.glEnable(GL11.GL_BLEND);
            //OpenGlHelper.glBlendFunc(775, 769, 1, 0);
			
			if (inventoryplayer.currentItem == 0)
				this.drawTexturedModalRect(sw - 20 - 8, sh - 83 - 8, 0, 87, 20, 20);
			else
				this.drawTexturedModalRect(sw - 20 - 8, sh - 83 - 8, 0, 47, 20, 20);

			if (inventoryplayer.currentItem == 1)
				this.drawTexturedModalRect(sw - 20 - 8, sh - 62 - 8, 0, 87, 20, 20);
			else
				this.drawTexturedModalRect(sw - 20 - 8, sh - 62 - 8, 0, 47, 20, 20);

			if (inventoryplayer.currentItem == 2)
				this.drawTexturedModalRect(sw - 20 - 8, sh - 41 - 8, 0, 87, 20, 20);
			else
				this.drawTexturedModalRect(sw - 20 - 8, sh - 41 - 8, 0, 47, 20, 20);

			if (inventoryplayer.currentItem == 3)
				this.drawTexturedModalRect(sw - 20 - 8, sh - 20 - 8, 0, 87, 20, 20);
			else
				this.drawTexturedModalRect(sw - 20 - 8, sh - 20 - 8, 0, 47, 20, 20);

			if (inventoryplayer.currentItem >= 4 && inventoryplayer.currentItem <= 6 )
				inventoryplayer.currentItem = 0;
			else if (inventoryplayer.currentItem >= 7)
				inventoryplayer.currentItem = 3;
				

			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.enableGUIStandardItemLighting();
			for (int i = 0; i < 4; ++i) {
				int j1 = sw - 20 - 7 + 1;
				int k1 = sh - (83 - (21 * i)) - 7 + 1; 
				this.renderInventorySlot(i, j1, k1, event.partialTicks);
			}

			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();

		}

	}

	protected static final RenderItem itemRenderer = new RenderItem();

	/**
	 * Renders the specified item of the inventory slot at the specified
	 * location. Args: slot, x, y, partialTick
	 */
	protected void renderInventorySlot(int p_73832_1_, int p_73832_2_,
			int p_73832_3_, float p_73832_4_) {
		ItemStack itemstack = this.mc.thePlayer.inventory.mainInventory[p_73832_1_];

		if (itemstack != null) {
			float f1 = (float) itemstack.animationsToGo - p_73832_4_;

			if (f1 > 0.0F) {
				GL11.glPushMatrix();
				float f2 = 1.0F + f1 / 5.0F;
				GL11.glTranslatef((float) (p_73832_2_ + 8),
						(float) (p_73832_3_ + 12), 0.0F);
				GL11.glScalef(1.0F / f2, (f2 + 1.0F) / 2.0F, 1.0F);
				GL11.glTranslatef((float) (-(p_73832_2_ + 8)),
						(float) (-(p_73832_3_ + 12)), 0.0F);
			}

			itemRenderer.renderItemAndEffectIntoGUI(this.mc.fontRenderer,
					this.mc.getTextureManager(), itemstack, p_73832_2_,
					p_73832_3_);

			if (f1 > 0.0F) {
				GL11.glPopMatrix();
			}

			itemRenderer.renderItemOverlayIntoGUI(this.mc.fontRenderer,
					this.mc.getTextureManager(), itemstack, p_73832_2_,
					p_73832_3_);
		}
	}
}
