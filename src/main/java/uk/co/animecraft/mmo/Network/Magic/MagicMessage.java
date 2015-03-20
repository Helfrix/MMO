package uk.co.animecraft.mmo.Network.Magic;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class MagicMessage implements IMessage
{
	int maxMagic;
	int currentMagic;

	// this constructor is required otherwise you'll get errors (used somewhere in fml through reflection)
	public MagicMessage() {}

	public MagicMessage(int maxMagic, int currentMagic)
	{
		this.maxMagic = maxMagic;
		this.currentMagic = currentMagic;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		// the order is important
		this.maxMagic = buf.readInt();
		this.currentMagic = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(maxMagic);
		buf.writeInt(currentMagic);
	}
}
