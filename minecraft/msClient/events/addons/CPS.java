package msClient.events.addons;

import org.lwjgl.opengl.GL11;

import msClient.config.Vbs;
import msClient.utils.ClickCounter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;

public class CPS {

	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static void run (GuiIngame g) {
		
		GL11.glPushMatrix();
		GL11.glScalef(Vbs.x, Vbs.x, Vbs.x);
		
		mc.fontRendererObj.drawString("CPS: " + ClickCounter.getClickResult(), 
				Math.round(Vbs.objects.get(2).getCoordX()/Vbs.x), 
				Math.round(Vbs.objects.get(2).getCoordY()/Vbs.x), 
				Vbs.MSColor);
		
		GL11.glPopMatrix();
	}
	
}
