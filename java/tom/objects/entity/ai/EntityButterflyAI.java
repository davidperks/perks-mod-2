package tom.objects.entity.ai;

import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import tom.objects.entity.EntityBirdOfPrey;
import tom.objects.entity.EntityButterfly;
import tom.objects.entity.model.AIStates;
import tom.util.Utilities;

public class EntityButterflyAI extends EntityAIBase {
	//put clockwise back in so its a bit more random
	//add landing on flowers
	//animate perching with wings up
	//add scatter if player comes close
	//add multiple wings so they look like real butterflies
	
	private static final int TAKE_OFF_CHANCE_BASE = 2400;
	private static final int PERCH_CHANCE_BASE = 1;
	private static final double attackRegionSize = 5.0D;
	private static final float pitchChangeRate = 1.5f;
	private EntityButterfly theButterfly;
	private World world;
	public float yawChangeRate = 1.5f;
	private double maxHeight = 76D;

	public EntityButterflyAI(EntityButterfly theButterfly) 
	{
		this.theButterfly = theButterfly;
		this.world = theButterfly.world;
	}
	
	@Override
	public boolean shouldExecute() 
	{
		return true;
	}
	
	@Override
	public void updateTask() 
	{
		switch (theButterfly.getState())
		{
			case AIStates.STATE_TAKING_OFF:
			{
				updateStateTakingOff();
				break;
			}
			case AIStates.STATE_PERCHED:
			{
				updateStatePerched();
				break;
			}
			case AIStates.STATE_TRAVELLING:
			{
				updateStateTravelling();
				break;
			}
			case AIStates.STATE_LANDING:
			{
				updateStateLanding();
				break;
			}
			case AIStates.STATE_SOARING:
			{
				updateStateSoaring();
				break;
			}
		}
	}
	
	private void updateStateLanding() 
	{
		//DEBUG
		
		System.out.println("Attempting to land!");
		
        theButterfly.motionX = theButterfly.getAnchorX() - theButterfly.posX;
        theButterfly.motionZ = theButterfly.getAnchorZ() - theButterfly.posZ;
        theButterfly.motionY = Math.min(-0.2F, theButterfly.motionY+0.2);
        
        if (hasLanded())
        {
            // DEBUG
            System.out.println("Made it to perch");

            theButterfly.setState(AIStates.STATE_PERCHED);
        }
	}
	
	private void updateStateTravelling() 
	{
		
        if (theButterfly.posY < maxHeight)
        {
            theButterfly.motionY = 0.1D;
        }

        moveForward(1.0D);
        
		if (theButterfly.posY >= maxHeight)
        {
            theButterfly.setState(AIStates.STATE_SOARING);
        }
	}
	
	private void updateStatePerched() 
	{
		processFrictionAndGravity();
		
		if (!hasLanded())
		{
			theButterfly.setState(AIStates.STATE_TAKING_OFF);
		}
		
		if (theButterfly.getRNG().nextInt(getTakeOffChance()) == 0)
        {
            theButterfly.setState(AIStates.STATE_TAKING_OFF);
        }

        // entity can get scared if player gets too close
        EntityPlayer closestPlayer = theButterfly.world.getClosestPlayerToEntity(theButterfly, 4.0D);
        if (closestPlayer != null)
        {
        	theButterfly.setState(AIStates.STATE_TAKING_OFF);
        }
	}

	private int getTakeOffChance() 
	{
		if (theButterfly.world.isRaining())
        {
            return TAKE_OFF_CHANCE_BASE * 1000;
        }

        if (theButterfly.isNocturnal())
        {
            if (!theButterfly.world.isDaytime())
            {
                return TAKE_OFF_CHANCE_BASE;
            }
            else
            {
                return TAKE_OFF_CHANCE_BASE * 100;
            }
        }
        else
        {
            if (!theButterfly.world.isDaytime())
            {
                return TAKE_OFF_CHANCE_BASE * 100;
            }
            else
            {
                return TAKE_OFF_CHANCE_BASE;
            }
        }
	}

