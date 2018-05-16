package msClient.events.addons;

import msClient.config.Variables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.Item;

public class OldBow {
	
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static void run (ItemRenderer i) {
		if(mc != null && mc.thePlayer != null && 
				mc.thePlayer.getItemInUse() != null && 
				mc.thePlayer.getItemInUse().getItem() != null && 
				Item.getIdFromItem(mc.thePlayer.getItemInUse().getItem()) == 261) {
    		GlStateManager.translate(0.0F, 0.0F, -0.08F);
    	}
	}

}
