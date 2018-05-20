package msClient.events.addons;

import java.util.List;

import org.lwjgl.opengl.GL11;


import msClient.config.Vbs;
import net.minecraft.client.gui.GuiIngame;

public class Memory {
	
	public static void run (GuiIngame g) {
		
		GL11.glPushMatrix();
	    GL11.glScalef(Vbs.x, Vbs.x, Vbs.x);
		
		long i = Runtime.getRuntime().maxMemory();
		long j = Runtime.getRuntime().totalMemory();
	    long k = Runtime.getRuntime().freeMemory();
	    long l = j - k;
	    long percent = Long.valueOf(l * 100L / i).longValue();
	
	    
	    g.getFontRenderer().drawString("Mem: " + percent + "%", Math.round(Vbs.objects.get(3).getCoordX()/Vbs.x), Math.round(Vbs.objects.get(3).getCoordY()/Vbs.x), Vbs.MSColor);
	    GL11.glPopMatrix(); 
	   
	    
	}

}
