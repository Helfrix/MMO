package uk.co.animecraft.mmo.Inventory.Slots;

import uk.co.animecraft.mmo.Items.emptyArmorItem;
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

public class SlotArmour extends Slot
{
	/** The player that is using the GUI where this slot resides. */
    private EntityPlayer thePlayer;
    private int field_75228_b;
    private int currentI = 0;
    
    public SlotArmour(EntityPlayer plr, IInventory arg0, int arg1, int arg2, int arg3, int arg4) {
		super(arg0, arg1, arg2, arg3);
		this.thePlayer = plr;
		this.currentI = arg4;
		
	}
    
    @Override
    public int getSlotStackLimit()
    {
        return 1;
    }
    
    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    @Override
    public boolean isItemValid(ItemStack p_75214_1_)
    {
        if (p_75214_1_ == null) return false;
        return p_75214_1_.getItem().isValidArmor(p_75214_1_, currentI, thePlayer);
    }
    
    /**
     * Returns the icon index on items.png that is used as background image of the slot.
     */
    @SideOnly(Side.CLIENT)
    public IIcon getBackgroundIconIndex()
    {
        return emptyArmorItem.getSlotIcon(currentI);
    }
	

}