package uk.co.animecraft.mmo;

import java.util.EnumMap;

import uk.co.animecraft.mmo.Blocks.BlockReg;
import uk.co.animecraft.mmo.Blocks.CustomBlocks;
import uk.co.animecraft.mmo.HUD.RenderHUD;
import uk.co.animecraft.mmo.Handlers.GuiHandler;
import uk.co.animecraft.mmo.Handlers.PacketHandler;
import uk.co.animecraft.mmo.Handlers.TickHandler;
import uk.co.animecraft.mmo.Items.ItemReg;
import uk.co.animecraft.mmo.Menus.*;
import uk.co.animecraft.mmo.Mobs.KillMobEvent;
import uk.co.animecraft.mmo.Mobs.RenderMobStats;
import uk.co.animecraft.mmo.Player.CustomPlayerEvent;
import uk.co.animecraft.mmo.proxies.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = MMOmod.MODID, version = MMOmod.VERSION)
public class MMOmod {
	public static final String MODID = "mmomod";
	public static final String VERSION = "0.0.1";

	@SidedProxy(clientSide = "uk.co.animecraft.mmo.proxies.ClientProxy", serverSide = "uk.co.animecraft.mmo.proxies.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance(MODID)
	public static MMOmod instance;
	
	private static int modGuiIndex = 0;
	public static final int GUI_CUSTOM_INV = modGuiIndex++;
	
	
	public static CreativeTabs tabMMO = new CreativeTabs("tabMMO")
	{
		public Item getTabIconItem()
		{
		return Item.getItemFromBlock(CustomBlocks.blockGreenLantern);
		}
	};
	
	public static CreativeTabs tabMMOfood = new CreativeTabs("tabMMOfood")
	{
		public Item getTabIconItem()
		{
		return Item.getItemFromBlock(CustomBlocks.blockGreenLantern);
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		BlockReg.registerblocks();
		ItemReg.registeritems();
		PacketHandler.registerPackets();
		

	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
			     
		proxy.registerRenderers();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		
		//MinecraftForge.EVENT_BUS.register(new CustomPlayerEvent());
		
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
		
		
	}
}
