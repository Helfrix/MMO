package uk.co.animecraft.mmo.Menus;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;

@SideOnly(Side.CLIENT)
public class IngameMenu extends GuiScreen {
	private int field_146445_a;
	private int field_146444_f;
	static public String mcClock = "00:00";

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	public void initGui() {
		this.field_146445_a = 0;
		this.buttonList.clear();
		byte b0 = -16;
		boolean flag = true;
		
		int bwidth = 98;
		int bmargin = 8;
		int halfheight = this.height / 2;
		int offset = 40;
		
		this.buttonList.add(new GuiButton(10, this.width / 2 - (98 / 2),
				halfheight - offset + (bmargin*4) + 80, 98, 20, "Logout"));

		if (!this.mc.isIntegratedServerRunning()) {
			((GuiButton) this.buttonList.get(0)).displayString = I18n.format(
					"menu.disconnect", new Object[0]);
		}

		// this.buttonList.add(new GuiButton(11, this.width / 2 - 100,
		// this.height / 4 + 24 + b0, I18n.format("menu.returnToGame", new
		// Object[0])));
		this.buttonList.add(new GuiButton(1, this.width / 2 - (bwidth / 2) - bwidth - bmargin, halfheight - offset, bwidth, 20,
				"Skills"));
		this.buttonList.add(new GuiButton(2, this.width / 2 - (bwidth / 2),	halfheight - offset, bwidth, 20,
				"Inventory"));
		this.buttonList.add(new GuiButton(3, this.width / 2 + (bwidth / 2) + bmargin, halfheight - offset, bwidth, 20,
				"Quests"));
		this.buttonList.add(new GuiButton(4, this.width / 2 - (bwidth / 2)	- bwidth - bmargin, halfheight - offset + bmargin + 20, bwidth, 20,
				"Messages"));
		this.buttonList.add(new GuiButton(5, this.width / 2 - (bwidth / 2),	halfheight - offset + bmargin + 20, bwidth, 20,
				"Friends"));
		this.buttonList.add(new GuiButton(6, this.width / 2 + (bwidth / 2) + bmargin, halfheight - offset + bmargin + 20, bwidth, 20,
				"Party/Guild"));
		this.buttonList.add(new GuiButton(7, this.width / 2 - (bwidth / 2) - bwidth - bmargin, halfheight - offset + (bmargin*2) + 40, bwidth, 20,
				"Stats"));
		this.buttonList.add(new GuiButton(8, this.width / 2 - (bwidth / 2),	halfheight - offset + (bmargin*2) + 40, bwidth, 20,
				"Settings"));
		this.buttonList.add(new GuiButton(9, this.width / 2 + (bwidth / 2) + bmargin, halfheight - offset + (bmargin*2) + 40, bwidth, 20,
				"Help"));
	}

	protected void actionPerformed(GuiButton p_146284_1_) {
		switch (p_146284_1_.id) {

		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			if (this.mc.thePlayer != null)
				this.mc.displayGuiScreen(new GuiStats(this, this.mc.thePlayer
						.getStatFileWriter()));
			break;
		case 8:
			this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
			break;
		case 9:
			this.mc.displayGuiScreen(new net.minecraft.client.gui.GuiShareToLan(this));
			break;
		case 10:
			p_146284_1_.enabled = false;
			this.mc.theWorld.sendQuittingDisconnectingPacket();
			this.mc.loadWorld((WorldClient) null);
			this.mc.displayGuiScreen(new GuiMainMenu());
			break;
		case 11:
			this.mc.displayGuiScreen((GuiScreen) null);
			this.mc.setIngameFocus();
			break;
		case 12:
			if (this.mc.thePlayer != null)
				this.mc.displayGuiScreen(new GuiAchievements(this,
						this.mc.thePlayer.getStatFileWriter()));
			break;
		default:
			break;
		}
	}

	/**
	 * Called from the main game loop to update the screen.
	 */
	public void updateScreen() {
		super.updateScreen();
		++this.field_146444_f;
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
		this.drawDefaultBackground();
		String PLR_NAME = this.mc.thePlayer.getDisplayName();
		this.drawCenteredString(this.fontRendererObj,
				"Hello " + PLR_NAME + "!", this.width / 2, this.height / 2 - 68, 16777215);
		this.drawString(this.fontRendererObj,mcClock,this.width - this.fontRendererObj.getStringWidth("00:00") - 8, 8, 0xffFFFFFF);
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}
}