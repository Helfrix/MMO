package uk.co.animecraft.mmo.Blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class newSemiTransBlock extends Block {

	public newSemiTransBlock(Material material) {
		super(material);
	}

	@Override
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderBlockPass() {
		return 1;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
        //return super.shouldSideBeRendered(par1IBlockAccess, x, y, z, 2 - side);
        Block i1 = par1IBlockAccess.getBlock(x, y, z);
        return i1 == this ? false : super.shouldSideBeRendered(par1IBlockAccess, x, y, z, side);
    }
}
