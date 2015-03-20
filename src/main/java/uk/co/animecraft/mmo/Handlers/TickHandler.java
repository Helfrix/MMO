package uk.co.animecraft.mmo.Handlers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;

import uk.co.animecraft.mmo.Menus.IngameMenu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.RenderLivingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class TickHandler {

	private Minecraft mc;
	public int CurrentTimeTick = 0;
	public int TotalTimeTicks = -864000;
	private MinecraftServer mcs;
	public static EntityLivingBase entity;

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {

	}

	// Called when the client ticks.
	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {

	}

	// Called when the server ticks. Usually 20 ticks a second.
	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event) {

		Calendar cal = Calendar.getInstance();

		if(cal.getTime().getHours() == 8 && cal.getTime().getMinutes() == 0 && cal.getTime().getSeconds() == 0 )
		{
			CurrentTimeTick = 0;
		}

		if (CurrentTimeTick == TotalTimeTicks)
			CurrentTimeTick = 0;

		--CurrentTimeTick;

		int sec = Math.round(-CurrentTimeTick / 10) + 28800;
		if(sec >= 86400) sec = sec - 86400;

		int min = Math.round(sec / 60);
		int minr = min % 60;
		int hr = Math.round(min / 60);

		String strh = String.valueOf(hr);
		String strm = String.valueOf(minr);

		if(strh.length() == 1) strh = "0"+strh;
		if(strm.length() == 1) strm = "0"+strm;

		String mcTime = strh+":"+strm;

		//TODO TIME
		//IngameMenu.mcClock = mcTime;

		int ConvertedTick = Math.round(CurrentTimeTick/30);

		mcs = MinecraftServer.getServer();
		WorldServer ws = mcs.worldServers[0];

		mcs.getEntityWorld().setWorldTime((long) ConvertedTick);

	}

	// Called when a new frame is displayed (See fps)
	@SubscribeEvent
	public void onRenderTick(TickEvent.RenderTickEvent event) {

	}
	
	// Called when the world ticks
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event) {

	}
}