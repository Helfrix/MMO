package uk.co.animecraft.mmo.proxies;


import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import uk.co.animecraft.mmo.MMOmod;
import uk.co.animecraft.mmo.HUD.RenderHUD;
import uk.co.animecraft.mmo.Handlers.GuiHandler;
import uk.co.animecraft.mmo.Handlers.TickHandler;
import uk.co.animecraft.mmo.Inventory.InvContainer;
import uk.co.animecraft.mmo.Inventory.PlayerInv;
import uk.co.animecraft.mmo.Menus.RenderMenu;
import uk.co.animecraft.mmo.Player.CustomPlayerEvent;
import uk.co.animecraft.mmo.Player.ExtendedPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy{
    
    public void registerRenderers() {
    	    	
		FMLCommonHandler.instance().bus().register(new TickHandler());
    	
    }

    public EntityPlayer getPlayerEntity(MessageContext ctx) {
		return ctx.getServerHandler().playerEntity;
	}

    
}
