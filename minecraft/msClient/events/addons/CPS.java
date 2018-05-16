package msClient.events.addons;

import org.lwjgl.opengl.GL11;

import msClient.config.Variables;
import msClient.utils.ClickCounter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;

public class CPS {

	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static void run (GuiIngame g) {
		
		GL11.glPushMatrix();
		GL11.glScalef(Variables.x, Variables.x, Variables.x);
		
		mc.fontRendererObj.drawString("CPS: " + ClickCounter.getClickResult(), 
				Math.round(Variables.objects.get(2).getCoordX()/Variables.x), 
				Math.round(Variables.objects.get(2).getCoordY()/Variables.x), 
				Variables.MSColor);
		
		GL11.glPopMatrix();
	}
	
}
