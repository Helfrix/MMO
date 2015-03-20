package uk.co.animecraft.mmo.Items;

import uk.co.animecraft.mmo.MMOmod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemReg {
	
	public static Item newItem;
	
	public static void registeritems(){
		
		//-------------------------------
		//Ingot's
		//-------------------------------
		
		// Marble Ingot - Test
		/*
		newItem = new newItem(64, MMOmod.tabMMO, 1, "ingotMarble");
		GameRegistry.registerItem(newItem, "ingotMarble");
		CustomItems.ingotMarble = newItem;
		*/
		
		//-------------------------------
		//Food Items - Heal Amount, Saturation, Edible All The Time, Food Level
		//-------------------------------
		
		//Pie
		newItem = new foodItem(10,0.6f,true,6)
		.setCreativeTab(MMOmod.tabMMOfood)
		.setTextureName("foodPie")
		.setUnlocalizedName("foodPie");
		GameRegistry.registerItem(newItem, "foodPie");
		CustomItems.foodPie = (foodItem) newItem;
		
		//Bread
		newItem = new foodItem(20,0.6f,true,6)
		.setCreativeTab(MMOmod.tabMMOfood)
		.setTextureName("foodBread")
		.setUnlocalizedName("foodBread");
		GameRegistry.registerItem(newItem, "foodBread");
		CustomItems.foodBread = (foodItem) newItem;
		
		//Bad Apple
		newItem = new foodItem(-4,0.6f,true,-4)
		.setCreativeTab(MMOmod.tabMMOfood)
		.setTextureName("mmo:foodBadApple")
		.setUnlocalizedName("foodBadApple");
		GameRegistry.registerItem(newItem, "foodBadApple");
		CustomItems.foodBadApple = (foodItem) newItem;
		
		//--------------------------------
		//Bag
		//--------------------------------
		
		//Blue Bag
		newItem = new bagItem(1, MMOmod.tabMMO, "mmo:itemBlueBag", "itemBlueBag");
		GameRegistry.registerItem(newItem, "itemBlueBag");
		CustomItems.itemBlueBag = (bagItem) newItem;
		
		//--------------------------------
		//Empty Slots
		//--------------------------------
		
		//Armout Helmet
		newItem = new emptyArmorItem(1, "emptyHelmet", 0);
		GameRegistry.registerItem(newItem, "emptyHelmet");
		CustomItems.emptyHelmet = (emptyArmorItem) newItem;
		
		//Armout Chestplate
		newItem = new emptyArmorItem(1, "emptyChestplate", 1);
		GameRegistry.registerItem(newItem, "emptyChestplate");
		CustomItems.emptyChestplate = (emptyArmorItem) newItem;
		
		//Armout Leggings
		newItem = new emptyArmorItem(1, "emptyLeggings", 2);
		GameRegistry.registerItem(newItem, "emptyLeggings");
		CustomItems.emptyLeggings = (emptyArmorItem) newItem;
		
		//Armout Boots
		newItem = new emptyArmorItem(1, "emptyBoots", 3);
		GameRegistry.registerItem(newItem, "emptyBoots");
		CustomItems.emptyBoots = (emptyArmorItem) newItem;
	}

}
