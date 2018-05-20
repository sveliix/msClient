package msClient.cosmetics.discoball;

import msClient.main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class LayerMsDiscoball implements LayerRenderer {

	private static final ResourceLocation rl = new ResourceLocation("textures/disco.png"); // TODO redo this texture pls
	private final RenderPlayer playerRenderer;
	
	public LayerMsDiscoball(RenderPlayer playerRendererIn) {
		this.playerRenderer = playerRendererIn;
	}
	
	public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float p_177141_2_, float p_177141_3_,
			float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
		float f = main.partialTicks; // range: 0F - 1F
		
		float fac = 0.2F; // dont hardcode that value like this
		
		GlStateManager.rotate((float)(System.currentTimeMillis()%((int)(360/fac)))*fac, 0F, 1F, 0F);
		
		this.playerRenderer.bindTexture(rl);
		this.playerRenderer.getMainModel().renderMsDiscoball(0.03125F);
		
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
	
}
