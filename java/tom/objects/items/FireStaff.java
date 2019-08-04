package tom.objects.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import tom.Main;
import tom.init.ItemInit;
import tom.util.IHasModel;
import tom.util.Reference;

public class FireStaff extends Item implements IHasModel
{
	private double acceleration = 0.2D;

	public FireStaff(String name) 
	{
		setUnlocalizedName(Reference.MODID + "." + name);
		setRegistryName(name);
		setCreativeTab(Main.PERKS);
		
		ItemInit.ITEMS.add(this);
	}

	//If aimed in the air
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		ItemStack item = playerIn.getHeldItem(handIn);
		Vec3d aim = playerIn.getLookVec();
		EntityLargeFireball fireBall = new EntityLargeFireball(worldIn, playerIn, 1, 1, 1);
		//this works but it is bad form!!
		fireBall.explosionPower = 5;
		//EntityShell shell = new EntityShell(worldIn);
		
		fireBall.setPosition(playerIn.posX + aim.x * 1.5D, playerIn.posY + aim.y * 1.5D, playerIn.posZ + aim.z * 1.5D);
		fireBall.accelerationX = aim.x * acceleration;
		fireBall.accelerationY = aim.y * acceleration;
		fireBall.accelerationZ = aim.z * acceleration;
		
		worldIn.spawnEntity(fireBall);
		
		item.damageItem(1, playerIn);
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
	}
	
	/*
	 * If aimed at ground
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
	*/
	
	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
