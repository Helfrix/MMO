package uk.co.animecraft.mmo.Blocks;

import uk.co.animecraft.mmo.MMOmod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockReg {

	public static Block newBlock;
	
	public static void registerblocks(){
		
		// Marble
		newBlock = new newDecoBlock(Material.ground)
				.setHardness(0.5F)
				.setStepSound(Block.soundTypeStone)
				.setBlockName("blockMarble")
				.setCreativeTab(MMOmod.tabMMO)
				.setBlockTextureName("mmo:blockMarble");
		GameRegistry.registerBlock(newBlock, "blockMarble");
		CustomBlocks.blockMarble = newBlock;

		// Green Lantern
		newBlock = new newSemiTransBlock(Material.glass)
				.setHardness(0.5F)
				.setStepSound(Block.soundTypeStone)
				.setBlockName("blockGreenLantern")
				.setCreativeTab(MMOmod.tabMMO)
				.setBlockTextureName("mmo:blockGreenLantern")
				.setLightLevel(1);
		GameRegistry.registerBlock(newBlock, "blockGreenLantern");
		CustomBlocks.blockGreenLantern = newBlock;
		
	}
	
}
