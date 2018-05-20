package msClient.events.addons;

import java.text.DecimalFormat;

import msClient.config.Vbs;
import msClient.test.Animation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.multiplayer.PlayerControllerMP;

public class DisplayReach {
	
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static void run (GuiIngame g) {
		if(mc.currentScreen == null) {
			String t = "";
			if(Vbs.funnyReach) {
				t = (new DecimalFormat(".##").format(PlayerControllerMP.hit * 10) + " blocks");
			}else {
				t = (new DecimalFormat(".##").format(PlayerControllerMP.hit) + " blocks");
			}
			if (System.currentTimeMillis() - PlayerControllerMP.lastAttack > 2000L) {
		        t = "Hasn't attacked";
		    }		
			Animation.instance.animation(500, 1);
			int s = mc.fontRendererObj.getStringWidth(t);
			if(!Vbs.fastReach) {
				g.drawRect(Vbs.objects.get(8).getCoordX()-2, 
						Vbs.objects.get(8).getCoordY()-2, 
						Vbs.objects.get(8).getCoordX() + s + 2, 
						Vbs.objects.get(8).getCoordY() + 10, 
						1342177280);
				
			}
			mc.fontRendererObj.drawString(t, 
					Vbs.objects.get(8).getCoordX(), 
					Vbs.objects.get(8).getCoordY(), 
					Vbs.MSColor);			
			
		}
	}

}
