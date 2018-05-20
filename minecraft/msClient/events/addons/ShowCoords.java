package msClient.events.addons;

import org.lwjgl.opengl.GL11;

import msClient.config.Vbs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;

public class ShowCoords {

	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static void run (GuiIngame g) {
		GL11.glPushMatrix();
		GL11.glScalef(Vbs.x, Vbs.x, Vbs.x);
		
		int coordX = (int)Minecraft.getMinecraft().thePlayer.posX;
		int coordY = (int)Minecraft.getMinecraft().thePlayer.posY;
		int coordZ = (int)Minecraft.getMinecraft().thePlayer.posZ;
		
		int temp = 0;
		
		mc.fontRendererObj.drawString("X: " + coordX, Math.round(Vbs.objects.get(1).getCoordX()/Vbs.x), Math.round(Vbs.objects.get(1).getCoordY()/Vbs.x) + temp, Vbs.MSColor);
		temp += 10;
		mc.fontRendererObj.drawString("Y: " + coordY, Math.round(Vbs.objects.get(1).getCoordX()/Vbs.x), Math.round(Vbs.objects.get(1).getCoordY()/Vbs.x) + temp , Vbs.MSColor);
		temp += 10;
		mc.fontRendererObj.drawString("Z: " + coordZ, Math.round(Vbs.objects.get(1).getCoordX()/Vbs.x), Math.round(Vbs.objects.get(1).getCoordY()/Vbs.x) + temp , Vbs.MSColor);
		temp += 10;
		
		GL11.glPopMatrix();
	}
	
}
