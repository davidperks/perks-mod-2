package tom.objects.entity.ai;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import tom.objects.entity.EntityBirdOfPrey;
import tom.objects.entity.model.AIStates;
import tom.util.Utilities;

public class EntityBOPAI extends EntityAIBase
{
	private static final int TAKE_OFF_CHANCE_BASE = 2400;
	private static final int PERCH_CHANCE_BASE = 1;
	private static final double attackRegionSize = 5.0D;
	private static final float pitchChangeRate = 1.5f;
	private EntityBirdOfPrey theBird;
	private World world;
	public float yawChangeRate = 1.5f;
	private double maxHeight = 140D;
	
	//At the moment takes off reaches maxheight soars and falls to 90% then takes off to max height etc.
	//Add perching!!
	
	public EntityBOPAI(EntityBirdOfPrey theBird) 
	{
		this.theBird = theBird;
		this.world = theBird.world;
	}

	@Override
	public boolean shouldExecute() 
	{
		return true;
	}
	
	@Override
	public void updateTask() 
	{
		switch (theBird.getState())
		{
		case AIStates.STATE_TAKING_OFF:
		{
			updateStateTakingOff();
			break;
		}
		case AIStates.STATE_SOARING:
		{
			updateStateSoaring();
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
		case AIStates.STATE_DIVING:
		{
			updateStateDiving();
			break;
		}
		case AIStates.STATE_LANDING:
		{
			updateStateLanding();
			break;
		}
		case AIStates.STATE_ATTACKING:
		{
			updateStateAttacking();
			break;
		}
		}
	}

	private void updateStateAttacking() 
	{
		if (theBird.getAttackTarget() != null)
        {           
            theBird.motionY = -2.0D;
            double ticksToHitTarget = (theBird.posY - theBird.getAttackTarget().posY) / Math.abs(theBird.motionY);
            theBird.motionX = (theBird.getAttackTarget().posX - theBird.posX) / ticksToHitTarget;
            theBird.motionZ = (theBird.getAttackTarget().posZ - theBird.posZ) / ticksToHitTarget;
            updatePitch(Utilities.getPitchFromVec(new Vec3d(
                    theBird.motionX, 
                    theBird.motionY,
                    theBird.motionZ)));
        }     
		
		EntityLivingBase target = theBird.getAttackTarget();
		
        // check if target has been killed or despawned
        if (target == null)
        {
            theBird.setState(AIStates.STATE_TAKING_OFF);
        }
        else if (target.isDead)
        {
            theBird.setAttackTarget(null);
            theBird.setState(AIStates.STATE_TAKING_OFF);
        }
        else if (!Utilities.isCourseTraversable(theBird, target.posX, target.posY, target.posZ))
        {
            // theBird.setAttackTarget(null);
            theBird.setState(AIStates.STATE_TAKING_OFF);
        }
        // check for hitting target
        else if (theBird.getDistance(theBird.getAttackTarget())<2.0F)
        {
            theBird.getAttackTarget().attackEntityFrom(
                    DamageSource.causeMobDamage(theBird), 
                    (float) theBird.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
            theBird.setState(AIStates.STATE_TAKING_OFF);
        }
	}

	protected void updatePitch(float parPitch)
    {
        float angleDiff = parPitch - theBird.rotationPitch;
        // handle possibility that shortest path is to rotate the other way
        if (angleDiff > 0.0001F)
        {
            if (angleDiff > 180.0F)
            {
                angleDiff -= 360.0F;
            }           

            theBird.rotationPitch += pitchChangeRate;
        }
        else if (angleDiff < -0.0001F)
        {
            if (angleDiff < -180.0F)
            {
                angleDiff += 360.0F;
            }

            theBird.rotationPitch -= pitchChangeRate;
        }

        // clamp to avoid oscillation around target
        if (Math.abs(angleDiff) < pitchChangeRate)
        {
            theBird.rotationPitch = parPitch;
        }
    }

	private void updateStateLanding() 
	{
		//DEBUG
		
		System.out.println("Attempting to land!");
		
        theBird.motionX = theBird.getAnchorX() - theBird.posX;
        theBird.motionZ = theBird.getAnchorZ() - theBird.posZ;
        theBird.motionY = Math.min(-0.2F, theBird.motionY+0.2);
        
        if (hasLanded())
        {
            // DEBUG
            System.out.println("Made it to perch");

            theBird.setState(AIStates.STATE_PERCHED);
        }
	}

	private void updateStateDiving() 
	{
		//DEBUG
		
		System.out.println("Diving ...");
		
        theBird.motionX = theBird.getAnchorX() - theBird.posX;
        theBird.motionZ = theBird.getAnchorZ() - theBird.posZ;
        theBird.motionY = -2.0D;
        
		// check if should start landing
		if (theBird.getDistanceSq(
				theBird.getAnchorX(),
				theBird.getAnchorY(),
				theBird.getAnchorZ())<25)
		{
		    theBird.setState(AIStates.STATE_LANDING);
		}
  		else if (hasLanded())
      	{
          System.out.println("Made it to perch");

          theBird.setState(AIStates.STATE_PERCHED);
          stopMoving();
        }
	}

	private void updateStateTravelling() 
	{
		
        if (theBird.posY < maxHeight)
        {
            theBird.motionY = 0.1D;
        }

        moveForward(1.0D);
        
		if (theBird.posY >= maxHeight)
        {
            theBird.setState(AIStates.STATE_SOARING);
        }
	}

	private void updateStatePerched() 
	{
		processFrictionAndGravity();
		
		if (!hasLanded())
		{
			theBird.setState(AIStates.STATE_TAKING_OFF);
		}
		
		if (theBird.getRNG().nextInt(getTakeOffChance()) == 0)
        {
            theBird.setState(AIStates.STATE_TAKING_OFF);
        }

        // entity can get scared if player gets too close
        EntityPlayer closestPlayer = theBird.world.getClosestPlayerToEntity(theBird, 4.0D);
        if (closestPlayer != null)
        {
        	theBird.setState(AIStates.STATE_TAKING_OFF);
        }
	}

	private int getTakeOffChance() 
	{
		if (theBird.world.isRaining())
        {
            return TAKE_OFF_CHANCE_BASE * 1000;
        }

        if (theBird.isNocturnal())
        {
            if (!theBird.world.isDaytime())
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
            if (!theBird.world.isDaytime())
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
		AxisAlignedBB entityBoundingBox = theBird.getEntityBoundingBox().offset(new Vec3d(0.0D, -0.5D, 0.0D));

        if (!theBird.world.getCollisionBoxes(theBird, entityBoundingBox).isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
	}

	private void updateStateSoaring() 
	{
		theBird.motionY = -0.1D;
		
		moveForward(1.0D);
		
		if(theBird.isClockwise())
		{
			updateYaw(theBird.rotationYaw + yawChangeRate);
		}
		else
		{
			updateYaw(theBird.rotationYaw - yawChangeRate);
		}
		
		if(theBird.posY < maxHeight * 0.9)
		{
			theBird.setState(AIStates.STATE_TRAVELLING);
		}
		
		considerAttacking();
		
		if(theBird.getAttackTarget() == null)
		{
			considerPerching();
		}
		else
		{
			theBird.setState(AIStates.STATE_ATTACKING);
		}
	}

	private void considerAttacking() 
	{
		// DEBUG
        if (theBird.getAttackTarget() != null) System.out.println("Attack target = " + theBird.getAttackTarget());

        // handle case where previous target becomes unsuitable
        if (Utilities.isSuitableTarget(theBird, theBird.getAttackTarget(), false))
        {
            // DEBUG

            System.out.println(theBird.getAttackTarget() + " is no longer a suitable target");
            
            theBird.setAttackTarget(null);
        }

        processNaturalAttack(); // go for its natural prey
	}

	// detect if there is an attack target in region on ground directly below eagle
    public void processNaturalAttack()
    {
        // find target on ground
        AxisAlignedBB attackRegion = new AxisAlignedBB(
                theBird.posX - attackRegionSize, 
                theBird.world.getHeight((int)theBird.posX, (int)theBird.posZ) - attackRegionSize, 
                theBird.posZ - attackRegionSize, 
                theBird.posX + attackRegionSize, 
                theBird.world.getHeight((int)theBird.posX, (int)theBird.posZ) + attackRegionSize, 
                theBird.posZ + attackRegionSize);

        for (int i=0; i<theBird.getPreyArray().length; i++)
        {
            List possibleTargetEntities = theBird.world.getEntitiesWithinAABB(theBird.getPreyArray()[i], attackRegion);
            Iterator<Object> targetIterator = possibleTargetEntities.iterator();
            while (targetIterator.hasNext())
            {
                EntityLivingBase possibleTarget = (EntityLivingBase)(targetIterator.next());
                if (Utilities.isCourseTraversable(theBird, possibleTarget.posX, possibleTarget.posY, possibleTarget.posZ))
                {
                    theBird.setAttackTarget(possibleTarget);
                }
            }
        }
    }

	private void updateStateTakingOff() 
	{
		theBird.motionY = 0.1D;
		
		if (theBird.posY > maxHeight)
		{
			theBird.setState(AIStates.STATE_SOARING);
		}
		
		moveForward(1.0D);
		
		if(theBird.isClockwise())
		{
			updateYaw(theBird.rotationYaw + yawChangeRate);
		}
		else
		{
			updateYaw(theBird.rotationYaw - yawChangeRate);
		}
	}
	
	public void moveForward(double parSpeedFactor)

    {
        theBird.motionX = theBird.getLookVec().x * parSpeedFactor * theBird.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
        theBird.motionZ = theBird.getLookVec().z * parSpeedFactor * theBird.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
    }
	
	protected void stopMoving()
    {
        theBird.motionX = 0;
        theBird.motionY = 0;
        theBird.motionZ = 0;
    }
	
	protected void processFrictionAndGravity()
    {
        theBird.motionX *= 0.93D;
        theBird.motionZ *= 0.93D;
        theBird.motionY -= 0.04D;
    }

    protected void updateYaw(float parYaw)
    {
        float angleDiff = parYaw - theBird.rotationYaw;
        // handle possibility that shortest path is to rotate the other way

        if (angleDiff > 0.0001F)
        {
            if (angleDiff > 180.0F)
            {
                angleDiff -= 360.0F;
            }                   

            theBird.rotationYaw += yawChangeRate;
        }
        else if (angleDiff < -0.0001F)
        {
            if (angleDiff < -180.0F)
            {
                angleDiff += 360.0F;
            }

            theBird.rotationYaw -= yawChangeRate;
        }

        // clamp to avoid oscillation around target
        if (Math.abs(angleDiff) < yawChangeRate)
        {
            theBird.rotationYaw = parYaw;
        }
    }
    
    public void considerPerching()
    {
        // always try to perch starting at dusk
        if (theBird.getRNG().nextInt(getPerchChance()) == 0)
        {
            if (theBird.world.getBlockState(theBird.world.getTopSolidOrLiquidBlock(theBird.getPosition())).getBlock() instanceof BlockLeaves)
            {
                if (Utilities.isCourseTraversable(
                        theBird,
                        theBird.posX, 
                        theBird.world.getHeight(
                                (int)theBird.posX, 
                                (int)theBird.posZ), 
                        theBird.posZ))
                {
                    theBird.setState(AIStates.STATE_DIVING);
                    theBird.setAnchor(
                            theBird.posX, 
                            theBird.world.getHeight(
                                    (int)theBird.posX, 
                                    (int)theBird.posZ), 
                            theBird.posZ);
                }
            }
        }
    }

    

    public int getPerchChance()
    {
        if (theBird.world.isRaining())
        {
            return 1;
        }

        if (theBird.isNocturnal())
        {
            if (theBird.world.isDaytime())
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
            if (theBird.world.isDaytime())
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
