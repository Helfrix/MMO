package uk.co.animecraft.mmo.Items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class foodItem extends ItemFood {
		
	private boolean alwaysEdible;
	private int healAmount;
	private int foodLevel;
	private float saturationModifier;

	public foodItem(int hAmount,float sAmount,boolean isAEdible,int fLevel) {
		super(hAmount, sAmount, isAEdible);
		
		alwaysEdible = isAEdible;
		healAmount = hAmount;		
		saturationModifier = sAmount;
		foodLevel = fLevel;
		
	}
	
	@Override
	public ItemStack onEaten(ItemStack par1, World par2, EntityPlayer par3) 
	{
        --par1.stackSize;
        par3.getFoodStats().addStats(foodLevel, saturationModifier);
        par3.setHealth(par3.getHealth() + healAmount);
        par2.playSoundAtEntity(par3, "random.burp", 0.5F, par2.rand.nextFloat() * 0.1F + 0.9F);
        //addPotions(par1, par2, par3);
		return par1;
	}
	
	public boolean isWolfsFavoriteMeat(){
		
		return false;
		
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        if (p_77659_3_.canEat(alwaysEdible))
        {
            p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
        }

        return p_77659_1_;
    }
	 
	public int getHealAmount(ItemStack p_150905_1_)
    {
        return this.healAmount;
    }
	
	public float getSaturationAmount(ItemStack p_150906_1_)
    {
        return this.saturationModifier;
    }
	
	public ItemFood setAlwaysEdible(){
		
		this.alwaysEdible = true;
		return this;
		
	}
	
}
