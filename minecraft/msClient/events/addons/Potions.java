package msClient.events.addons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;

public class Potions {
	
	public static void run (GuiIngame g) {
		g.updateActivePotionEffects();
		
		if (g.hasActivePotionEffects)
    	{
        	g.drawActivePotionEffects();
    	}
	}

}
