package uk.co.animecraft.mmo.Inventory.Slots;

import uk.co.animecraft.mmo.Items.bagItem;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

public class SlotBag extends Slot
{
	
    private int field_75228_b;
    
    public SlotBag(IInventory arg0, int arg1, int arg2, int arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}
    
    @Override
    public int getSlotStackLimit()
    {
        return 1;
    }
    
    @Override
    public boolean isItemValid(ItemStack p_75214_1_)
    {
        if (p_75214_1_ == null) return false;
        
        if (p_75214_1_.getItem() instanceof bagItem)
        return true;
        else
        return false;
    }
	
    @SideOnly(Side.CLIENT)
    public IIcon getBackgroundIconIndex()
    {
        return bagItem.getSlotIcon();
    }
    

}