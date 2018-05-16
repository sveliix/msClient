package msClient.events.addons;

import org.lwjgl.opengl.GL11;

import msClient.config.Variables;
import msClient.mlgHelper.Rechner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.util.MovingObjectPosition;

public class MLG_Helper{
	
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static void run(GuiIngame g) {
		
		GL11.glPushMatrix();
		GL11.glScalef(Variables.x, Variables.x, Variables.x);
		
		MovingObjectPosition mop = mc.getRenderViewEntity().rayTrace(200, 1.0F);//Das ist ab hier für den MLGHelper

    	if(mop != null) {

    		double lookBY = mop.getBlockPos().getY();
    		
    		String mlg = Rechner.calcMLG(mc.thePlayer.getPosition().getY(), (int)lookBY+1);
    		
    		if(mlg != null) {
    			
    			mc.fontRendererObj.drawString(mlg , Math.round(Variables.objects.get(4).getCoordX()/Variables.x), Math.round(Variables.objects.get(4).getCoordY()/Variables.x), Variables.MSColor);
    			
    		}
    	}
    	GL11.glPopMatrix();
	}

}
