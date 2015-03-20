package uk.co.animecraft.mmo.Inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class PlayerInv implements IInventory{

	public ItemStack[] playerInventory = new ItemStack[256];

	public ItemStack[] armorInventory = new ItemStack[4];

	public final String INV_NAME = "";
	public final String INVTagNAME = "plrInvTag";

	public static int numSlots = 18;
	public EntityPlayer player;

	public PlayerInv()
	{

	}

	@Override
	public void closeInventory() {
	}

	public ItemStack armorItemInSlot(int p_70440_1_)
	{
		return this.armorInventory[p_70440_1_];
	}

	public int getTotalArmorValue()
	{
		int i = 0;

		for (int j = 0; j < this.armorInventory.length; ++j)
		{
			if (this.armorInventory[j] != null && this.armorInventory[j].getItem() instanceof ItemArmor)
			{
				int k = ((ItemArmor)this.armorInventory[j].getItem()).damageReduceAmount;
				i += k;
			}
		}

		return i;
	}

	public void damageArmor(float p_70449_1_)
	{
		p_70449_1_ /= 4.0F;

		if (p_70449_1_ < 1.0F)
		{
			p_70449_1_ = 1.0F;
		}

		for (int i = 0; i < this.armorInventory.length; ++i)
		{
			if (this.armorInventory[i] != null && this.armorInventory[i].getItem() instanceof ItemArmor)
			{
				this.armorInventory[i].damageItem((int)p_70449_1_, this.player);

				if (this.armorInventory[i].stackSize == 0)
				{
					this.armorInventory[i] = null;
				}
			}
		}
	}

	@Override
	public ItemStack decrStackSize(int arg0, int arg1) {
		if (this.playerInventory[arg0] != null)
		{
			ItemStack itemstack;

			if (this.playerInventory[arg0].stackSize <= arg1)
			{
				itemstack = this.playerInventory[arg0];
				this.playerInventory[arg0] = null;
				this.markDirty();
				return itemstack;
			}
			else
			{
				itemstack = this.playerInventory[arg0].splitStack(arg1);

				if (this.playerInventory[arg0].stackSize == 0)
				{
					this.playerInventory[arg0] = null;
				}

				this.markDirty();
				return itemstack;
			}
		}
		else
		{
			return null;
		}
	}

	public static int getHotbarSize()
	{
		return 4;
	}

	@Override
	public String getInventoryName() {
		return INV_NAME;
	}
	

	public boolean isNameLocalized()
	{
	return INV_NAME.length() > 0;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public int getSizeInventory() {
		return numSlots + 4;
	}

	@Override
	public ItemStack getStackInSlot(int arg0) {
		return this.playerInventory[arg0];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int arg0) {
		if (this.playerInventory[arg0] != null)
		{
			ItemStack itemstack = this.playerInventory[arg0];
			this.playerInventory[arg0] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	public boolean hasItemStack(ItemStack p_70431_1_)
	{
		int i;

		for (i = 0; i < this.armorInventory.length; ++i)
		{
			if (this.armorInventory[i] != null && this.armorInventory[i].isItemEqual(p_70431_1_))
			{
				return true;
			}
		}

		for (i = 0; i < this.playerInventory.length; ++i)
		{
			if (this.playerInventory[i] != null && this.playerInventory[i].isItemEqual(p_70431_1_))
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack)
	{
		//TODO needs filtering
		// If you have different kinds of slots, then check them here:
			// if (slot == SLOT_SHIELD && itemstack.getItem() instanceof ItemShield) return true;

		// For now, only ItemUseMana items can be stored in these slots
		//return itemstack.getItem() instanceof ItemUseMana;
		return true;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void setInventorySlotContents(int arg0, ItemStack arg1) {
		this.playerInventory[arg0] = arg1;

		if (arg1 != null && arg1.stackSize > this.getInventoryStackLimit())
		{
			arg1.stackSize = this.getInventoryStackLimit();
		}

		this.markDirty();

	}


	@Override
	public void markDirty() {
		for (int i = 0; i < this.getSizeInventory(); ++i)
		{
			if (this.getStackInSlot(i) != null && this.getStackInSlot(i).stackSize == 0)
				this.setInventorySlotContents(i, null);
		}	
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	public void writeToNBT(NBTTagCompound compound)
	{
		NBTTagList items = new NBTTagList();

		for (int i = 0; i < getSizeInventory(); ++i)
		{
			if (getStackInSlot(i) != null)
			{
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte) i);
				getStackInSlot(i).writeToNBT(item);
				items.appendTag(item);
			}
		}

		compound.setTag(INVTagNAME, items);
	}
	
	public void readFromNBT(NBTTagCompound compound)
    {
		
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.playerInventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot");

            if (j >= 0 && j < getSizeInventory())
            {
                this.playerInventory[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

}
