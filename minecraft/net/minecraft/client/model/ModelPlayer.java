package net.minecraft.client.model;

import java.util.ArrayList;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelPlayer extends ModelBiped
{
    public ModelRenderer bipedLeftArmwear;
    public ModelRenderer bipedRightArmwear;
    public ModelRenderer bipedLeftLegwear;
    public ModelRenderer bipedRightLegwear;
    public ModelRenderer bipedBodyWear;
    private ModelRenderer bipedCape;
    private ModelRenderer bipedDeadmau5Head;
    private ArrayList<ModelRenderer> msAfro = new ArrayList<ModelRenderer>();
    private ArrayList<ModelRenderer> msSunglasses = new ArrayList<ModelRenderer>();
    private ArrayList<ModelRenderer> msShades = new ArrayList<ModelRenderer>();
    
    public float ticks_2;
    public float ticks_3;
    public float ticks_4;
    public float ticks_5;
    public float ticks_6;
    
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;   
    
    private boolean smallArms;
    private static final String __OBFID = "CL_00002626";

    public ModelPlayer(float p_i46304_1_, boolean p_i46304_2_)
    {
        super(p_i46304_1_, 0.0F, 64, 64);
        
        ModelRenderer mr = new ModelRenderer(this, 0, 0);
        this.msAfro = msAfro(mr);
        mr = new ModelRenderer(this, 0, 0);
        this.msSunglasses = msSunglasses(mr);
        mr = new ModelRenderer(this, 0, 0);
        this.msShades = msShades(mr);
        
        this.smallArms = p_i46304_2_;
        this.bipedDeadmau5Head = new ModelRenderer(this, 24, 0);
        this.bipedDeadmau5Head.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, p_i46304_1_);
        this.bipedCape = new ModelRenderer(this, 0, 0);
        this.bipedCape.setTextureSize(64, 32);
        this.bipedCape.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, p_i46304_1_);
        
        if (p_i46304_2_)
        {
            this.bipedLeftArm = new ModelRenderer(this, 32, 48);
            this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, p_i46304_1_);
            this.bipedLeftArm.setRotationPoint(5.0F, 2.5F, 0.0F);
            this.bipedRightArm = new ModelRenderer(this, 40, 16);
            this.bipedRightArm.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, p_i46304_1_);
            this.bipedRightArm.setRotationPoint(-5.0F, 2.5F, 0.0F);
            this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
            this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, p_i46304_1_ + 0.25F);
            this.bipedLeftArmwear.setRotationPoint(5.0F, 2.5F, 0.0F);
            this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
            this.bipedRightArmwear.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, p_i46304_1_ + 0.25F);
            this.bipedRightArmwear.setRotationPoint(-5.0F, 2.5F, 10.0F);
        }
        else
        {
            this.bipedLeftArm = new ModelRenderer(this, 32, 48);
            this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, p_i46304_1_);
            this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
            this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
            this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, p_i46304_1_ + 0.25F);
            this.bipedLeftArmwear.setRotationPoint(5.0F, 2.0F, 0.0F);
            this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
            this.bipedRightArmwear.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, p_i46304_1_ + 0.25F);
            this.bipedRightArmwear.setRotationPoint(-5.0F, 2.0F, 10.0F);
        }
        this.bipedLeftLeg = new ModelRenderer(this, 16, 48);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i46304_1_);
        this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.bipedLeftLegwear = new ModelRenderer(this, 0, 48);
        this.bipedLeftLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i46304_1_ + 0.25F);
        this.bipedLeftLegwear.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.bipedRightLegwear = new ModelRenderer(this, 0, 32);
        this.bipedRightLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i46304_1_ + 0.25F);
        this.bipedRightLegwear.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.bipedBodyWear = new ModelRenderer(this, 16, 32);
        this.bipedBodyWear.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, p_i46304_1_ + 0.25F);
        this.bipedBodyWear.setRotationPoint(0.0F, 0.0F, 0.0F);
        
    }

    public void setRotation(ModelRenderer modelRenderer, float xRotation, float yRotation, float zRotation)
    {
      modelRenderer.rotateAngleX = xRotation;
      modelRenderer.rotateAngleY = yRotation;
      modelRenderer.rotateAngleZ = zRotation;
    }
    
    public static float interpolateRotation(float par1, float par2, float par3)
    {
        float f;

        for (f = par2 - par1; f < -180.0F; f += 360.0F)
        {
            ;
        }

        while (f >= 180.0F)
        {
            f -= 360.0F;
        }

        return par1 + par3 * f;
    }
    
    public static float getFirstRotationX(AbstractClientPlayer clientPlayer, float partialTicks)
    {
        float f = interpolateRotation(clientPlayer.prevRenderYawOffset, clientPlayer.renderYawOffset, partialTicks);
        float f1 = interpolateRotation(clientPlayer.prevRotationYawHead, clientPlayer.rotationYawHead, partialTicks);
        float f2 = f1 - f;

        if (clientPlayer.isRiding() && clientPlayer.ridingEntity instanceof EntityLivingBase)
        {
            EntityLivingBase entitylivingbase = (EntityLivingBase)clientPlayer.ridingEntity;
            f = interpolateRotation(entitylivingbase.prevRenderYawOffset, entitylivingbase.renderYawOffset, partialTicks);
            f2 = f1 - f;
            float f3 = MathHelper.wrapAngleTo180_float(f2);

            if (f3 < -85.0F)
            {
                f3 = -85.0F;
            }

            if (f3 >= 85.0F)
            {
                f3 = 85.0F;
            }

            f = f1 - f3;

            if (f3 * f3 > 2500.0F)
            {
                float f4 = f + f3 * 0.2F;
            }
        }

        return f2;
    }
    
    public static float getSecondRotationX(AbstractClientPlayer clientPlayer, float partialTicks)
    {
        return clientPlayer.prevRotationPitch + (clientPlayer.rotationPitch - clientPlayer.prevRotationPitch) * partialTicks;
    }
    
    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float scale)
    {
        super.render(entityIn, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale);
        GlStateManager.pushMatrix();
        this.updateValues(entityIn, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale);

        if (this.isChild)
        {
            float f = 2.0F;
            GlStateManager.scale(1.0F / f, 1.0F / f, 1.0F / f);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.bipedLeftLegwear.render(scale);
            this.bipedRightLegwear.render(scale);
            this.bipedLeftArmwear.render(scale);
            this.bipedRightArmwear.render(scale);
            this.bipedBodyWear.render(scale);
        }
        else
        {
            if (entityIn.isSneaking())
            {
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
            }            

            this.bipedLeftLegwear.render(scale);
            this.bipedRightLegwear.render(scale);
            this.bipedLeftArmwear.render(scale);
            this.bipedRightArmwear.render(scale);
            this.bipedBodyWear.render(scale);
        }

        GlStateManager.popMatrix();
    }
    
    public void updateValues(Entity entityIn, float ticks_2, float ticks_3, float ticks_4, float ticks_5, float ticks_6)
    {
      this.ticks_2 = ticks_2;
      this.ticks_3 = ticks_3;
      this.ticks_4 = ticks_4;
      this.ticks_5 = ticks_5;
      this.ticks_6 = ticks_6;
    }

    public void renderDeadmau5Head(float p_178727_1_)
    {
        copyModelAngles(this.bipedHead, this.bipedDeadmau5Head);
        this.bipedDeadmau5Head.rotationPointX = 0.0F;
        this.bipedDeadmau5Head.rotationPointY = 0.0F;
        this.bipedDeadmau5Head.render(p_178727_1_);
    }

    public void renderCape(float p_178728_1_)
    {
        this.bipedCape.render(p_178728_1_);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entityIn)
    {
        super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, entityIn);
        copyModelAngles(this.bipedLeftLeg, this.bipedLeftLegwear);
        copyModelAngles(this.bipedRightLeg, this.bipedRightLegwear);
        copyModelAngles(this.bipedLeftArm, this.bipedLeftArmwear);
        copyModelAngles(this.bipedRightArm, this.bipedRightArmwear);
        copyModelAngles(this.bipedBody, this.bipedBodyWear);
    }

    public void renderRightArm()
    {
        this.bipedRightArm.render(0.0625F);
        this.bipedRightArmwear.render(0.0625F);
    }

    public void renderLeftArm()
    {
        this.bipedLeftArm.render(0.0625F);
        this.bipedLeftArmwear.render(0.0625F);
    }

    public void setInvisible(boolean invisible)
    {
        super.setInvisible(invisible);
        this.bipedLeftArmwear.showModel = invisible;
        this.bipedRightArmwear.showModel = invisible;
        this.bipedLeftLegwear.showModel = invisible;
        this.bipedRightLegwear.showModel = invisible;
        this.bipedBodyWear.showModel = invisible;
        this.bipedCape.showModel = invisible;
        this.bipedDeadmau5Head.showModel = invisible;
    }

    public void postRenderArm(float scale)
    {
        if (this.smallArms)
        {
            ++this.bipedRightArm.rotationPointX;
            this.bipedRightArm.postRender(scale);
            --this.bipedRightArm.rotationPointX;
        }
        else
        {
            this.bipedRightArm.postRender(scale);
        }
    }
    
    public void renderMsAfro(float f) {
    	for (ModelRenderer mr : this.msAfro) {
    		mr.render(f);
    	}
    	
    }
    
    public void renderMsSunglasses(float f) {
    	for (ModelRenderer mr : this.msSunglasses) {
    		mr.render(f);
    	}
    }
    
    public void renderMsShades(float f) {
    	for (ModelRenderer mr : this.msShades) {
    		mr.render(f);
    	}
    }
    
    // rechts unten hinten -> positiv
    
    public ArrayList<ModelRenderer> msAfro(ModelRenderer mr) {
    	ArrayList<ModelRenderer> a = new ArrayList<ModelRenderer>();
    	mr.setTextureSize(256, 256);
    	
    	mr.addBox(-6, -2, -1, 1, 1, 4);
        a.add(mr);
        mr.addBox(-5, -2, -1, 1, 1, 5);
        a.add(mr);
        mr.addBox(-4, -2, -1, 1, 1, 6);
        a.add(mr);
        mr.addBox(-3, -2, -1, 6, 1, 7);
        a.add(mr);
        mr.addBox(3, -2, -1, 1, 1, 6);
        a.add(mr);
        mr.addBox(4, -2, -1, 1, 1, 5);
        a.add(mr);
        mr.addBox(5, -2, -1, 1, 1, 4);
        a.add(mr);
    	
    	mr.addBox(-7, -4, -4, 1, 2, 8);
        a.add(mr);
        mr.addBox(-6, -4, -5, 1, 2, 10);
        a.add(mr);
        
        mr.addBox(-5, -4, -6, 1, 2, 12);
        a.add(mr);
        mr.addBox(-4, -4, 0, 1, 2, 6);
        a.add(mr);
        mr.addBox(-3, -4, 0, 6, 2, 7);
        a.add(mr);
        mr.addBox(3, -4, 0, 1, 2, 6);
        a.add(mr);
        mr.addBox(4, -4, -6, 1, 2, 12);
        a.add(mr);
        
        mr.addBox(5, -4, -5, 1, 2, 10);
        a.add(mr);
        mr.addBox(6, -4, -4, 1, 2, 8);
        a.add(mr);
        
    	
    	mr.addBox(-8, -8, -4, 1, 4, 8);
        a.add(mr);
        mr.addBox(-7, -8, -5, 1, 4, 10);
        a.add(mr);
        mr.addBox(-6, -8, -6, 1, 4, 12);
        a.add(mr);
        
        mr.addBox(-5, -8, -7, 2, 3, 14);
        a.add(mr);
        mr.addBox(-3, -8, -8, 6, 3, 16);
        a.add(mr);
        mr.addBox(3, -8, -7, 2, 3, 14);
        a.add(mr);
        
        mr.addBox(-5, -5, -7, 1, 1, 14); // edited
        a.add(mr);
        mr.addBox(-4, -5, 0, 1, 1, 7); // edited
        a.add(mr);
        mr.addBox(-3, -5, 0, 6, 1, 8); // edited
        a.add(mr);
        mr.addBox(3, -5, 0, 1, 1, 7); // edited
        a.add(mr);
        mr.addBox(4, -5, -7, 1, 1, 14); // edited
        a.add(mr);
        
        mr.addBox(5, -8, -6, 1, 4, 12);
        a.add(mr);
        mr.addBox(6, -8, -5, 1, 4, 10);
        a.add(mr);
        mr.addBox(7, -8, -4, 1, 4, 8);
        a.add(mr);
        
        
        mr.addBox(-7, -10, -4, 1, 2, 8);
        a.add(mr);
        mr.addBox(-6, -10, -5, 1, 2, 10);
        a.add(mr);
        mr.addBox(-5, -10, -6, 2, 2, 12);
        a.add(mr);
        mr.addBox(-3, -10, -7, 6, 2, 14);
        a.add(mr);
        mr.addBox(3, -10, -6, 2, 2, 12);
        a.add(mr);
        mr.addBox(5, -10, -5, 1, 2, 10);
        a.add(mr);
        mr.addBox(6, -10, -4, 1, 2, 8);
        a.add(mr);
        
        mr.addBox(-6, -11, -3, 1, 1, 6);
        a.add(mr);
        mr.addBox(-5, -11, -4, 1, 1, 8);
        a.add(mr);
        mr.addBox(-4, -11, -5, 1, 1, 10);
        a.add(mr);
        mr.addBox(-3, -11, -6, 6, 1, 12);
        a.add(mr);
        mr.addBox(3, -11, -5, 1, 1, 10);
        a.add(mr);
        mr.addBox(4, -11, -4, 1, 1, 8);
        a.add(mr);
        mr.addBox(5, -11, -3, 1, 1, 6);
        a.add(mr);
        
        mr.addBox(-5, -12, -3, 1, 1, 6);
        a.add(mr);
        mr.addBox(-4, -12, -4, 1, 1, 8);
        a.add(mr);
        mr.addBox(-3, -12, -5, 6, 1, 10);
        a.add(mr);
        mr.addBox(3, -12, -4, 1, 1, 8);
        a.add(mr);
        mr.addBox(4, -12, -3, 1, 1, 6);
        a.add(mr);
        
        mr.addBox(-4, -13, -3, 2, 1, 6);
        a.add(mr);
        mr.addBox(-2, -13, -4, 4, 1, 8);
        a.add(mr);
        mr.addBox(2, -13, -3, 2, 1, 6);
        a.add(mr);
    	
        return a;
    }
    
    public ArrayList<ModelRenderer> msSunglasses(ModelRenderer mr) {
    	ArrayList<ModelRenderer> a = new ArrayList<ModelRenderer>();
    	
    	mr.setTextureSize(64, 64);
    	
    	mr.addBox(-5, -4, 0, 1, 1, 1);
    	a.add(mr);
    	mr.addBox(-5, -5, -4, 1, 1, 5);
    	a.add(mr);
    	
    	mr.addBox(-5, -5, -5, 4, 1, 1);
    	a.add(mr);
    	mr.addBox(-4, -4, -5, 1, 1, 1);
    	a.add(mr);
    	mr.addBox(-3, -3, -5, 2, 1, 1);
    	a.add(mr);
    	mr.addBox(-1, -4, -5, 2, 1, 1);
    	a.add(mr);
    	mr.addBox(1, -3, -5, 2, 1, 1);
    	a.add(mr);
    	mr.addBox(3, -4, -5, 1, 1, 1); 
    	a.add(mr);
    	mr.addBox(1, -5, -5, 4, 1, 1); 
    	a.add(mr);
    	
    	mr.addBox(4, -5, -4, 1, 1, 5);
    	a.add(mr);
    	mr.addBox(4, -4, 0, 1, 1, 1);
    	a.add(mr);
    	
    	return a;
    }
    
    public ArrayList<ModelRenderer> msShades(ModelRenderer mr) {
    	ArrayList<ModelRenderer> a = new ArrayList<ModelRenderer>();
    	
    	mr.setTextureSize(256, 256);
    	
    	mr.addBox(-3, -4, -5, 2, 1, 1);
    	a.add(mr);
    	mr.addBox(1, -4, -5, 2, 1, 1);
    	a.add(mr);
    	
    	return a;
    }
}
