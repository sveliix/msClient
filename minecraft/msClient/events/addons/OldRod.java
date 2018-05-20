package msClient.events.addons;

import msClient.config.Vbs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.Item;

public class OldRod {
	
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static void run (ItemRenderer i) {
    	if(mc != null && mc.thePlayer != null && 
    			mc.thePlayer.getCurrentEquippedItem() != null && 
    			mc.thePlayer.getCurrentEquippedItem().getItem() != null && 
    			Item.getIdFromItem(mc.thePlayer.getCurrentEquippedItem().getItem()) == 346) {
    		GlStateManager.translate(0.1F, -0.02F, -0.335F);
    	}
	}

}
