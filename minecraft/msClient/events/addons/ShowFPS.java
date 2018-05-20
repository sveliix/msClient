package msClient.events.addons;

import org.lwjgl.opengl.GL11;

import msClient.config.Vbs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;

public class ShowFPS {
	
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static void run (GuiIngame g) {
		
		GL11.glPushMatrix();
		GL11.glScalef(Vbs.x, Vbs.x, Vbs.x);
		
		int fps = Minecraft.getDebugFPS();
		
		mc.fontRendererObj.drawString("FPS: " + fps, 
				Math.round(Vbs.objects.get(0).getCoordX()/Vbs.x), 
				Math.round(Vbs.objects.get(0).getCoordY()/Vbs.x), 
				Vbs.MSColor);
		
		GL11.glPopMatrix();
		
	}

}
