package uk.co.animecraft.mmo.Mobs;

import uk.co.animecraft.mmo.Helper.MobHelper;
import uk.co.animecraft.mmo.Player.ExtendedPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class KillMobEvent {

	private Minecraft mc;

	public KillMobEvent(Minecraft mc) {
		super();

		this.mc = mc;
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void killEntity(LivingDeathEvent event) {

		if(event.isCancelable())
		{
			System.out.println("mob died!");
			DamageSource source = event.source;

			if(source.getSourceOfDamage() instanceof EntityPlayer) {
				System.out.println("mob killed by player!");
				MobHelper mh = new MobHelper();
				ExtendedPlayer props = ExtendedPlayer.get(mc.thePlayer);

				props.addAttackXP(mh.getMobDeathXP(event.entity));
			}
		}

	}

}