	private boolean hasLanded()
	{
		AxisAlignedBB entityBoundingBox = theButterfly.getEntityBoundingBox().offset(new Vec3d(0.0D, -0.5D, 0.0D));

        if (!theButterfly.world.getCollisionBoxes(theButterfly, entityBoundingBox).isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
	}
	
	private void updateStateTakingOff() 
	{
		theButterfly.motionY = 0.1D;
		
		if (theButterfly.posY > maxHeight)
		{
			theButterfly.setState(AIStates.STATE_SOARING);
		}
		
		moveForward(1.0D);
		
		updateYaw(theButterfly.rotationYaw - yawChangeRate);
	}
	
	private void updateStateSoaring() 
	{
		theButterfly.motionY = -0.1D;
		
		moveForward(1.0D);
		
		updateYaw(theButterfly.rotationYaw - yawChangeRate);
		
		if(theButterfly.posY < maxHeight * 0.9)
		{
			theButterfly.setState(AIStates.STATE_TRAVELLING);
		}
		
		considerPerching();
	}
	
	public void moveForward(double parSpeedFactor)

    {
        theButterfly.motionX = theButterfly.getLookVec().x * parSpeedFactor * theButterfly.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
        theButterfly.motionZ = theButterfly.getLookVec().z * parSpeedFactor * theButterfly.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
    }
	
	protected void stopMoving()
    {
        theButterfly.motionX = 0;
        theButterfly.motionY = 0;
        theButterfly.motionZ = 0;
    }
	
	protected void processFrictionAndGravity()
    {
        theButterfly.motionX *= 0.93D;
        theButterfly.motionZ *= 0.93D;
        theButterfly.motionY -= 0.04D;
    }
	
	protected void updateYaw(float parYaw)
    {
        float angleDiff = parYaw - theButterfly.rotationYaw;
        // handle possibility that shortest path is to rotate the other way

        if (angleDiff > 0.0001F)
        {
            if (angleDiff > 180.0F)
            {
                angleDiff -= 360.0F;
            }                   

            theButterfly.rotationYaw += yawChangeRate;
        }
        else if (angleDiff < -0.0001F)
        {
            if (angleDiff < -180.0F)
            {
                angleDiff += 360.0F;
            }

            theButterfly.rotationYaw -= yawChangeRate;
        }

        // clamp to avoid oscillation around target
        if (Math.abs(angleDiff) < yawChangeRate)
        {
            theButterfly.rotationYaw = parYaw;
        }
    }
    
    public void considerPerching()
    {
        // always try to perch starting at dusk
        if (theButterfly.getRNG().nextInt(getPerchChance()) == 0)
        {
            if (theButterfly.world.getBlockState(theButterfly.world.getTopSolidOrLiquidBlock(theButterfly.getPosition())).getBlock() instanceof BlockLeaves)
            {
                if (Utilities.isCourseTraversable(
                        theButterfly,
                        theButterfly.posX, 
                        theButterfly.world.getHeight(
                                (int)theButterfly.posX, 
                                (int)theButterfly.posZ), 
                        theButterfly.posZ))
                {
                    theButterfly.setState(AIStates.STATE_DIVING);
                    theButterfly.setAnchor(
                            theButterfly.posX, 
                            theButterfly.world.getHeight(
                                    (int)theButterfly.posX, 
                                    (int)theButterfly.posZ), 
                            theButterfly.posZ);
                }
            }
        }
    }

    

    public int getPerchChance()
    {
        if (theButterfly.world.isRaining())
        {
            return 1;
        }

        if (theButterfly.isNocturnal())
        {
            if (theButterfly.world.isDaytime())
            {
                return PERCH_CHANCE_BASE;
            }
            else
            {
                return PERCH_CHANCE_BASE * 10000;
            }
        }
        else
        {
            if (theButterfly.world.isDaytime())
            {
                return PERCH_CHANCE_BASE * 10000;
            }
            else
            {
                return PERCH_CHANCE_BASE;
            }
        }
    }


}
