package tom.objects.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelCow - Either Mojang or a mod author
 * Created using Tabula 7.0.1
 */
public class ModelCentaur extends ModelBase {
    public ModelRenderer CowBody;
    public ModelRenderer CowUdders;
    public ModelRenderer CowBackLeftLeg;
    public ModelRenderer CowFrontLeftLeg;
    public ModelRenderer CowBackRightLeg;
    public ModelRenderer CowFrontRightLeg;
    public ModelRenderer VillagerHead;

    public ModelCentaur() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.CowBackLeftLeg = new ModelRenderer(this, 0, 16);
        this.CowBackLeftLeg.setRotationPoint(4.0F, 12.0F, 7.0F);
        this.CowBackLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.CowFrontLeftLeg = new ModelRenderer(this, 0, 16);
        this.CowFrontLeftLeg.setRotationPoint(4.0F, 12.0F, -6.0F);
        this.CowFrontLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.CowFrontRightLeg = new ModelRenderer(this, 0, 16);
        this.CowFrontRightLeg.setRotationPoint(-4.0F, 12.0F, -6.0F);
        this.CowFrontRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.VillagerHead = new ModelRenderer(this, 0, 0);
        this.VillagerHead.setRotationPoint(0.0F, 0.0F, -5.0F);
        this.VillagerHead.addBox(-4.1F, -8.0F, -5.0F, 8, 10, 8, 0.0F);
        this.CowBackRightLeg = new ModelRenderer(this, 0, 16);
        this.CowBackRightLeg.setRotationPoint(-4.0F, 12.0F, 7.0F);
        this.CowBackRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.CowUdders = new ModelRenderer(this, 52, 0);
        this.CowUdders.setRotationPoint(0.0F, 5.0F, 2.0F);
        this.CowUdders.addBox(-2.0F, 2.0F, -8.0F, 4, 6, 1, 0.0F);
        this.setRotateAngle(CowUdders, 1.5707963267948966F, 0.0F, 0.0F);
        this.CowBody = new ModelRenderer(this, 18, 4);
        this.CowBody.setRotationPoint(0.0F, 5.0F, 2.0F);
        this.CowBody.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, 0.0F);
        this.setRotateAngle(CowBody, 1.5707963267948966F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.CowBackRightLeg.render(f5);
        this.VillagerHead.render(f5);
        this.CowBackLeftLeg.render(f5);
        this.CowFrontRightLeg.render(f5);
        this.CowUdders.render(f5);
        this.CowFrontLeftLeg.render(f5);
        this.CowBody.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) 
    {
    	this.CowFrontLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    	this.CowBackRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    	this.CowBackLeftLeg.rotateAngleX = -MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    	this.CowFrontRightLeg.rotateAngleX = -MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    	
    	//need to centre head correctly for this to work.
    	//Adjust the model.
    	this.VillagerHead.rotateAngleY = netHeadYaw * 0.01745F;
    	this.VillagerHead.rotateAngleX = headPitch * 0.01745F;
    }
}
