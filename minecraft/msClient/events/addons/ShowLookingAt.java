package msClient.events.addons;

import org.lwjgl.opengl.GL11;

import msClient.config.Variables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;

public class ShowLookingAt {

	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static void run (GuiIngame g) {
		GL11.glPushMatrix();
		GL11.glScalef(Variables.x, Variables.x, Variables.x);
		
		BlockPos blockpos1 = mc.objectMouseOver.getBlockPos();
		
		if(mc.objectMouseOver != null && 
				mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && 
				blockpos1 != null) { // noch speichern
			
			mc.fontRendererObj.drawString(String.format("Looking at: %d %d %d", 
					new Object[] {Integer.valueOf(blockpos1.getX()), 
							Integer.valueOf(blockpos1.getY()), 
							Integer.valueOf(blockpos1.getZ())}), 
					Math.round(Variables.objects.get(6).getCoordX()/Variables.x), 
					Math.round(Variables.objects.get(6).getCoordY()/Variables.x), 
					Variables.MSColor);
		}
		
		GL11.glPopMatrix();
	}
	
}
