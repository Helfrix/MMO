package uk.co.animecraft.mmo.Items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class bagItem extends Item {
	
	
	public static final String[] EMPTY_SLOT_NAMES = new String[] {"mmo:slotBag"};
	
	public bagItem(int maxStackSize, CreativeTabs tab, String texture, String name) {
		setMaxStackSize(maxStackSize);
		setCreativeTab(tab);
		setUnlocalizedName(name);
		setTextureName(texture);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister p_94581_1_)
    {
        super.registerIcons(p_94581_1_);

        this.emptySlotIcon = p_94581_1_.registerIcon(EMPTY_SLOT_NAMES[0]);
    }
	
	private static IIcon emptySlotIcon;
	
	public static IIcon getSlotIcon(){
		
		return emptySlotIcon;
		
	}
	
}
