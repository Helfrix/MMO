package uk.co.animecraft.mmo.Items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class newItem extends Item {
	public newItem(int maxStackSize, CreativeTabs tab, int texture, String name) {
		setMaxStackSize(maxStackSize);
		setCreativeTab(tab);
		setUnlocalizedName(name);
	}
}
