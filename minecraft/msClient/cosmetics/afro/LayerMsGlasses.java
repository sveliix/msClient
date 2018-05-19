package msClient.cosmetics.afro;

import org.lwjgl.opengl.GL11;

import msClient.main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class LayerMsGlasses implements LayerRenderer {
	private static final ResourceLocation rl = new ResourceLocation("textures/sunglasses.png");
	private final RenderPlayer playerRenderer;
	
	public LayerMsGlasses(RenderPlayer playerRendererIn) {
		this.playerRenderer = playerRendererIn;
	}
	
	public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float p_177141_2_, float p_177141_3_,
			float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
		float f = main.partialTicks;
		
		if (Minecraft.getMinecraft().currentScreen != null && 
				entitylivingbaseIn == Minecraft.getMinecraft().thePlayer) {
			f = 1.0F;
		}
		
		float f1 = getFirstRotationX(entitylivingbaseIn, f);
		float f2 = getSecondRotationX(entitylivingbaseIn, f);
		
		GlStateManager.pushMatrix();
		if (entitylivingbaseIn.isSneaking()) {
			GlStateManager.translate(0.0D, 0.3D, 0.0D);
		}
		GlStateManager.rotate(f1, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(f2, 1.0F, 0.0F, 0.0F);
		
		this.playerRenderer.bindTexture(rl);
		this.playerRenderer.getMainModel().renderMsSunglasses(0.03125F);
		
		GlStateManager.popMatrix();
	}

	@Override
	public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float p_177141_2_, float p_177141_3_,
			float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
		this.doRenderLayer((AbstractClientPlayer)entitylivingbaseIn, 
				p_177141_2_, p_177141_3_, partialTicks, p_177141_5_, p_177141_6_, p_177141_7_, scale);
	}
	

	@Override
	public boolean shouldCombineTextures() {
		return false;
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
	
	
}
