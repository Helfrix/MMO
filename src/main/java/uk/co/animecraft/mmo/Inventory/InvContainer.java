package uk.co.animecraft.mmo.Inventory;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import uk.co.animecraft.mmo.Inventory.Slots.SlotArmour;
import uk.co.animecraft.mmo.Inventory.Slots.SlotBag;
import uk.co.animecraft.mmo.Inventory.Slots.StandardSlot;
import uk.co.animecraft.mmo.Items.bagItem;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;


public class InvContainer extends Container
{
	/** Avoid magic numbers! This will greatly reduce the chance of you making errors in 'transferStackInSlot' method */
	private static final int HOTBAR_START = 0, HOTBAR_END = HOTBAR_START+3,
	BAG_START = HOTBAR_END+1, BAG_END = BAG_START + 1,
	INV_START = BAG_END+1, INV_END = INV_START+PlayerInv.numSlots,
	ARMOR_START = INV_END + 1,ARMOR_END = ARMOR_START+3;

	public InvContainer(EntityPlayer player, InventoryPlayer inventoryPlayer, PlayerInv inventoryCustom)
	{
		int i;

		//ACTION BAR
		for (i = 0; i < 4; ++i)
		{
			this.addSlotToContainer(new StandardSlot(inventoryPlayer, i, 82, (i * 21) + 7)); 
		}

		//BAG SLOTS
		for (i = 0; i < 2; ++i)
		{
			this.addSlotToContainer(new SlotBag(inventoryPlayer, i + BAG_START, (i*22) + 31, 70)); 
		}
		
		int d = INV_START;
		//PLAYER INVENTORY
		for (i = 0; i < 20; ++i)
		{
			for (int j = 0; j < 6; ++j)
			{
				if(d == 99)
					d += 5;

				if(d < PlayerInv.numSlots + INV_START)
					this.addSlotToContainer(new StandardSlot(inventoryPlayer, d, 111 + j * 21, 7 + i * 21));

				d++;
			}
		} 

		//ARMOUR BAR
		for (i = 0; i < 4; ++i)
		{
			this.addSlotToContainer(new SlotArmour(player, inventoryPlayer, inventoryPlayer.getSizeInventory() - 1 - i, 7, (i * 21) + 7, i));
		}
		
		
	}

	/**
	 * This should always return true, since custom inventory can be accessed from anywhere
	 */
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}

	/**
	 * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	 * Basically the same as every other container I make, since I define the same constant indices for all of them
	 */
	public ItemStack transferStackInSlot(EntityPlayer player, int slotID)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(slotID);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (slotID >= ARMOR_START - 1 && slotID <= ARMOR_END)
			{
				// try to place in player inventory
				if (!this.mergeItemStack(itemstack1, INV_START, INV_END + 1, false))
				{
					return null;
				}

				//slot.onSlotChange(itemstack1, itemstack);
			}
			// Item is in inventory / hotbar, try to place either in custom or armor slots
			else if (itemstack1.getItem() instanceof ItemArmor)
			{
				int type = ((ItemArmor) itemstack1.getItem()).armorType;
				if (!this.mergeItemStack(itemstack1, ARMOR_START + type - 1, ARMOR_START + type, false))
				{
					return null;
				}
			}
			else if (itemstack1.getItem() instanceof bagItem)
			{
				if (!this.mergeItemStack(itemstack1, BAG_START, BAG_END + 1, false))
				{
					return null;
				}
			}
			// item in player's inventory, place in action bar
			else if (slotID >= INV_START && slotID <= INV_END)
			{
				if (!this.mergeItemStack(itemstack1, HOTBAR_START, HOTBAR_END + 1, false))
				{
					return null;
				}
			}
			// item in action bar - place in player inventory
			else if (slotID >= HOTBAR_START && slotID <= HOTBAR_END)
			{
				if (!this.mergeItemStack(itemstack1, INV_START, INV_END + 1, false))
				{
					return null;
				}
			}


			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack) null);
			}
			else
			{
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize)
			{
				return null;
			}

			slot.onPickupFromSlot(player, itemstack1);
		}

		return itemstack;
	}

}