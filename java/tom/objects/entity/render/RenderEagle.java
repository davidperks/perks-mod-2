package tom.objects.entity.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import tom.objects.entity.model.ModelBirdOfPrey;
import tom.objects.entity.model.ModelCentaur;
import tom.util.Reference;

public class RenderEagle extends RenderLiving
{
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/eagle.png");

	public RenderEagle(RenderManager rendermanagerIn) 
	{
		super(rendermanagerIn, new ModelBirdOfPrey(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		return TEXTURES;
	}
	
	@Override
	protected void applyRotations(EntityLivingBase entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) 
	{
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}

}
