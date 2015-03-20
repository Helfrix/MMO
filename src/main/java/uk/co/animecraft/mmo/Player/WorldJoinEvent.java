package uk.co.animecraft.mmo.Player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class WorldJoinEvent {

	private NBTTagCompound data;
	
	public WorldJoinEvent(){
		
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onWorldJoin(EntityJoinWorldEvent event){

		if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity) == null)
		{
			ExtendedPlayer.register((EntityPlayer) event.entity);
			System.out.println("player entity construct!");
		}

		if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(ExtendedPlayer.EXT_PROP_NAME) == null)
		{
			event.entity.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer((EntityPlayer) event.entity));
			System.out.println("player entity construct2!");
			
			data = new NBTTagCompound();
			
			ExtendedPlayer.get((EntityPlayer) event.entity).loadNBTData(data);
		}
		
	}
	
}
