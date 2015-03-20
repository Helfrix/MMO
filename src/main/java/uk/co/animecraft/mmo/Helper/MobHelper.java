package uk.co.animecraft.mmo.Helper;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.*;

public class MobHelper {
	
	public int getMobDeathXP(Entity ent){
		
		//TODO get list of mob types and return XP amount
		int XPamount = 0;
		
			if(ent instanceof EntitySpider)
				XPamount = 10;
			else if(ent instanceof EntityCaveSpider)
				XPamount = 10;
			else if(ent instanceof EntityZombie)
				XPamount = 10;
			else if(ent instanceof EntitySkeleton)
				XPamount = 10;
			else if(ent instanceof EntityPigZombie)
				XPamount = 10;
			else if(ent instanceof EntitySlime)
				XPamount = 10;
			else if(ent instanceof EntityCreeper)
				XPamount = 10;
			
		return XPamount;
	}
	
}
