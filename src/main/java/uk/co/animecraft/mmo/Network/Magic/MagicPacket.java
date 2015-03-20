package uk.co.animecraft.mmo.Network.Magic;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MagicPacket implements IMessageHandler<MagicMessage, IMessage>
{
	@Override
	public IMessage onMessage(MagicMessage message, MessageContext ctx)
	{
		// just to make sure that the side is correct
		if (ctx.side.isClient())
		{
			int integer = message.maxMagic;
			int integer2 = message.currentMagic;
		}
		return null;
	}


}
