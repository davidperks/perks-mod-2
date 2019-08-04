package tom.objects.entity;

import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import tom.objects.entity.ai.EntityBOPAI;
import tom.objects.entity.ai.EntityButterflyAI;
import tom.objects.entity.model.AIStates;
import tom.util.handlers.SoundHandler;

public class EntityButterfly extends EntityFlying 
{
	private int state;
	private float scaleFactor;
	private int random;
	
	private static final DataParameter<Integer> STATE = EntityDataManager.createKey(EntityButterfly.class,  DataSerializers.VARINT);
	private static final DataParameter<Float> SCALE_FACTOR = EntityDataManager.createKey(EntityButterfly.class,  DataSerializers.FLOAT);
	private static final DataParameter<Float> ANCHOR_X = EntityDataManager.createKey(EntityButterfly.class,  DataSerializers.FLOAT);
	private static final DataParameter<Float> ANCHOR_Y = EntityDataManager.createKey(EntityButterfly.class,  DataSerializers.FLOAT);
	private static final DataParameter<Float> ANCHOR_Z = EntityDataManager.createKey(EntityButterfly.class,  DataSerializers.FLOAT);
	
	public EntityButterfly(World worldIn) 
	{
		super(worldIn);
		
		random = rand.nextInt(10);
		setState(AIStates.STATE_TAKING_OFF);
		setScaleFactor(1.0F);
	}
	
	@Override
	protected void initEntityAI() 
	{
		//need to AI task
		super.initEntityAI();
		tasks.addTask(1, new EntityButterflyAI(this));
	}

	@Override
	public boolean isAIDisabled() 
	{
		return false;
	}
	
	@Override
	protected boolean canDespawn() 
	{
		return false;
	}
	
	@Override
	protected void applyEntityAttributes() 
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25F);
	}
	
	@Override
	protected void entityInit() 
	{
		super.entityInit();
		this.getDataManager().register(STATE, Integer.valueOf(AIStates.STATE_TAKING_OFF));
		this.getDataManager().register(SCALE_FACTOR, Float.valueOf(1.0F));
		this.getDataManager().register(ANCHOR_X, Float.valueOf(0F));
		this.getDataManager().register(ANCHOR_Y, Float.valueOf(0F));
		this.getDataManager().register(ANCHOR_Z, Float.valueOf(0F));
		//this.getDataManager().register(CLOCKWISE, Boolean.valueOf(world.rand.nextBoolean()));
	}

	public int getState() 
	{
		return this.getDataManager().get(STATE).intValue();
	}
	
	public void setState(int state)
	{
		this.getDataManager().set(STATE, Integer.valueOf(state));
	}

	public float getScaleFactor() 
	{
		return this.getDataManager().get(SCALE_FACTOR).floatValue();
	}
	
	public void setScaleFactor(float scaleFactor)
	{
		this.getDataManager().set(SCALE_FACTOR, Float.valueOf(scaleFactor));
	}
	
	public double getAnchorX()
	{
		return this.getDataManager().get(ANCHOR_X).floatValue();
	}
	
	public double getAnchorY()
	{
		return this.getDataManager().get(ANCHOR_X).floatValue();
	}
	
	public double getAnchorZ()
	{
		return this.getDataManager().get(ANCHOR_X).floatValue();
	}
	
	public void setAnchor(double anchor_x, double anchor_y, double anchor_z)
	{
		this.getDataManager().set(ANCHOR_X, Float.valueOf((float)anchor_x));
		this.getDataManager().set(ANCHOR_Y, Float.valueOf((float)anchor_y));
		this.getDataManager().set(ANCHOR_Z, Float.valueOf((float)anchor_z));
	}

	public int getRandFactor() 
	{
		return random;
	}
	
	public boolean isNocturnal()
    {
        return false;
    }

	public Class[] getPreyArray() 
	{
		return null;
	}
	
	@Override
	protected SoundEvent getAmbientSound() 
	{
		return null;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) 
	{
		return null;
	}
	
	@Override
	protected SoundEvent getDeathSound() 
	{
		return null;
	}
	
	@Override
	protected boolean makeFlySound() 
	{
		return false;
	}
}
