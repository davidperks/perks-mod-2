package tom.objects.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelHamster - David Perks
 * Created using Tabula 7.0.1
 */
public class ModelHamster extends ModelPerksAnimals 
{
    public ModelRenderer body;
    public ModelRenderer leg_back_left;
    public ModelRenderer leg_front_left;
    public ModelRenderer leg_back_right;
    public ModelRenderer leg_front_right;
    public ModelRenderer head;
    public ModelRenderer tail;

    public ModelHamster() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 11.0F, 2.0F);
        this.body.addBox(-5.0F, -10.0F, -9.6F, 7, 14, 6, 0.0F);
        this.setRotateAngle(body, 1.5707963267948966F, 0.0F, 0.0F);
        this.leg_back_right = new ModelRenderer(this, 36, 0);
        this.leg_back_right.setRotationPoint(-3.0F, 18.0F, 6.0F);
        this.leg_back_right.addBox(-2.0F, 2.6F, -2.0F, 2, 3, 2, 0.0F);
        this.leg_back_left = new ModelRenderer(this, 20, 0);
        this.leg_back_left.setRotationPoint(2.0F, 17.7F, 6.0F);
        this.leg_back_left.addBox(-2.0F, 2.6F, -2.0F, 2, 3, 2, 0.0F);
        this.tail = new ModelRenderer(this, 48, 1);
        this.tail.setRotationPoint(-2.8F, 15.0F, 2.8F);
        this.tail.addBox(0.0F, 0.0F, 0.0F, 3, 3, 4, 0.0F);
        this.leg_front_right = new ModelRenderer(this, 44, 0);
        this.leg_front_right.setRotationPoint(-3.0F, 18.0F, -5.0F);
        this.leg_front_right.addBox(-2.0F, 2.6F, -3.0F, 2, 3, 2, 0.0F);
        this.leg_front_left = new ModelRenderer(this, 28, 0);
        this.leg_front_left.setRotationPoint(2.0F, 18.0F, -6.0F);
        this.leg_front_left.addBox(-2.0F, 2.6F, -1.9F, 2, 3, 2, 0.0F);
        this.head = new ModelRenderer(this, 26, 5);
        this.head.setRotationPoint(-0.6F, 16.7F, -5.3F);
        this.head.addBox(-4.0F, -1.4F, -7.8F, 6, 4, 5, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.body.render(f5);
        this.leg_back_right.render(f5);
        this.leg_back_left.render(f5);
        this.tail.render(f5);
        this.leg_front_right.render(f5);
        this.leg_front_left.render(f5);
        this.head.render(f5);
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
    	this.leg_front_left.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    	this.leg_back_right.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    	this.leg_back_left.rotateAngleX = -MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    	this.leg_front_right.rotateAngleX = -MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    	
    	//need to centre head correctly for this to work.
    	//Adjust the model.
    	this.head.rotateAngleY = netHeadYaw * 0.01745F;
    	this.head.rotateAngleX = headPitch * 0.01745F;
    }
}
