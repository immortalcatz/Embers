package teamroots.embers.block;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import teamroots.embers.tileentity.ITileEntityBase;
import teamroots.embers.tileentity.TileEntityFurnaceTop;
import teamroots.embers.tileentity.TileEntityItemPipe;
import teamroots.embers.tileentity.TileEntityPipe;
import teamroots.embers.tileentity.TileEntityTank;

public class BlockItemPipe extends BlockTEBase {
	
	public BlockItemPipe(Material material, String name, boolean addToTab) {
		super(material, name, addToTab);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityItemPipe();
	}
	
	@Override
	public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor){
		if (world.getTileEntity(pos) != null){
			((TileEntityItemPipe)world.getTileEntity(pos)).updateNeighbors(world);
		}
	}
	
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block){
		if (world.getTileEntity(pos) != null){
			((TileEntityItemPipe)world.getTileEntity(pos)).updateNeighbors(world);
			world.notifyBlockUpdate(pos, state, world.getBlockState(pos), 3);
		}
	}
	
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state){
		if (world.getTileEntity(pos) != null){
			((TileEntityItemPipe)world.getTileEntity(pos)).updateNeighbors(world);
		}
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
		double x1 = 0.375;
		double y1 = 0.375;
		double z1 = 0.375;
		double x2 = 0.625;
		double y2 = 0.625;
		double z2 = 0.625;
		
		if (source.getTileEntity(pos) instanceof TileEntityItemPipe){
			TileEntityItemPipe pipe = ((TileEntityItemPipe)source.getTileEntity(pos));
			if (pipe.up != TileEntityItemPipe.EnumPipeConnection.NONE){
				y2 = 1;
			}
			if (pipe.down != TileEntityItemPipe.EnumPipeConnection.NONE){
				y1 = 0;
			}
			if (pipe.north != TileEntityItemPipe.EnumPipeConnection.NONE){
				z1 = 0;
			}
			if (pipe.south != TileEntityItemPipe.EnumPipeConnection.NONE){
				z2 = 1;
			}
			if (pipe.west != TileEntityItemPipe.EnumPipeConnection.NONE){
				x1 = 0;
			}
			if (pipe.east != TileEntityItemPipe.EnumPipeConnection.NONE){
				x2 = 1;
			}
		}
		
		return new AxisAlignedBB(x1,y1,z1,x2,y2,z2);
	}
	
	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player){
		super.onBlockHarvested(world, pos, state, player);
		if (world.getTileEntity(pos.up()) instanceof TileEntityItemPipe){
			((TileEntityItemPipe)world.getTileEntity(pos.up())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.down()) instanceof TileEntityItemPipe){
			((TileEntityItemPipe)world.getTileEntity(pos.down())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.north()) instanceof TileEntityItemPipe){
			((TileEntityItemPipe)world.getTileEntity(pos.north())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.south()) instanceof TileEntityItemPipe){
			((TileEntityItemPipe)world.getTileEntity(pos.south())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.west()) instanceof TileEntityItemPipe){
			((TileEntityItemPipe)world.getTileEntity(pos.west())).updateNeighbors(world);
		}
		if (world.getTileEntity(pos.east()) instanceof TileEntityItemPipe){
			((TileEntityItemPipe)world.getTileEntity(pos.east())).updateNeighbors(world);
		}
	}
}
