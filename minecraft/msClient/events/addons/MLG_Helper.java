package msClient.events.addons;

import org.lwjgl.opengl.GL11;

import msClient.config.Vbs;
import msClient.mlgHelper.Rechner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.util.MovingObjectPosition;

public class MLG_Helper{
	
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static void run(GuiIngame g) {
		
		GL11.glPushMatrix();
		GL11.glScalef(Vbs.x, Vbs.x, Vbs.x);
		
		MovingObjectPosition mop = mc.getRenderViewEntity().rayTrace(200, 1.0F);//Das ist ab hier für den MLGHelper

    	if(mop != null) {

    		double lookBY = mop.getBlockPos().getY();
    		
    		String mlg = Rechner.calcMLG(mc.thePlayer.getPosition().getY(), (int)lookBY+1);
    		
    		if(mlg != null) {
    			
    			mc.fontRendererObj.drawString("[" + ((int)lookBY+1) + "]" + mlg, 
    					Math.round(Vbs.objects.get(4).getCoordX()/Vbs.x), 
    					Math.round(Vbs.objects.get(4).getCoordY()/Vbs.x), 
    					Vbs.MSColor);
    			
    		}
    	}
    	GL11.glPopMatrix();
	}

}
