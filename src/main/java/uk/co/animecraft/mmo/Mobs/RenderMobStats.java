package uk.co.animecraft.mmo.Mobs;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class RenderMobStats{

	private Minecraft mc;
	public int myTick = 0;

	public RenderMobStats(Minecraft mc) {
		super();

		// We need this to invoke the render engine.
		this.mc = mc;
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void renderStatsOverHead(RenderLivingEvent.Specials.Pre event) {

		if (event.isCancelable() && !(event.entity instanceof EntityPlayer)) {
			
			renderOHS(event.entity);
			
		}
		

	}

	protected void renderOHS(EntityLivingBase entity) {

		String entityName = entity.func_145748_c_().getFormattedText();
		
		Tessellator tessellator = Tessellator.instance;

		EntityClientPlayerMP player = mc.thePlayer;		
		
		double diffX = (entity.prevPosX + (entity.posX - entity.prevPosX))
		- (player.prevPosX + (player.posX - player.prevPosX));
		double diffY = (entity.prevPosY + (entity.posY - entity.prevPosY))
		- (player.prevPosY + (player.posY - player.prevPosY));
		double diffZ = (entity.prevPosZ + (entity.posZ - entity.prevPosZ))
		- (player.prevPosZ + (player.posZ - player.prevPosZ));
		
		/*
		double diffX = (entity.lastTickPosX + (entity.posX - entity.lastTickPosX))
		- (player.lastTickPosX + (player.posX - player.lastTickPosX));
		double diffY = (entity.lastTickPosY + (entity.posY - entity.lastTickPosY))
		- (player.lastTickPosY + (player.posY - player.lastTickPosY));
		double diffZ = (entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ))
		- (player.lastTickPosZ + (player.posZ - player.lastTickPosZ));*/
		
		float eh = entity.height;
		float ew = entity.width;
		
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glTranslated(diffX, diffY + eh + 0.5, diffZ);
		GL11.glRotatef(-player.rotationYaw, 0.0F, 1.0F, 0.0F);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glPushMatrix();

		
		GL11.glRotatef(90, 1, 0, 0);
		GL11.glScalef(0.02f, 0.02f, 0.02f);
		
		//float eHealth = entity.getDataWatcher().getWatchableObjectFloat(6);
		//float eMaxHealth = ((EntityLivingBase) entity).getMaxHealth();
		
		float eHealth = entity.getHealth();
		float eMaxHealth = entity.getMaxHealth();

		
		mc.renderEngine.bindTexture(new ResourceLocation("mmo", "textures/gui/MobHealthBlank.png"));

		tessellator.setBrightness(15);
		tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		
		tessellator.startDrawingQuads();
		
		float pix = (1/100);
		float w = ew*100;
		float h = 6;
		float halfw = w/2;
		float halfh = h/2;
		
		float healthPerc = eHealth / eMaxHealth;
		
		float neww = -halfw + (healthPerc*w);
		
		//Width,Depth,Height,U,V
		tessellator.addVertexWithUV(halfw, 0, -halfh, 0, 0);
		tessellator.addVertexWithUV(halfw, 0, halfh, 0, 1);
		tessellator.addVertexWithUV(-halfw, 0, halfh, 1, 1);
		tessellator.addVertexWithUV(-halfw, 0, -halfh, 1, 0);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		if(healthPerc > 0.5)
		{
		mc.renderEngine.bindTexture(new ResourceLocation("mmo", "textures/gui/MobHealth.png"));
		}
		else
		{
			if(healthPerc <= 0.5 && healthPerc >= 0.2)
			{
				mc.renderEngine.bindTexture(new ResourceLocation("mmo", "textures/gui/MobHealthO.png"));
			}
			else
			{
				mc.renderEngine.bindTexture(new ResourceLocation("mmo", "textures/gui/MobHealthR.png"));
			}
		}
		
		//Width,Depth,Height,U,V
		tessellator.addVertexWithUV(halfw, -0.01, -halfh, 0, 0);
		tessellator.addVertexWithUV(halfw, -0.01, halfh, 0, 1);
		tessellator.addVertexWithUV( -neww, -0.01, halfh, 1, 1);
		tessellator.addVertexWithUV( -neww, -0.01, -halfh, 1, 0);
		
		tessellator.draw();
		
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glRotatef(90, 1, 0, 0);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glScalef(0.05f, 0.05f, 0.05f);
		GL11.glRotatef(90, 1f, 0f, 0f);
		GL11.glRotatef(180, 0f, 1f, 0f);
		GL11.glTranslatef(0f, -10f, 0f);
		RenderManager.instance.getFontRenderer().drawString(entityName, -RenderManager.instance.getFontRenderer().getStringWidth(entityName) / 2, 0 , 553648127);		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		RenderManager.instance.getFontRenderer().drawString(entityName, -RenderManager.instance.getFontRenderer().getStringWidth(entityName) / 2, 0, -1);
		//GL11.glDisable(GL11.GL_LIGHTING);
		//GL11.glDisable(GL11.GL_BLEND);
		
		//GL11.glPopMatrix();
		GL11.glPopMatrix();
		GL11.glPopMatrix();// restore saved gl transform state
	}

}
