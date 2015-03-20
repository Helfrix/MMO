package uk.co.animecraft.mmo.Message;

import java.io.IOException;

import cpw.mods.fml.relauncher.Side;

import uk.co.animecraft.mmo.Message.AbstractMessage.AbstractClientMessage;
import uk.co.animecraft.mmo.Player.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

public class SyncPropsMessage extends AbstractClientMessage<SyncPropsMessage>
{
	
	private NBTTagCompound data;

	public SyncPropsMessage() {}

	public SyncPropsMessage(EntityPlayer player) {
		
		data = new NBTTagCompound();
		
		ExtendedPlayer.get(player).saveNBTData(data);
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		data = buffer.readNBTTagCompoundFromBuffer();
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		buffer.writeNBTTagCompoundToBuffer(data);
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		ExtendedPlayer.get(player).loadNBTData(data);
	}
}
