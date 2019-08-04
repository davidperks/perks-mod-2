package tom.objects.entity;

import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import tom.objects.entity.ai.EntityBOPAI;
import tom.objects.entity.model.AIStates;
import tom.util.handlers.SoundHandler;

public class EntityBirdOfPrey extends EntityFlying 
{
	private int state;
	private float scaleFactor;
	private int random;
	
	private Class[] preyArray = new Class[] {EntityChicken.class, EntityBat.class, EntityPig.class, EntityRabbit.class};
	
	private static final DataParameter<Integer> STATE = EntityDataManager.createKey(EntityBirdOfPrey.class,  DataSerializers.VARINT);
	private static final DataParameter<Float> SCALE_FACTOR = EntityDataManager.createKey(EntityBirdOfPrey.class,  DataSerializers.FLOAT);
	private static final DataParameter<Float> ANCHOR_X = EntityDataManager.createKey(EntityBirdOfPrey.class,  DataSerializers.FLOAT);
	private static final DataParameter<Float> ANCHOR_Y = EntityDataManager.createKey(EntityBirdOfPrey.class,  DataSerializers.FLOAT);
	private static final DataParameter<Float> ANCHOR_Z = EntityDataManager.createKey(EntityBirdOfPrey.class,  DataSerializers.FLOAT);
	private static final DataParameter<Boolean> CLOCKWISE = EntityDataManager.createKey(EntityBirdOfPrey.class,  DataSerializers.BOOLEAN);
	
	public EntityBirdOfPrey(World worldIn) 
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
		tasks.addTask(1, new EntityBOPAI(this));
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
		getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
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
		this.getDataManager().register(CLOCKWISE, Boolean.valueOf(world.rand.nextBoolean()));
	}

	public int getState() 
	{
		return this.getDataManager().get(STATE).intValue();
	}
	
	public void setState(int state)
	{
		this.getDataManager().set(STATE, Integer.valueOf(state));
	}
	
	public boolean isClockwise()
	{
		return this.getDataManager().get(CLOCKWISE).booleanValue();
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
		return preyArray;
	}
	
	@Override
	public void setAttackTarget(EntityLivingBase entitylivingbaseIn) 
	{
		System.out.println("Setting attack target to = " + entitylivingbaseIn);
		super.setAttackTarget(entitylivingbaseIn);
	}
	
	@Override
	protected SoundEvent getAmbientSound() 
	{
		return SoundHandler.ENTITY_BIRD_AMBIENT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) 
	{
		return SoundHandler.ENTITY_BIRD_HURT;
	}
	
	@Override
	protected SoundEvent getDeathSound() 
	{
		return SoundHandler.ENTITY_BIRD_DEATH;
	}
	
	@Override
	protected float playFlySound(float fl) 
	{
		this.playSound(SoundHandler.ENTITY_BIRD_FLAPPING, 0.15f, 1.0f);
		return fl;
	}
	@Override
	protected boolean makeFlySound() 
	{
		return true;
	}
}
