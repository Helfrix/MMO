package uk.co.animecraft.mmo.proxies;

import org.lwjgl.input.Keyboard;

import uk.co.animecraft.mmo.HUD.RenderHUD;
import uk.co.animecraft.mmo.Handlers.GuiHandler;
import uk.co.animecraft.mmo.Handlers.KeyHandler;
import uk.co.animecraft.mmo.Menus.RenderMenu;
import uk.co.animecraft.mmo.Mobs.KillMobEvent;
import uk.co.animecraft.mmo.Mobs.RenderMobStats;
import uk.co.animecraft.mmo.Player.CustomPlayerEvent;
import uk.co.animecraft.mmo.Player.WorldJoinEvent;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

	public static KeyBinding[] keyBindings;

	@Override
	public void registerRenderers() {
		
		MinecraftForge.EVENT_BUS.register(new RenderMenu(Minecraft.getMinecraft()));
		MinecraftForge.EVENT_BUS.register(new RenderHUD(Minecraft.getMinecraft()));
		MinecraftForge.EVENT_BUS.register(new RenderMobStats(Minecraft.getMinecraft()));
		MinecraftForge.EVENT_BUS.register(new KillMobEvent(Minecraft.getMinecraft()));
		MinecraftForge.EVENT_BUS.register(new WorldJoinEvent());
		
		FMLCommonHandler.instance().bus().register(new KeyHandler());
		
	}
	
	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		// Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
		// your packets will not work as expected because you will be getting a
		// client player even when you are on the server!
		// Sounds absurd, but it's true.

		// Solution is to double-check side before returning the player:
		return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
	}

}