package uk.co.animecraft.mmo.Handlers;

import uk.co.animecraft.mmo.MMOmod;
import uk.co.animecraft.mmo.Inventory.GuiClass;
import uk.co.animecraft.mmo.Inventory.InvContainer;
import uk.co.animecraft.mmo.Inventory.PlayerInv;
import uk.co.animecraft.mmo.Player.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getClientGuiElement(int guiId, EntityPlayer player, World worldObj,
			int x, int y, int z) {
		if (guiId == MMOmod.GUI_CUSTOM_INV)  {
			return new GuiClass(player, player.inventory, ExtendedPlayer.get(player).inventory);
		}  else {
			return null;
		}
	}

	@Override
	public Object getServerGuiElement(int guiId, EntityPlayer player, World worldObj,
			int x, int y, int z) {
		
		if (guiId == MMOmod.GUI_CUSTOM_INV) {
			System.out.println("guiId: " + String.valueOf(new InvContainer(player, player.inventory, ExtendedPlayer.get(player).inventory)));
			return new InvContainer(player, player.inventory, ExtendedPlayer.get(player).inventory);
		}  else {
			return null;
		}
	}

}
