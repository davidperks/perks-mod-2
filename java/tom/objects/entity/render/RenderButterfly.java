package tom.objects.entity.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import tom.objects.entity.EntityButterfly;
import tom.objects.entity.model.ModelButterfly;
import tom.objects.entity.model.ModelCentaur;
import tom.util.Reference;

public class RenderButterfly extends RenderLiving
{
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/butterfly.png");

	public RenderButterfly(RenderManager rendermanagerIn) 
	{
		super(rendermanagerIn, new ModelButterfly(), 0.5F);
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