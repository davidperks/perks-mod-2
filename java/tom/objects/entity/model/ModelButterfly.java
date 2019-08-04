package tom.objects.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import tom.objects.entity.EntityButterfly;

/**
 * Butterfly - David Perks
 * Created using Tabula 7.0.1
 */
public class ModelButterfly extends ModelBase {
    public ModelRenderer Wing1;
    public ModelRenderer Wing2;

    public ModelButterfly() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Wing1 = new ModelRenderer(this, 0, 0);
        this.Wing1.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.Wing1.addBox(-12.0F, 0.0F, -5.0F, 12, 0, 10, 0.0F);
        this.Wing2 = new ModelRenderer(this, 0, 0);
        this.Wing2.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.Wing2.addBox(0.0F, 0.0F, -5.0F, 12, 0, 10, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Wing1.render(f5);
        this.Wing2.render(f5);
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
    	this.Wing1.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    	this.Wing2.rotateAngleZ = -MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }
}
