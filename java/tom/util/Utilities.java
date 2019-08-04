package tom.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import tom.objects.entity.EntityBirdOfPrey;

public class Utilities 
{
	/**
     * True if the entity has an unobstructed line of travel to the waypoint.
     */
    public static boolean isCourseTraversable(Entity parEntity, double parX, double parY, double parZ)
    {
        double theDistance = MathHelper.sqrt(parX * parX + parY * parY + parZ * parZ);

        double incrementX = (parX - parEntity.posX) / theDistance;
        double incrementY = (parY - parEntity.posY) / theDistance;
        double incrementZ = (parZ - parEntity.posZ) / theDistance;
        AxisAlignedBB entityBoundingBox = parEntity.getEntityBoundingBox();

        for (int i = 1; i < theDistance; ++i)
        {
            entityBoundingBox.offset(incrementX, incrementY, incrementZ);

            if (!parEntity.world.getCollisionBoxes(parEntity, entityBoundingBox).isEmpty())
            {
                return false;
            }
        }

        return true;
    }

	
	/**
     * A method used to see if an entity is a suitable target through a number of checks.
     */

    public static boolean isSuitableTarget(EntityLivingBase theAttackerEntity, 
            EntityLivingBase parPossibleTargetEntity,
            boolean parShouldCheckSight)
    {
        if (parPossibleTargetEntity == null)
        {
//            // DEBUG
  
            System.out.println("Target isn't suitable because it is null");
            return false;
        }
        else if (parPossibleTargetEntity == theAttackerEntity)
        {
            // DEBUG
            System.out.println("Target isn't suitable because it is itself");
            return false;
        }
        else if (!parPossibleTargetEntity.isEntityAlive())
        {
            // DEBUG
            System.out.println("Target isn't suitable because it is dead");
            return false;
        }
        else if (theAttackerEntity.isOnSameTeam(parPossibleTargetEntity))
        {
            // DEBUG
            System.out.println("Target isn't suitable because it is on same team");
            return false;
        }
        else if (theAttackerEntity instanceof EntityLiving && parShouldCheckSight)
        {
            // DEBUG
            System.out.println("The attacker can see target = "+((EntityLiving)theAttackerEntity).getEntitySenses().canSee(parPossibleTargetEntity));
            return ((EntityLiving)theAttackerEntity).getEntitySenses().canSee(parPossibleTargetEntity);
        }
        else
        {
            return true;
        }
    }
    
    public static float getPitchFromVec(Vec3d parVec)
    {
        // The coordinate system for Minecraft is a bit backwards as explained 
        // at https://github.com/chraft/c-raft/wiki/Vectors,-Location,-Yaw-and-Pitch-in-C%23raft
        Vec3d theVec = parVec.normalize();
        return (float) Math.toDegrees(Math.asin(theVec.y));
    }
}
