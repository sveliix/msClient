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
    
    // disco dude
    // afro
    private ArrayList<ModelRenderer> msAfro = new ArrayList<ModelRenderer>();
    private ArrayList<ModelRenderer> msSunglasses = new ArrayList<ModelRenderer>();
    private ArrayList<ModelRenderer> msShades = new ArrayList<ModelRenderer>();
    
    // lord
    // tophat
    private ArrayList<ModelRenderer> msTophat = new ArrayList<ModelRenderer>();
    private ArrayList<ModelRenderer> msTophatEdge = new ArrayList<ModelRenderer>();
    
    public float ticks_2;
    public float ticks_3;
    public float ticks_4;
    public float ticks_5;
    public float ticks_6;
    
    // TODO temporary solution
    public boolean doAfro = true;
    public boolean doSunglasses = true;
    public boolean doTophat = false;
    
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
        mr = new ModelRenderer(this, 0, 0);
        this.msTophat = msTophat(mr);
        mr = new ModelRenderer(this, 0, 0);
        this.msTophatEdge = msTophatEdge(mr);
        
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
    	if (doAfro) {
    		for (ModelRenderer mr : this.msAfro) {
    			mr.render(f);
    		}
    	}
    	
    }
    
    public void renderMsSunglasses(float f) {
    	if (doSunglasses) {
    		for (ModelRenderer mr : this.msSunglasses) {
    			mr.render(f);
    		}
    	}
    }
    
    public void renderMsShades(float f) {
    	if (doSunglasses) {
    		for (ModelRenderer mr : this.msShades) {
    			mr.render(f);
    		}
    	}
    }
    
    public void renderMsTophat(float f) {
    	if (doTophat) {
    		for (ModelRenderer mr : this.msTophat) {
    			mr.render(f);
    		}
    	}
    }
    
    public void renderMsTophatEdge(float f) {
    	if (doTophat) {
    		for (ModelRenderer mr : this.msTophatEdge) {
    			mr.render(f);
    		}
    	}
    }
    
    // rechts unten hinten -> positiv
    
    public ArrayList<ModelRenderer> msAfro(ModelRenderer mr) {
    	ArrayList<ModelRenderer> a = new ArrayList<ModelRenderer>();
    	mr.setTextureSize(256, 256);

    	mr.addBox(-6, -2, -1, 1, 1, 4); a.add(mr);
        mr.addBox(-5, -2, -1, 1, 1, 5); a.add(mr);
        mr.addBox(-4, -2, -1, 1, 1, 6); a.add(mr);
        mr.addBox(-3, -2, -1, 6, 1, 7); a.add(mr);
        mr.addBox(3, -2, -1, 1, 1, 6); a.add(mr);
        mr.addBox(4, -2, -1, 1, 1, 5); a.add(mr);
        mr.addBox(5, -2, -1, 1, 1, 4); a.add(mr);
    	
    	mr.addBox(-7, -4, -4, 1, 2, 8); a.add(mr);
        mr.addBox(-6, -4, -5, 1, 2, 10); a.add(mr);
        mr.addBox(-5, -4, -6, 1, 2, 12); a.add(mr);
        
        mr.addBox(-4, -4, 0, 1, 2, 6); a.add(mr);
        mr.addBox(-3, -4, 0, 6, 2, 7); a.add(mr);
        mr.addBox(3, -4, 0, 1, 2, 6); a.add(mr);
        
        mr.addBox(4, -4, -6, 1, 2, 12); a.add(mr);
        mr.addBox(5, -4, -5, 1, 2, 10); a.add(mr);
        mr.addBox(6, -4, -4, 1, 2, 8); a.add(mr);
        
    	
    	mr.addBox(-8, -8, -4, 1, 4, 8); a.add(mr);
        mr.addBox(-7, -8, -5, 1, 4, 10); a.add(mr);
        mr.addBox(-6, -8, -6, 1, 4, 12); a.add(mr);
        
        mr.addBox(-5, -8, -7, 2, 3, 14); a.add(mr);
        mr.addBox(-3, -8, -8, 6, 3, 16); a.add(mr);
        mr.addBox(3, -8, -7, 2, 3, 14); a.add(mr);
        
        mr.addBox(-5, -5, -7, 1, 1, 14); a.add(mr);
        mr.addBox(-4, -5, 0, 1, 1, 7); a.add(mr);
        mr.addBox(-3, -5, 0, 6, 1, 8); a.add(mr);
        mr.addBox(3, -5, 0, 1, 1, 7); a.add(mr);
        mr.addBox(4, -5, -7, 1, 1, 14); a.add(mr);
        
        mr.addBox(5, -8, -6, 1, 4, 12); a.add(mr);
        mr.addBox(6, -8, -5, 1, 4, 10); a.add(mr);
        mr.addBox(7, -8, -4, 1, 4, 8); a.add(mr);
        
        
        mr.addBox(-7, -10, -4, 1, 2, 8); a.add(mr);
        mr.addBox(-6, -10, -5, 1, 2, 10); a.add(mr);
        mr.addBox(-5, -10, -6, 2, 2, 12); a.add(mr);
        mr.addBox(-3, -10, -7, 6, 2, 14); a.add(mr);
        mr.addBox(3, -10, -6, 2, 2, 12); a.add(mr);
        mr.addBox(5, -10, -5, 1, 2, 10); a.add(mr);
        mr.addBox(6, -10, -4, 1, 2, 8); a.add(mr);
        
        mr.addBox(-6, -11, -3, 1, 1, 6); a.add(mr);
        mr.addBox(-5, -11, -4, 1, 1, 8); a.add(mr);
        mr.addBox(-4, -11, -5, 1, 1, 10); a.add(mr);
        mr.addBox(-3, -11, -6, 6, 1, 12); a.add(mr);
        mr.addBox(3, -11, -5, 1, 1, 10); a.add(mr);
        mr.addBox(4, -11, -4, 1, 1, 8); a.add(mr);
        mr.addBox(5, -11, -3, 1, 1, 6); a.add(mr);
        
        mr.addBox(-5, -12, -3, 1, 1, 6); a.add(mr);
        mr.addBox(-4, -12, -4, 1, 1, 8); a.add(mr);
        mr.addBox(-3, -12, -5, 6, 1, 10); a.add(mr);
        mr.addBox(3, -12, -4, 1, 1, 8); a.add(mr);
        mr.addBox(4, -12, -3, 1, 1, 6); a.add(mr);
        
        mr.addBox(-4, -13, -3, 2, 1, 6); a.add(mr);
        mr.addBox(-2, -13, -4, 4, 1, 8); a.add(mr);
        mr.addBox(2, -13, -3, 2, 1, 6); a.add(mr);
        
        return a;
    }
    
    public ArrayList<ModelRenderer> msSunglasses(ModelRenderer mr) {
    	ArrayList<ModelRenderer> a = new ArrayList<ModelRenderer>();
    	
    	mr.setTextureSize(64, 64);
    	
    	mr.addBox(-9, -8, 1, 1, 2, 1);
    	a.add(mr);
    	mr.addBox(-9, -10, -8, 1, 2, 10);
    	a.add(mr);
    	
    	mr.addBox(-9, -10, -10, 7, 1, 2);
    	a.add(mr);
    	mr.addBox(-9, -9, -10, 2, 1, 2);
    	a.add(mr);
    	mr.addBox(-8, -8, -10, 1, 3, 2);
    	a.add(mr);
    	mr.addBox(-7, -5, -10, 5, 1, 2);
    	a.add(mr);
    	mr.addBox(-3, -6, -10, 2, 1, 2);
    	a.add(mr);
    	mr.addBox(-2, -9, -10, 1, 3, 2);
    	a.add(mr);
    	
    	mr.addBox(-1, -9, -9, 2, 2, 1);
    	a.add(mr);
    	
    	mr.addBox(1, -9, -10, 1, 3, 2);
    	a.add(mr);
    	mr.addBox(1, -6, -10, 2, 1, 2);
    	a.add(mr);
    	mr.addBox(2, -5, -10, 5, 1, 2);
    	a.add(mr);
    	mr.addBox(7, -8, -10, 1, 3, 2);
    	a.add(mr);
    	mr.addBox(7, -9, -10, 2, 1, 2);
    	a.add(mr);
    	mr.addBox(2, -10, -10, 7, 1, 2);
    	a.add(mr);
    	
    	mr.addBox(8, -10, -8, 1, 2, 10);
    	a.add(mr);
    	mr.addBox(8, -8, 1, 1, 2, 1);
    	a.add(mr);
    	
    	return a;
    }
    
    public ArrayList<ModelRenderer> msShades(ModelRenderer mr) {
    	ArrayList<ModelRenderer> a = new ArrayList<ModelRenderer>();
    	
    	mr.setTextureSize(256, 256);

    	mr.addBox(-7, -9, -9, 5, 3, 1);
    	a.add(mr);
    	mr.addBox(-7, -6, -9, 4, 1, 1);
    	a.add(mr);
    	
    	mr.addBox(2, -9, -9, 5, 3, 1);
    	a.add(mr);
    	mr.addBox(3, -6, -9, 4, 1, 1);
    	a.add(mr);
    	
    	return a;
    }
    
    public ArrayList<ModelRenderer> msTophat(ModelRenderer mr) {
    	ArrayList<ModelRenderer> a = new ArrayList<ModelRenderer>();
    	mr.setTextureSize(256, 256);
    	
    	//base 
    	mr.addBox(-9, -14, -4, 1, 1, 8); a.add(mr);
    	mr.addBox(-8, -14, -6, 1, 1, 12); a.add(mr);
    	mr.addBox(-7, -14, -7, 2, 1, 14); a.add(mr);
    	mr.addBox(-5, -14, -8, 2, 1, 16); a.add(mr);
    	mr.addBox(-3, -14, -9, 6, 1, 18); a.add(mr);
    	
    	mr.addBox(3, -14, -8, 2, 1, 16); a.add(mr);
    	mr.addBox(5, -14, -7, 2, 1, 14); a.add(mr);
    	mr.addBox(7, -14, -6, 1, 1, 12); a.add(mr);
    	mr.addBox(8, -14, -4, 1, 1, 8); a.add(mr);
    	
    	
    	mr.addBox(-18, -15, -4, 1, 1, 8); a.add(mr);
    	mr.addBox(-17, -15, -6, 1, 1, 12); a.add(mr);
    	mr.addBox(-16, -15, -7, 1, 1, 14); a.add(mr);
    	mr.addBox(-15, -15, -8, 2, 1, 16); a.add(mr);
    	mr.addBox(-13, -15, -9, 2, 1, 18); a.add(mr);
    	mr.addBox(-11, -15, -10, 2, 1, 20); a.add(mr);
    	mr.addBox(-9, -15, -11, 4, 1, 22); a.add(mr);
    	mr.addBox(-5, -15, -12, 10, 1, 24); a.add(mr);
    	
    	mr.addBox(5, -15, -11, 4, 1, 22); a.add(mr);
    	mr.addBox(9, -15, -10, 2, 1, 20); a.add(mr);
    	mr.addBox(11, -15, -9, 2, 1, 18); a.add(mr);
    	mr.addBox(13, -15, -8, 2, 1, 16); a.add(mr);
    	mr.addBox(15, -15, -7, 1, 1, 14); a.add(mr);
    	mr.addBox(16, -15, -6, 1, 1, 12); a.add(mr);
    	mr.addBox(17, -15, -4, 1, 1, 8); a.add(mr);
    	
    	
    	//body
    	mr.addBox(-13, -21, -5, 1, 6, 10); a.add(mr);
    	mr.addBox(-12, -21, -7, 1, 6, 14); a.add(mr);
    	mr.addBox(-11, -21, -8, 1, 6, 16); a.add(mr);
    	mr.addBox(-10, -21, -9, 1, 6, 18); a.add(mr);
    	mr.addBox(-9, -21, -10, 1, 6, 20); a.add(mr);
    	mr.addBox(-8, -21, -11, 1, 6, 22); a.add(mr);
    	mr.addBox(-7, -21, -12, 3, 6, 24); a.add(mr);
    	mr.addBox(-4, -21, -13, 8, 6, 26); a.add(mr);
    	
    	mr.addBox(4, -21, -12, 3, 6, 24); a.add(mr);
    	mr.addBox(7, -21, -11, 1, 6, 22); a.add(mr);
    	mr.addBox(8, -21, -10, 1, 6, 20); a.add(mr);
    	mr.addBox(9, -21, -9, 1, 6, 18); a.add(mr);
    	mr.addBox(10, -21, -8, 1, 6, 16); a.add(mr);
    	mr.addBox(11, -21, -7, 1, 6, 14); a.add(mr);
    	mr.addBox(12, -21, -5, 1, 6, 10); a.add(mr);
    	
    	
    	mr.addBox(-12, -27, -5, 1, 6, 10); a.add(mr);
    	mr.addBox(-11, -27, -7, 1, 6, 14); a.add(mr);
    	mr.addBox(-10, -27, -8, 1, 6, 16); a.add(mr);
    	mr.addBox(-9, -27, -9, 1, 6, 18); a.add(mr);
    	mr.addBox(-8, -27, -10, 2, 6, 20); a.add(mr);
    	mr.addBox(-6, -27, -11, 2, 6, 22); a.add(mr);
    	mr.addBox(-4, -27, -12, 8, 6, 24); a.add(mr);
    	
    	mr.addBox(4, -27, -11, 2, 6, 22); a.add(mr);
    	mr.addBox(6, -27, -10, 2, 6, 20); a.add(mr);
    	mr.addBox(8, -27, -9, 1, 6, 18); a.add(mr);
    	mr.addBox(9, -27, -8, 1, 6, 16); a.add(mr);
    	mr.addBox(10, -27, -7, 1, 6, 14); a.add(mr);
    	mr.addBox(11, -27, -5, 1, 6, 10); a.add(mr);
    	
    	
    	//center++
    	mr.addBox(-11, -31, -5, 1, 4, 10); a.add(mr);
    	mr.addBox(-10, -31, -7, 1, 4, 14); a.add(mr);
    	mr.addBox(-9, -31, -8, 1, 4, 16); a.add(mr);
    	mr.addBox(-8, -31, -9, 1, 4, 18); a.add(mr);
    	mr.addBox(-7, -31, -10, 2, 4, 20); a.add(mr);
    	mr.addBox(-5, -31, -11, 2, 4, 22); a.add(mr);
    	mr.addBox(-3, -31, -12, 8, 4, 24); a.add(mr);
    	
    	mr.addBox(5, -31, -11, 2, 4, 22); a.add(mr);
    	mr.addBox(7, -31, -10, 2, 4, 20); a.add(mr);
    	mr.addBox(9, -31, -9, 1, 4, 18); a.add(mr);
    	mr.addBox(10, -31, -8, 1, 4, 16); a.add(mr);
    	mr.addBox(11, -31, -7, 1, 4, 14); a.add(mr);
    	mr.addBox(12, -31, -5, 1, 4, 10); a.add(mr);
    	
    	
    	//center++
    	mr.addBox(-11, -35, -5, 1, 4, 10); a.add(mr);
    	mr.addBox(-10, -35, -7, 1, 4, 14); a.add(mr);
    	mr.addBox(-9, -35, -8, 1, 4, 16); a.add(mr);
    	mr.addBox(-8, -35, -9, 1, 4, 18); a.add(mr);
    	mr.addBox(-7, -35, -10, 1, 4, 20); a.add(mr);
    	mr.addBox(-6, -35, -11, 1, 4, 22); a.add(mr);
    	mr.addBox(-5, -35, -12, 3, 4, 24); a.add(mr);
    	mr.addBox(-2, -35, -13, 8, 4, 26); a.add(mr);
    	
    	mr.addBox(6, -35, -12, 3, 4, 24); a.add(mr);
    	mr.addBox(9, -35, -11, 1, 4, 22); a.add(mr);
    	mr.addBox(10, -35, -10, 1, 4, 20); a.add(mr);
    	mr.addBox(11, -35, -9, 1, 4, 18); a.add(mr);
    	mr.addBox(12, -35, -8, 1, 4, 16); a.add(mr);
    	mr.addBox(13, -35, -7, 1, 4, 14); a.add(mr);
    	mr.addBox(14, -35, -5, 1, 4, 10); a.add(mr);
    	
    	
    	mr.addBox(-12, -38, -5, 1, 3, 10); a.add(mr);
    	mr.addBox(-11, -38, -7, 1, 3, 14); a.add(mr);
    	mr.addBox(-10, -38, -9, 1, 3, 18); a.add(mr);
    	mr.addBox(-9, -38, -10, 1, 3, 20); a.add(mr);
    	mr.addBox(-8, -38, -11, 2, 3, 22); a.add(mr);
    	mr.addBox(-6, -38, -12, 1, 3, 24); a.add(mr);
    	mr.addBox(-5, -38, -13, 3, 3, 26); a.add(mr);
    	mr.addBox(-2, -38, -14, 8, 3, 28); a.add(mr);
    	
    	mr.addBox(6, -38, -13, 3, 3, 26); a.add(mr);
    	mr.addBox(9, -38, -12, 1, 3, 24); a.add(mr);
    	mr.addBox(10, -38, -11, 2, 3, 22); a.add(mr);
    	mr.addBox(12, -38, -10, 1, 3, 20); a.add(mr);
    	mr.addBox(13, -38, -9, 1, 3, 18); a.add(mr);
    	mr.addBox(14, -38, -7, 1, 3, 14); a.add(mr);
    	mr.addBox(15, -38, -5, 1, 3, 10); a.add(mr);
    	
    	
    	// center++
    	mr.addBox(-12, -40, -5, 1, 2, 10); a.add(mr);
    	mr.addBox(-11, -40, -7, 1, 2, 14); a.add(mr);
    	mr.addBox(-10, -40, -9, 1, 2, 18); a.add(mr);
    	mr.addBox(-9, -40, -10, 1, 2, 20); a.add(mr);
    	mr.addBox(-8, -40, -11, 1, 2, 22); a.add(mr);
    	mr.addBox(-7, -40, -12, 1, 2, 24); a.add(mr);
    	mr.addBox(-6, -40, -13, 2, 2, 26); a.add(mr);
    	mr.addBox(-4, -40, -14, 3, 2, 28); a.add(mr);
    	mr.addBox(-1, -40, -15, 8, 2, 30); a.add(mr);
    	
    	mr.addBox(7, -40, -14, 3, 2, 28); a.add(mr);
    	mr.addBox(10, -40, -13, 2, 2, 26); a.add(mr);
    	mr.addBox(12, -40, -12, 1, 2, 24); a.add(mr);
    	mr.addBox(13, -40, -11, 1, 2, 22); a.add(mr);
    	mr.addBox(14, -40, -10, 1, 2, 20); a.add(mr);
    	mr.addBox(15, -40, -9, 1, 2, 18); a.add(mr);
    	mr.addBox(16, -40, -7, 1, 2, 14); a.add(mr);
    	mr.addBox(17, -40, -5, 1, 2, 10); a.add(mr);
    	
    	
    	mr.addBox(-13, -42, -6, 1, 2, 12); a.add(mr);
    	mr.addBox(-12, -42, -8, 1, 2, 16); a.add(mr);
    	mr.addBox(-11, -42, -9, 1, 2, 18); a.add(mr);
    	mr.addBox(-10, -42, -11, 1, 2, 22); a.add(mr);
    	mr.addBox(-9, -42, -12, 2, 2, 24); a.add(mr);
    	mr.addBox(-7, -42, -13, 1, 2, 26); a.add(mr);
    	mr.addBox(-6, -42, -14, 2, 2, 28); a.add(mr);
    	mr.addBox(-4, -42, -15, 3, 2, 30); a.add(mr);
    	// mr.addBox(-1, -42, -16, 8, 2, 32); a.add(mr);
    	
    	mr.addBox(7, -42, -15, 3, 2, 30); a.add(mr);
    	mr.addBox(10, -42, -14, 2, 2, 28); a.add(mr);
    	mr.addBox(12, -42, -13, 1, 2, 26); a.add(mr);
    	mr.addBox(13, -42, -12, 2, 2, 24); a.add(mr);
    	mr.addBox(15, -42, -11, 1, 2, 22); a.add(mr);
    	mr.addBox(16, -42, -9, 1, 2, 18); a.add(mr);
    	mr.addBox(17, -42, -8, 1, 2, 16); a.add(mr);
    	mr.addBox(18, -42, -6, 1, 2, 12); a.add(mr);
    	
    	
    	
    	/* (0.0150625F)
    	mr.addBox(-25, -40, -7, 1, 6, 14); a.add(mr);
    	mr.addBox(-24, -40, -10, 1, 6, 20); a.add(mr);
    	mr.addBox(-23, -40, -12, 1, 6, 24); a.add(mr);
    	mr.addBox(-22, -40, -14, 1, 6, 28); a.add(mr);
    	mr.addBox(-21, -40, -15, 1, 6, 30); a.add(mr);
    	mr.addBox(-20, -40, -16, 1, 6, 32); a.add(mr);
    	mr.addBox(-19, -40, -17, 1, 6, 34); a.add(mr);
    	mr.addBox(-18, -40, -18, 1, 6, 36); a.add(mr);
    	mr.addBox(-17, -40, -19, 1, 6, 38); a.add(mr);
    	mr.addBox(-16, -40, -20, 1, 6, 40); a.add(mr);
    	mr.addBox(-15, -40, -21, 2, 6, 42); a.add(mr);
    	mr.addBox(-13, -40, -22, 2, 6, 44); a.add(mr);
    	mr.addBox(-11, -40, -23, 2, 6, 46); a.add(mr);
    	mr.addBox(-9, -40, -24, 4, 6, 48); a.add(mr);
    	mr.addBox(-5, -40, -25, 10, 6, 50); a.add(mr);
    	
    	mr.addBox(5, -40, -24, 4, 6, 48); a.add(mr);
    	mr.addBox(9, -40, -23, 2, 6, 46); a.add(mr);
    	mr.addBox(11, -40, -22, 2, 6, 44); a.add(mr);
    	mr.addBox(13, -40, -21, 2, 6, 42); a.add(mr);
    	mr.addBox(15, -40, -20, 1, 6, 40); a.add(mr);
    	mr.addBox(16, -40, -19, 1, 6, 38); a.add(mr);
    	mr.addBox(17, -40, -18, 1, 6, 36); a.add(mr);
    	mr.addBox(18, -40, -17, 1, 6, 34); a.add(mr);
    	mr.addBox(19, -40, -16, 1, 6, 32); a.add(mr);
    	mr.addBox(20, -40, -15, 1, 6, 30); a.add(mr);
    	mr.addBox(21, -40, -14, 1, 6, 28); a.add(mr);
    	mr.addBox(22, -40, -12, 1, 6, 24); a.add(mr);
    	mr.addBox(23, -40, -10, 1, 6, 20); a.add(mr);
    	mr.addBox(24, -40, -7, 1, 6, 14); a.add(mr);
    	
    	
    	mr.addBox(-24, -60, -7, 1, 20, 14); a.add(mr);
    	mr.addBox(-23, -60, -10, 1, 20, 20); a.add(mr);
    	mr.addBox(-22, -60, -12, 1, 20, 24); a.add(mr);
    	mr.addBox(-21, -60, -13, 1, 20, 26); a.add(mr);
    	mr.addBox(-20, -60, -15, 1, 20, 30); a.add(mr);
    	mr.addBox(-19, -60, -16, 1, 20, 32); a.add(mr);
    	mr.addBox(-18, -60, -17, 1, 20, 34); a.add(mr);
    	mr.addBox(-17, -60, -18, 1, 20, 36); a.add(mr);
    	mr.addBox(-16, -60, -19, 2, 20, 38); a.add(mr);
    	mr.addBox(-14, -60, -20, 1, 20, 40); a.add(mr);
    	mr.addBox(-13, -60, -21, 2, 20, 42); a.add(mr);
    	mr.addBox(-11, -60, -22, 2, 20, 44); a.add(mr);
    	mr.addBox(-9, -60, -23, 4, 20, 46); a.add(mr);
    	mr.addBox(-5, -60, -24, 10, 20, 48); a.add(mr);
    	
    	mr.addBox(5, -60, -23, 4, 20, 46); a.add(mr);
    	mr.addBox(9, -60, -22, 2, 20, 44); a.add(mr);
    	mr.addBox(11, -60, -21, 2, 20, 42); a.add(mr);
    	mr.addBox(13, -60, -20, 1, 20, 40); a.add(mr);
    	mr.addBox(14, -60, -19, 2, 20, 38); a.add(mr);
    	mr.addBox(16, -60, -18, 1, 20, 36); a.add(mr);
    	mr.addBox(17, -60, -17, 1, 20, 34); a.add(mr);
    	mr.addBox(18, -60, -16, 1, 20, 32); a.add(mr);
    	mr.addBox(19, -60, -15, 1, 20, 30); a.add(mr);
    	mr.addBox(20, -60, -13, 1, 20, 26); a.add(mr);
    	mr.addBox(21, -60, -12, 1, 20, 24); a.add(mr);
    	mr.addBox(22, -60, -10, 1, 20, 20); a.add(mr);
    	mr.addBox(23, -60, -7, 1, 20, 14); a.add(mr);
    	
    	// center++
    	mr.addBox(-24, -66, -7, 1, 6, 14); a.add(mr);
    	mr.addBox(-23, -66, -10, 1, 6, 20); a.add(mr);
    	mr.addBox(-22, -66, -12, 1, 6, 24); a.add(mr);
    	mr.addBox(-21, -66, -14, 1, 6, 28); a.add(mr);
    	mr.addBox(-20, -66, -15, 1, 6, 30); a.add(mr);
    	mr.addBox(-19, -66, -16, 1, 6, 32); a.add(mr);
    	mr.addBox(-18, -66, -17, 1, 6, 34); a.add(mr);
    	mr.addBox(-17, -66, -18, 1, 6, 36); a.add(mr);
    	mr.addBox(-16, -66, -19, 1, 6, 38); a.add(mr);
    	mr.addBox(-15, -66, -20, 1, 6, 40); a.add(mr);
    	mr.addBox(-14, -66, -21, 2, 6, 42); a.add(mr);
    	mr.addBox(-12, -66, -22, 2, 6, 44); a.add(mr);
    	mr.addBox(-10, -66, -23, 2, 6, 46); a.add(mr);
    	mr.addBox(-8, -66, -24, 4, 6, 48); a.add(mr);
    	mr.addBox(-4, -66, -25, 10, 6, 50); a.add(mr);
    	
    	mr.addBox(6, -66, -24, 4, 6, 48); a.add(mr);
    	mr.addBox(10, -66, -23, 2, 6, 46); a.add(mr);
    	mr.addBox(12, -66, -22, 2, 6, 44); a.add(mr);
    	mr.addBox(14, -66, -21, 2, 6, 42); a.add(mr);
    	mr.addBox(16, -66, -20, 1, 6, 40); a.add(mr);
    	mr.addBox(17, -66, -19, 1, 6, 38); a.add(mr);
    	mr.addBox(18, -66, -18, 1, 6, 36); a.add(mr);
    	mr.addBox(19, -66, -17, 1, 6, 34); a.add(mr);
    	mr.addBox(20, -66, -16, 1, 6, 32); a.add(mr);
    	mr.addBox(21, -66, -15, 1, 6, 30); a.add(mr);
    	mr.addBox(22, -66, -14, 1, 6, 28); a.add(mr);
    	mr.addBox(23, -66, -12, 1, 6, 24); a.add(mr);
    	mr.addBox(24, -66, -10, 1, 6, 20); a.add(mr);
    	mr.addBox(25, -66, -7, 1, 6, 14); a.add(mr);
    	
    	// center++
    	mr.addBox(-24, -70, -7, 1, 4, 14); a.add(mr);
    	mr.addBox(-23, -70, -10, 1, 4, 20); a.add(mr);
    	mr.addBox(-22, -70, -12, 1, 4, 24); a.add(mr);
    	mr.addBox(-21, -70, -14, 1, 4, 28); a.add(mr);
    	mr.addBox(-20, -70, -15, 1, 4, 30); a.add(mr);
    	mr.addBox(-19, -70, -17, 1, 4, 34); a.add(mr);
    	mr.addBox(-18, -70, -18, 1, 4, 36); a.add(mr);
    	mr.addBox(-17, -70, -19, 1, 4, 38); a.add(mr);
    	mr.addBox(-16, -70, -20, 2, 4, 40); a.add(mr);
    	mr.addBox(-14, -70, -21, 1, 4, 42); a.add(mr);
    	mr.addBox(-13, -70, -22, 1, 4, 44); a.add(mr);
    	mr.addBox(-12, -70, -23, 2, 4, 46); a.add(mr);
    	mr.addBox(-10, -70, -24, 3, 4, 48); a.add(mr);
    	mr.addBox(-7, -70, -25, 3, 4, 50); a.add(mr);
    	mr.addBox(-4, -70, -26, 12, 4, 52); a.add(mr);
    	
    	mr.addBox(8, -70, -25, 3, 4, 50); a.add(mr);
    	mr.addBox(11, -70, -24, 3, 4, 48); a.add(mr);
    	mr.addBox(14, -70, -23, 2, 4, 46); a.add(mr);
    	mr.addBox(16, -70, -22, 1, 4, 44); a.add(mr);
    	mr.addBox(17, -70, -21, 1, 4, 42); a.add(mr);
    	mr.addBox(18, -70, -20, 2, 4, 40); a.add(mr);
    	mr.addBox(20, -70, -19, 1, 4, 38); a.add(mr);
    	mr.addBox(21, -70, -18, 1, 4, 36); a.add(mr);
    	mr.addBox(22, -70, -17, 1, 4, 34); a.add(mr);
    	mr.addBox(23, -70, -15, 1, 4, 30); a.add(mr);
    	mr.addBox(24, -70, -14, 1, 4, 28); a.add(mr);
    	mr.addBox(25, -70, -12, 1, 4, 24); a.add(mr);
    	mr.addBox(26, -70, -10, 1, 4, 20); a.add(mr);
    	mr.addBox(27, -70, -7, 1, 4, 14); a.add(mr);
    	
    	
    	mr.addBox(-25, -74, -7, 1, 4, 14); a.add(mr);
    	mr.addBox(-24, -74, -10, 1, 4, 20); a.add(mr);
    	mr.addBox(-23, -74, -12, 1, 4, 24); a.add(mr);
    	mr.addBox(-22, -74, -14, 1, 4, 28); a.add(mr);
    	mr.addBox(-21, -74, -16, 1, 4, 32); a.add(mr);
    	mr.addBox(-20, -74, -17, 1, 4, 34); a.add(mr);
    	mr.addBox(-19, -74, -18, 1, 4, 36); a.add(mr);
    	mr.addBox(-18, -74, -19, 1, 4, 38); a.add(mr);
    	mr.addBox(-17, -74, -20, 1, 4, 40); a.add(mr);
    	mr.addBox(-16, -74, -21, 1, 4, 42); a.add(mr);
    	mr.addBox(-15, -74, -22, 2, 4, 44); a.add(mr);
    	mr.addBox(-13, -74, -23, 1, 4, 46); a.add(mr);
    	mr.addBox(-12, -74, -24, 2, 4, 48); a.add(mr);
    	mr.addBox(-10, -74, -25, 3, 4, 50); a.add(mr);
    	mr.addBox(-7, -74, -26, 3, 4, 52); a.add(mr);
    	mr.addBox(-4, -74, -27, 12, 4, 54); a.add(mr);
    	
    	mr.addBox(8, -74, -26, 3, 4, 52); a.add(mr);
    	mr.addBox(11, -74, -25, 3, 4, 50); a.add(mr);
    	mr.addBox(14, -74, -24, 2, 4, 48); a.add(mr);
    	mr.addBox(16, -74, -23, 1, 4, 46); a.add(mr);
    	mr.addBox(17, -74, -22, 2, 4, 44); a.add(mr);
    	mr.addBox(19, -74, -21, 1, 4, 42); a.add(mr);
    	mr.addBox(20, -74, -20, 1, 4, 40); a.add(mr);
    	mr.addBox(21, -74, -19, 1, 4, 38); a.add(mr);
    	mr.addBox(22, -74, -18, 1, 4, 36); a.add(mr);
    	mr.addBox(23, -74, -17, 1, 4, 34); a.add(mr);
    	mr.addBox(24, -74, -16, 1, 4, 32); a.add(mr);
    	mr.addBox(25, -74, -14, 1, 4, 28); a.add(mr);
    	mr.addBox(26, -74, -12, 1, 4, 24); a.add(mr);
    	mr.addBox(27, -74, -10, 1, 4, 20); a.add(mr);
    	mr.addBox(28, -74, -7, 1, 4, 14); a.add(mr);
    	*/ 
    	
    	
        // base (0.0625F)
    	/* 
        mr.addBox(-16, -14, -6, 1, 2, 12);
    	a.add(mr);
    	mr.addBox(-15, -14, -8, 1, 2, 16);
    	a.add(mr);
    	mr.addBox(-14, -14, -9, 1, 2, 18);
    	a.add(mr);
    	mr.addBox(-13, -14, -11, 1, 2, 22);
    	a.add(mr);
    	mr.addBox(-12, -14, -12, 2, 2, 24);
    	a.add(mr);
    	mr.addBox(-10, -14, -13, 1, 2, 26);
    	a.add(mr);
    	mr.addBox(-9, -14, -14, 2, 2, 28);
    	a.add(mr);
    	mr.addBox(-7, -14, -15, 3, 2, 30);
    	a.add(mr);
    	mr.addBox(-4, -14, -16, 8, 2, 32);
    	a.add(mr);
    	mr.addBox(4, -14, -15, 3, 2, 30);
    	a.add(mr);
    	mr.addBox(7, -14, -14, 2, 2, 28);
    	a.add(mr);
    	mr.addBox(9, -14, -13, 1, 2, 26);
    	a.add(mr);
    	mr.addBox(10, -14, -12, 2, 2, 24);
    	a.add(mr);
    	mr.addBox(12, -14, -11, 1, 2, 22);
    	a.add(mr);
    	mr.addBox(13, -14, -9, 1, 2, 18);
    	a.add(mr);
    	mr.addBox(14, -14, -8, 1, 2, 16);
    	a.add(mr);
    	mr.addBox(15, -14, -6, 1, 2, 12);
    	a.add(mr);
    	*/
    	
    	return a;
    }
    
    public ArrayList<ModelRenderer> msTophatEdge(ModelRenderer mr) {
    	ArrayList<ModelRenderer> a = new ArrayList<ModelRenderer>();
    	mr.setTextureSize(256, 256);
    	
    	
    	return a;
    }
}
