package uk.co.animecraft.mmo.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.Map;

import uk.co.animecraft.mmo.MMOmod;
import uk.co.animecraft.mmo.Handlers.PacketHandler;
import uk.co.animecraft.mmo.Inventory.PlayerInv;
import uk.co.animecraft.mmo.Message.SyncAttackXPMessage;
import uk.co.animecraft.mmo.Message.SyncPropsMessage;
import uk.co.animecraft.mmo.proxies.CommonProxy;

import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.relauncher.Side;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties {

	//Use ExtendedPlayer props = ExtendedPlayer.get(player);
	//to access properties.

	public final static String EXT_PROP_NAME = "ExtendedProps";
	private final EntityPlayer player;
	private int currentMagic, maxMagic;
	private int MainLevel, AttackLevel, 
	DefenceLevel, ArcheryLevel, 
	MagicLevel, MiningLevel, 
	FishingLevel, TamingLevel, 
	CookingLevel, HarvestingLevel, SmithingLevel;
	private int AttackXP, 
	DefenceXP, ArcheryXP, 
	MagicXP, MiningXP,
	FishingXP, TamingXP, 
	CookingXP, HarvestingXP, SmithingXP;
	private boolean isAdmin, isMod;

	public final PlayerInv inventory = new PlayerInv();

	public ExtendedPlayer(EntityPlayer player)
	{
		System.out.println("before register - get player!");
		this.player = player;
		
	}

	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer(player));
		System.out.println("registered!");
	}

	public static final ExtendedPlayer get(EntityPlayer player)
	{
		return (ExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);
	}

	@Override
	public void init(Entity arg0, World arg1) {
		System.out.println("init!");
		this.currentMagic = this.maxMagic = 50;
		this.MainLevel = 1;
		this.AttackLevel = 1;
		this.DefenceLevel = 1;
		this.ArcheryLevel = 1;
		this.MagicLevel = 1;
		this.MiningLevel = 1;
		this.FishingLevel = 1;
		this.TamingLevel = 1;
		this.CookingLevel = 1;
		this.HarvestingLevel = 1;
		this.SmithingLevel = 1;

		this.isAdmin = false;
		this.isMod = false;
		
		this.AttackXP = 0;
		this.DefenceXP = 0;
		this.ArcheryXP = 0;
		this.MagicXP = 0;
		this.MiningXP = 0;
		this.FishingXP = 0;
		this.TamingXP = 0;
		this.CookingXP = 0;
		this.HarvestingXP = 0;
		this.SmithingXP = 0;
		
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		System.out.println("load nbt!");
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);

		this.currentMagic = properties.getInteger("CurrentMagic");
		this.maxMagic = properties.getInteger("MaxMagic");

		this.MainLevel = properties.getInteger("MainLevel");
		this.AttackLevel = properties.getInteger("AttackLevel");
		this.DefenceLevel = properties.getInteger("DefenceLevel");
		this.ArcheryLevel = properties.getInteger("ArcheryLevel");
		this.MagicLevel = properties.getInteger("MagicLevel");
		this.MiningLevel = properties.getInteger("MiningLevel");
		this.FishingLevel = properties.getInteger("FishingLevel");
		this.TamingLevel = properties.getInteger("TamingLevel");
		this.CookingLevel = properties.getInteger("CookingLevel");
		this.HarvestingLevel = properties.getInteger("HarvestingLevel");
		this.SmithingLevel = properties.getInteger("SmithingLevel");

		this.AttackXP = properties.getInteger("AttackXP");
		this.DefenceXP = properties.getInteger("DefenceXP");
		this.ArcheryXP = properties.getInteger("ArcheryXP");
		this.MagicXP = properties.getInteger("MagicXP");
		this.MiningXP = properties.getInteger("MiningXP");
		this.FishingXP = properties.getInteger("FishingXP");
		this.TamingXP = properties.getInteger("TamingXP");
		this.CookingXP = properties.getInteger("CookingXP");
		this.HarvestingXP = properties.getInteger("HarvestingXP");
		this.SmithingXP = properties.getInteger("SmithingXP");

		this.inventory.readFromNBT(properties);

	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		System.out.println("save nbt!");
		NBTTagCompound properties = new NBTTagCompound();

		properties.setInteger("CurrentMana", this.currentMagic);
		properties.setInteger("MaxMana", this.maxMagic);

		properties.setInteger("MainLevel", this.MainLevel);
		properties.setInteger("AttackLevel", this.AttackLevel);
		properties.setInteger("DefenceLevel", this.DefenceLevel);
		properties.setInteger("ArcheryLevel", this.ArcheryLevel);
		properties.setInteger("MagicLevel", this.MagicLevel);
		properties.setInteger("MiningLevel", this.MiningLevel);
		properties.setInteger("FishingLevel", this.FishingLevel);
		properties.setInteger("TamingLevel", this.TamingLevel);
		properties.setInteger("CookingLevel", this.CookingLevel);
		properties.setInteger("HarvestingLevel", this.HarvestingLevel);
		properties.setInteger("SmithingLevel", this.SmithingLevel);

		properties.setInteger("AttackXP", this.AttackXP);
		properties.setInteger("DefenceXP", this.DefenceXP);
		properties.setInteger("ArcheryXP", this.ArcheryXP);
		properties.setInteger("MagicXP", this.MagicXP);
		properties.setInteger("MiningXP", this.MiningXP);
		properties.setInteger("FishingXP", this.FishingXP);
		properties.setInteger("TamingXP", this.TamingXP);
		properties.setInteger("CookingXP", this.CookingXP);
		properties.setInteger("HarvestingXP", this.HarvestingXP);
		properties.setInteger("SmithingXP", this.SmithingXP);

		this.inventory.writeToNBT(properties);

		compound.setTag(EXT_PROP_NAME, properties);
	}

	public boolean consumeMana(int amount)
	{
		// Does the player have enough mana?
		boolean sufficient = amount <= this.currentMagic;
		// Consume the amount anyway; if it's more than the player's current mana,
		// mana will be set to 0
		this.currentMagic -= (amount < this.currentMagic ? amount : this.currentMagic);
		// Return if the player had enough mana
		return sufficient;
	}

	public void replenishMana()
	{
		this.currentMagic = this.maxMagic;
	}

	private static final String getSaveKey(EntityPlayer player) {

		return player.getDisplayName() + ":" + EXT_PROP_NAME;
	}

	public int getCurrentMagic(){
		return currentMagic;
	}

	public int getMaxMagic(){
		return maxMagic;
	}

	public int getMainLevel(){
		return MainLevel;
	}

	public int getLevel(int type){
		switch (type){
		case 0: //Attack Level
			return this.AttackLevel;
		case 1: //Defence Level
			return this.DefenceLevel;
		case 2: //Archery Level
			return this.ArcheryLevel;
		case 3: //Magic Level
			return this.MagicLevel;
		case 4: //Smithing Level
			return this.SmithingLevel;
		case 5: //Cooking Level
			return this.CookingLevel;
		case 6: //Taming Level
			return this.TamingLevel;
		case 7: //Mining Level
			return this.MiningLevel;
		case 8: //Fishing Level
			return this.FishingLevel;
		case 9: //Harvesting Level
			return this.HarvestingLevel;
		default:
			return 1;
		}
	}

	public int getXP(int type){
		switch (type){
		case 0: //Attack XP
			return this.AttackXP;
		case 1: //Defence XP
			return this.DefenceXP;
		case 2: //Archery XP
			return this.ArcheryXP;
		case 3: //Magic XP
			return this.MagicXP;
		case 4: //Smithing XP
			return this.SmithingXP;
		case 5: //Cooking XP
			return this.CookingXP;
		case 6: //Taming XP
			return this.TamingXP;
		case 7: //Mining XP
			return this.MiningXP;
		case 8: //Fishing XP
			return this.FishingXP;
		case 9: //Harvesting XP
			return this.HarvestingXP;
		default:
			return 1;
		}
	}

	public void addAttackXP(int amount){

		this.AttackXP += amount;

		System.out.println("isServer: " + String.valueOf(Minecraft.getMinecraft().theWorld.isRemote));
		System.out.println("Wooooo GET XP!");
		
		if(Minecraft.getMinecraft().theWorld.isRemote)
		PacketHandler.sendTo(new SyncAttackXPMessage(player,this.AttackXP), (EntityPlayerMP) player);
		
	}

	public void addDefenceXP(int amount){

		this.DefenceXP += amount;

	}
	
	public void addArcheryXP(int amount){

		this.ArcheryXP += amount;

	}
	
	public void addMagicXP(int amount){

		this.MagicXP += amount;

	}
	
	public void addSmithingXP(int amount){

		this.SmithingXP += amount;

	}
	
	public void addCookingXP(int amount){

		this.CookingXP += amount;

	}
	
	public void addTamingXP(int amount){

		this.TamingXP += amount;

	}
	
	public void addMiningXP(int amount){

		this.MiningXP += amount;

	}
	
	public void addFishingXP(int amount){

		this.FishingXP += amount;

	}
	
	public void addHarvestingXP(int amount){

		this.HarvestingXP += amount;

	}
	
	// XP Setters
	public void setAttackXP(int amount){
		System.out.println("setting attack xp!");
		this.AttackXP = amount;
		
	}

	public void setDefenceXP(int amount){

		this.DefenceXP = amount;

	}
	
	public void setArcheryXP(int amount){

		this.ArcheryXP = amount;

	}
	
	public void setMagicXP(int amount){

		this.MagicXP = amount;

	}
	
	public void setSmithingXP(int amount){

		this.SmithingXP = amount;

	}
	
	public void setCookingXP(int amount){

		this.CookingXP = amount;

	}
	
	public void setTamingXP(int amount){

		this.TamingXP = amount;

	}
	
	public void setMiningXP(int amount){

		this.MiningXP = amount;

	}
	
	public void setFishingXP(int amount){

		this.FishingXP = amount;

	}
	
	public void setHarvestingXP(int amount){

		this.HarvestingXP = amount;

	}


}
