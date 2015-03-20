package uk.co.animecraft.mmo.Message;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import uk.co.animecraft.mmo.Handlers.PacketHandler;
import uk.co.animecraft.mmo.Message.AbstractMessage.AbstractClientMessage;
import uk.co.animecraft.mmo.Message.AbstractMessage.AbstractServerMessage;
import uk.co.animecraft.mmo.Player.ExtendedPlayer;
import cpw.mods.fml.relauncher.Side;

public class SyncAttackXPMessage extends AbstractClientMessage<SyncAttackXPMessage>
{

	private int amount;

	public SyncAttackXPMessage() {}

	public SyncAttackXPMessage(EntityPlayer player, int amount) {

		this.amount = amount;
		
		ExtendedPlayer.get(player).setAttackXP(amount);
		
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		this.amount = buffer.readInt();
		System.out.println("read amount: "+String.valueOf(this.amount));
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		buffer.writeInt(this.amount);
		System.out.println("write amount: "+String.valueOf(this.amount));
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		if (side.isClient())
		{
			ExtendedPlayer.get(player).setAttackXP(this.amount);
			System.out.println("XP recv");
		} else {
			ExtendedPlayer.get(player).setAttackXP(amount);
			PacketHandler.sendTo(this, (EntityPlayerMP) player);
		}
		//ExtendedPlayer.get(player).setAttackXP(this.amount);
		//System.out.println("process amount: "+String.valueOf(this.amount));
	}
}
