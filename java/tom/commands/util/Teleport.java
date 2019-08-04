package tom.commands.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class Teleport extends Teleporter
{
	private final WorldServer world;
	private double X,Y,Z;

	public Teleport(WorldServer world, double X, double Y, double Z) 
	{
		super(world);
		this.world = world;
		this.X = X;
		this.Y = Y;
		this.Z = Z;
	}
	
	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw) 
	{
		this.world.getBlockState(new BlockPos((int)this.X, (int)this.Y, (int)this.Z));
		entityIn.setPosition(X, Y, Z);
		entityIn.motionX = 0f;
		entityIn.motionY = 0f;
		entityIn.motionZ = 0f;
	}
	
	public static void teleportToDimension(EntityPlayer player, int dimension, double X, double Y, double Z)
	{
		int oldDimension = player.getEntityWorld().provider.getDimension();
		EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
		MinecraftServer server = player.getEntityWorld().getMinecraftServer();
		WorldServer worldServer = server.getWorld(dimension);
		
		if (worldServer == null || server == null) throw new IllegalArgumentException("Dimension does not exist");
		
		worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension(entityPlayerMP, dimension, new Teleport(worldServer, X, Y, Z));	
		player.setPositionAndUpdate(X, Y, Z);
	}

}
