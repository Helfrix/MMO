package uk.co.animecraft.mmo.Inventory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import uk.co.animecraft.mmo.Helper.GameMechanicsHelper;
import uk.co.animecraft.mmo.Player.ExtendedPlayer;

@SideOnly(Side.CLIENT)
public class GuiClass extends customGuiContainer
{
	/** x size of the inventory window in pixels. Defined as float, passed as int */
	private float xSize_lo;

	/** y size of the inventory window in pixels. Defined as float, passed as int. */
	private float ySize_lo;
	
	private int xSize = 239;
	private int ySize = 230;

	/** Normally I use '(ModInfo.MOD_ID, "textures/...")', but it can be done this way as well */
	private static final ResourceLocation iconLocation = new ResourceLocation("mmo:textures/gui/inventory.png");

	/** Could use IInventory type to be more generic, but this way will save an import... */
	private PlayerInv inventory = new PlayerInv();
	public static EntityPlayer currentPlayer;
	

	public GuiClass(EntityPlayer player, InventoryPlayer inventoryPlayer, PlayerInv inventoryCustom)
	{
		super(new InvContainer(player, inventoryPlayer, inventoryCustom));
		this.inventory = inventoryCustom;
		this.currentPlayer = player;
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	public void drawScreen(int par1, int par2, float par3)
	{
		super.drawScreen(par1, par2, par3);
		this.xSize_lo = (float)par1;
		this.ySize_lo = (float)par2;
	}
	
	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		
		//=====================================================
		// Draw XP Bar
		//=====================================================
		
		for (int i = 0; i < 10; ++i)
		{
			
			drawXPBar(i, 5, (i * 13) + 97);
			
		}
			
	}

	public void drawXPBar(int type, int posX, int posY){

		ExtendedPlayer props = ExtendedPlayer.get(mc.thePlayer);
		FontRenderer fontRender = mc.fontRenderer;
		GameMechanicsHelper gmh = new GameMechanicsHelper();
		String str = "";
		
		//Draw Bar
		this.mc.getTextureManager().bindTexture(new ResourceLocation("mmo", "textures/gui/inventory.png"));

		System.out.println("drawbar: isServer = " + Minecraft.getMinecraft().theWorld.isRemote);
		int maxXP = gmh.getMaxXP(props.getLevel(type));
		int currentXP = props.getXP(type);
		int XPPerc = currentXP / maxXP;
		int XPLen = XPPerc * 95;
		
		this.drawTexturedModalRect(posX, posY, 0, 230, 95, 11); //Blank
		
		this.drawTexturedModalRect(posX, posY, 0, 241, XPLen, 11); //Green

		switch (type) {
		case 0:
			this.drawString(fontRender, "Attack", posX + 5, posY + 2, 0xffFFFFFF);
			break;
		case 1:
			this.drawString(fontRender, "Defence", posX + 5, posY + 2, 0xffFFFFFF);
			break;
		case 2:
			this.drawString(fontRender, "Archery", posX + 5, posY + 2, 0xffFFFFFF);
			break;
		case 3:
			this.drawString(fontRender, "Magic", posX + 5, posY + 2, 0xffFFFFFF);
			break;
		case 4:
			this.drawString(fontRender, "Smithing", posX + 5, posY + 2, 0xffFFFFFF);
			break;
		case 5:
			this.drawString(fontRender, "Cooking", posX + 5, posY + 2, 0xffFFFFFF);
			break;
		case 6:
			this.drawString(fontRender, "Taming", posX + 5, posY + 2, 0xffFFFFFF);
			break;
		case 7:
			this.drawString(fontRender, "Mining", posX + 5, posY + 2, 0xffFFFFFF);
			break;
		case 8:
			this.drawString(fontRender, "Fishing", posX + 5, posY + 2, 0xffFFFFFF);
			break;
		case 9:
			this.drawString(fontRender, "Harvesting", posX + 5, posY + 2, 0xffFFFFFF);
			
			break;
		default:
			break;

		}
		
		this.drawString(fontRender, "Lvl " + String.valueOf(props.getLevel(type)), posX + 61, posY + 2, 0xffFFFFFF);

	}
	
	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(iconLocation);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		int i1;
		//drawPlayerModel(k + 51, l + 75, 30, (float)(k + 51) - this.xSize_lo, (float)(l + 75 - 50) - this.ySize_lo, this.mc.thePlayer);
		drawPlayerModel(k + 51, l + 65, 30, (float)(k + 51) - this.xSize_lo, (float)(l + 65 - 50) - this.ySize_lo, this.mc.thePlayer);
	}

	/**
	 * This renders the player model in standard inventory position;
	 * copied straight from vanilla code but with renamed method parameters
	 */
	public static void drawPlayerModel(int x, int y, int scale, float yaw, float pitch, EntityLivingBase entity) {
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 50.0F);
		GL11.glScalef(-scale, scale, scale);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		float f2 = entity.renderYawOffset;
		float f3 = entity.rotationYaw;
		float f4 = entity.rotationPitch;
		float f5 = entity.prevRotationYawHead;
		float f6 = entity.rotationYawHead;
		GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-((float) Math.atan(pitch / 40.0F)) * 20.0F, 1.0F, 0.0F, 0.0F);
		entity.renderYawOffset = (float) Math.atan(yaw / 40.0F) * 20.0F;
		entity.rotationYaw = (float) Math.atan(yaw / 40.0F) * 40.0F;
		entity.rotationPitch = -((float) Math.atan(pitch / 40.0F)) * 20.0F;
		entity.rotationYawHead = entity.rotationYaw;
		entity.prevRotationYawHead = entity.rotationYaw;
		GL11.glTranslatef(0.0F, entity.yOffset, 0.0F);
		RenderManager.instance.playerViewY = 180.0F;
		RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
		entity.renderYawOffset = f2;
		entity.rotationYaw = f3;
		entity.rotationPitch = f4;
		entity.prevRotationYawHead = f5;
		entity.rotationYawHead = f6;
		GL11.glPopMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}
	
}
