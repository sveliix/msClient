package msClient.cosmetics;

import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;

public class LayerMS implements LayerRenderer{
	private final RenderPlayer playerRenderer;
	
	public LayerMS(RenderPlayer playerRendererIn) {
		this.playerRenderer = playerRendererIn;
	}

	@Override
	public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float p_177141_2_, float p_177141_3_,
			float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
		this.playerRenderer.getMainModel().renderMS(0.0625F);
		
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
	
	
}
