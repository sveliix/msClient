package msClient.events.addons;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

import msClient.config.Vbs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;

public class EP {
	
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static void run (GuiIngame g) {
		
		GL11.glPushMatrix();
		GL11.glScalef(Vbs.x, Vbs.x, Vbs.x);
		
		String temp = "";
		
		List<String> list = Lists.newArrayList(new String[] {  });
		
		for (Iterator<Entity> entities = mc.theWorld.loadedEntityList.iterator(); entities.hasNext();) {
		    Object theObject = entities.next();
		    if ((theObject instanceof EntityLivingBase)) {
		       EntityLivingBase entity = (EntityLivingBase)theObject;
		       
		       if (!(entity instanceof EntityPlayerSP)) {
		    	   if(entity.getHeldItem() != null) {
	    		    		  
		    		   if(entity.getHeldItem().getItem() == Items.ender_pearl) {
		    			  
		    			   list.add(entity.getName() + "hat eine EP!!!");
		    			   
		    			   Item z = entity.getHeldItem().getItem();
  		    			   
		    			   if(mc.thePlayer.interactWith(entity)) {
		    				   mc.fontRendererObj.drawString("True", 100, 100, Color.WHITE.getRGB());
		    				   System.out.println("True");
		    			   }
		    			   
		    			   for (int i = 0; i < list.size(); ++i)
		    		        {
		    				   String s = list.get(i);
		    				   
			    			   mc.fontRendererObj.drawString(s, 
			    					   Math.round(Vbs.objects.get(5).getCoordX()/Vbs.x), 
			    					   Math.round((Vbs.objects.get(5).getCoordY()+(8*i))/Vbs.x), 
			    					   Vbs.MSColor);
		    		        }
		    		   	}  
		    	   	}  
		       	}
		    }
		}
		GL11.glPopMatrix();
	}

}
