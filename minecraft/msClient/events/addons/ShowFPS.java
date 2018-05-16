package msClient.events.addons;

import org.lwjgl.opengl.GL11;

import msClient.config.Variables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;

public class ShowFPS {
	
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static void run (GuiIngame g) {
		
		GL11.glPushMatrix();
		GL11.glScalef(Variables.x, Variables.x, Variables.x);
		
		int fps = Minecraft.getDebugFPS();
		
		mc.fontRendererObj.drawString("FPS: " + fps, 
				Math.round(Variables.objects.get(0).getCoordX()/Variables.x), 
				Math.round(Variables.objects.get(0).getCoordY()/Variables.x), 
				Variables.MSColor);
		
		GL11.glPopMatrix();
		
	}

}
