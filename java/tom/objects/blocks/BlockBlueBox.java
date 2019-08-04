package tom.objects.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBlueBox extends BlockBase
{
	public static final AxisAlignedBB BLUEBOX = new AxisAlignedBB(0.25D, 0, 0.25D, 0.75D, 0.0625D, 0.75D);

	public BlockBlueBox(String name) 
	{
		super(name, Material.CAKE);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) 
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) 
	{
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
	{
		return BLUEBOX;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		//Very cool!!!
		if (!worldIn.isRemote)
		{
			EntityPig piggy = new EntityPig(worldIn);
			piggy.posX = pos.getX();
			piggy.posY = pos.getY();
			piggy.posZ = pos.getZ();
			worldIn.spawnEntity(piggy);
			worldIn.createExplosion(playerIn, pos.getX(), pos.getY(), pos.getZ(), 20, true);
		}
		return true;
	}
}
