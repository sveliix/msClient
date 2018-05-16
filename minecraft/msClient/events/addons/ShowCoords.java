package msClient.events.addons;

import org.lwjgl.opengl.GL11;

import msClient.config.Variables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;

public class ShowCoords {

	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static void run (GuiIngame g) {
		GL11.glPushMatrix();
		GL11.glScalef(Variables.x, Variables.x, Variables.x);
		
		int coordX = (int)Minecraft.getMinecraft().thePlayer.posX;
		int coordY = (int)Minecraft.getMinecraft().thePlayer.posY;
		int coordZ = (int)Minecraft.getMinecraft().thePlayer.posZ;
		
		int temp = 0;
		
		mc.fontRendererObj.drawString("X: " + coordX, Math.round(Variables.objects.get(1).getCoordX()/Variables.x), Math.round(Variables.objects.get(1).getCoordY()/Variables.x) + temp, Variables.MSColor);
		temp += 10;
		mc.fontRendererObj.drawString("Y: " + coordY, Math.round(Variables.objects.get(1).getCoordX()/Variables.x), Math.round(Variables.objects.get(1).getCoordY()/Variables.x) + temp , Variables.MSColor);
		temp += 10;
		mc.fontRendererObj.drawString("Z: " + coordZ, Math.round(Variables.objects.get(1).getCoordX()/Variables.x), Math.round(Variables.objects.get(1).getCoordY()/Variables.x) + temp , Variables.MSColor);
		temp += 10;
		
		GL11.glPopMatrix();
	}
	
}
