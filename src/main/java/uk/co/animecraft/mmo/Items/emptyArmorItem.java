package uk.co.animecraft.mmo.Items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class emptyArmorItem extends Item {


	public static final String[] EMPTY_SLOT_NAMES = new String[] {"mmo:slotHelmet","mmo:slotChestplate","mmo:slotLeggings","mmo:slotBoots"};

	private IIcon emptySlotIcon;

	public final int slotType;

	public emptyArmorItem(int maxStackSize, String name, int type) {
		setMaxStackSize(maxStackSize);
		this.slotType = type;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister p_94581_1_)
	{
		super.registerIcons(p_94581_1_);

		this.emptySlotIcon = p_94581_1_.registerIcon(EMPTY_SLOT_NAMES[slotType]);
	}

	public static IIcon getSlotIcon(int type){

		switch (type)
		{
		case 0:
			return CustomItems.emptyHelmet.emptySlotIcon;
		case 1:
			return CustomItems.emptyChestplate.emptySlotIcon;
		case 2:
			return CustomItems.emptyLeggings.emptySlotIcon;
		case 3:
			return CustomItems.emptyBoots.emptySlotIcon;
		default:
			return null;
		}

	}

}
