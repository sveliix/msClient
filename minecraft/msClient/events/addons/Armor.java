package msClient.events.addons;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import msClient.main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;

public class Armor extends AddOn {
	
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static void run (GuiIngame g) {
		
		//mc.fontRendererObj.drawString("Test " + main.instance.getTSClients(), 100, 100, variables.MSColor);
		
		if(mc.thePlayer.getCurrentArmor(0) != null){
    		int a0 = mc.thePlayer.getCurrentArmor(0).getItemDamage();
			int b0 = mc.thePlayer.getCurrentArmor(0).getMaxDamage();
			int color0 = Color.WHITE.getRGB();
			if(b0-a0 <= b0/10*3 && b0-a0 > b0/10) {
				color0 = Color.ORANGE.getRGB();
			}else if(b0-a0 <= b0/10) {
				color0 = Color.RED.getRGB();
			}
			
			if(b0-a0>=0) {
				GL11.glPushMatrix(); //Start new matrix
	        	GL11.glScalef(0.6F, 0.6F, 0.6F); //scale it to 0.5 size on each side. Must be float e.g.: 2
	        	mc.fontRendererObj.drawString(b0-a0 + "/" + b0, Math.round((mc.currentScreen.width/2+106)/0.6F), Math.round((mc.currentScreen.height-7)/0.6F), color0);
	        	GL11.glPopMatrix();
			}
    	}
    	if(mc.thePlayer.getCurrentArmor(1) != null){
    		int a1 = mc.thePlayer.getCurrentArmor(1).getItemDamage();
			int b1 = mc.thePlayer.getCurrentArmor(1).getMaxDamage();
			int color1 = Color.WHITE.getRGB();
			if(b1-a1 <= b1/10*3 && b1-a1 > b1/10) {
				color1 = Color.ORANGE.getRGB();
			}else if(b1-a1 <= b1/10) {
				color1 = Color.RED.getRGB();
			}
			
			if(b1-a1>=0) {
				GL11.glPushMatrix(); //Start new matrix
		    	GL11.glScalef(0.6F, 0.6F, 0.6F); //scale it to 0.5 size on each side. Must be float e.g.: 2.0F
				mc.fontRendererObj.drawString(b1-a1 + "/" + b1, Math.round((mc.currentScreen.width/2+106)/0.6F), Math.round((mc.currentScreen.height-17)/0.6F), color1);
				GL11.glPopMatrix();
			}
    	}
    	if(mc.thePlayer.getCurrentArmor(2) != null){
    		int a2 = mc.thePlayer.getCurrentArmor(2).getItemDamage();
			int b2 = mc.thePlayer.getCurrentArmor(2).getMaxDamage();
			int color2 = Color.WHITE.getRGB();
			if(b2-a2 <= b2/10*3 && b2-a2 > b2/10) {
				color2 = Color.ORANGE.getRGB();
			}else if(b2-a2 <= b2/10) {
				color2 = Color.RED.getRGB();
			}
			if(b2-a2>=0) {
				GL11.glPushMatrix(); //Start new matrix
				GL11.glScalef(0.6F, 0.6F, 0.6F); //scale it to 0.5 size on each side. Must be float e.g.: 2.0F
				mc.fontRendererObj.drawString(b2-a2 + "/" + b2, Math.round((mc.currentScreen.width/2+106)/0.6F), Math.round((mc.currentScreen.height-27)/0.6F), color2);
				GL11.glPopMatrix();
			}
    	}
    	if(mc.thePlayer.getCurrentArmor(3) != null){
    		int a3 = mc.thePlayer.getCurrentArmor(3).getItemDamage();
			int b3 = mc.thePlayer.getCurrentArmor(3).getMaxDamage();
			int color3 = Color.WHITE.getRGB();
			if(b3-a3 <= b3/10*3 && b3-a3 > b3/10) {
				color3 = Color.ORANGE.getRGB();
			}else if(b3-a3 <= b3/10) {
				color3 = Color.RED.getRGB();
			}
			if(b3-a3>=0) {
				GL11.glPushMatrix(); //Start new matrix
				GL11.glScalef(0.6F, 0.6F, 0.6F); //scale it to 0.5 size on each side. Must be float e.g.: 2.0F
				mc.fontRendererObj.drawString(b3-a3 + "/" + b3, Math.round((mc.currentScreen.width/2+106)/0.6F), Math.round((mc.currentScreen.height-37)/0.6F), color3);
				GL11.glPopMatrix();
			}
    	}
    	mc.fontRendererObj.drawString("", Math.round((mc.currentScreen.width/2+106)/0.6F), Math.round((mc.currentScreen.height-37)/0.6F), Color.WHITE.getRGB());
		
		if(mc.thePlayer.getCurrentArmor(0) != null){
			if(mc.thePlayer.getCurrentArmor(0).getItem() == Items.diamond_boots) {
				final ResourceLocation tex = new ResourceLocation("textures/items/diamond_boots.png");
				mc.getTextureManager().bindTexture(tex);
				
				Gui.drawModalRectWithCustomSizedTexture(mc.currentScreen.width/2+95, mc.currentScreen.height-10, 0, 0, 10, 10, 10, 10);
				
			}else if(mc.thePlayer.getCurrentArmor(0).getItem() == Items.iron_boots) {
				final ResourceLocation tex = new ResourceLocation("textures/items/iron_boots.png");
				mc.getTextureManager().bindTexture(tex);
				
				
				Gui.drawModalRectWithCustomSizedTexture(mc.currentScreen.width/2+95, mc.currentScreen.height-10, 0, 0, 10, 10, 10, 10);
				
			}else if(mc.thePlayer.getCurrentArmor(0).getItem() == Items.golden_boots) {
				final ResourceLocation tex = new ResourceLocation("textures/items/gold_boots.png");
				mc.getTextureManager().bindTexture(tex);
				
				Gui.drawModalRectWithCustomSizedTexture(mc.currentScreen.width/2+95, mc.currentScreen.height-10, 0, 0, 10, 10, 10, 10);
				
			}else if(mc.thePlayer.getCurrentArmor(0).getItem() == Items.chainmail_boots) {
				final ResourceLocation tex = new ResourceLocation("textures/items/chainmail_boots.png");
				mc.getTextureManager().bindTexture(tex);
				
				Gui.drawModalRectWithCustomSizedTexture(mc.currentScreen.width/2+95, mc.currentScreen.height-10, 0, 0, 10, 10, 10, 10);
				
			}else if(mc.thePlayer.getCurrentArmor(0).getItem() == Items.leather_boots) {
				final ResourceLocation tex = new ResourceLocation("textures/items/leather_boots.png");
				mc.getTextureManager().bindTexture(tex);
				
				Gui.drawModalRectWithCustomSizedTexture(mc.currentScreen.width/2+95, mc.currentScreen.height-10, 0, 0, 10, 10, 10, 10);
				
			}else {
				
			}
		}
		if(mc.thePlayer.getCurrentArmor(1) != null){
			if(mc.thePlayer.getCurrentArmor(1).getItem() == Items.diamond_leggings) {
				final ResourceLocation tex = new ResourceLocation("textures/items/diamond_leggings.png");
				mc.getTextureManager().bindTexture(tex);
				
				Gui.drawModalRectWithCustomSizedTexture(mc.currentScreen.width/2+95, mc.currentScreen.height-20, 0, 0, 10, 10, 10, 10);
				
			}else if(mc.thePlayer.getCurrentArmor(1).getItem() == Items.iron_leggings) {
				final ResourceLocation tex = new ResourceLocation("textures/items/iron_leggings.png");
				mc.getTextureManager().bindTexture(tex);
				
				Gui.drawModalRectWithCustomSizedTexture(mc.currentScreen.width/2+95, mc.currentScreen.height-20, 0, 0, 10, 10, 10, 10);
				
			}else if(mc.thePlayer.getCurrentArmor(1).getItem() == Items.golden_leggings) {
				final ResourceLocation tex = new ResourceLocation("textures/items/gold_leggings.png");
				mc.getTextureManager().bindTexture(tex);
				
				Gui.drawModalRectWithCustomSizedTexture(mc.currentScreen.width/2+95, mc.currentScreen.height-20, 0, 0, 10, 10, 10, 10);
				
			}else if(mc.thePlayer.getCurrentArmor(1).getItem() == Items.chainmail_leggings) {
				final ResourceLocation tex = new ResourceLocation("textures/items/chainmail_leggings.png");
				mc.getTextureManager().bindTexture(tex);
				
				Gui.drawModalRectWithCustomSizedTexture(mc.currentScreen.width/2+95, mc.currentScreen.height-20, 0, 0, 10, 10, 10, 10);
				
			}else if(mc.thePlayer.getCurrentArmor(1).getItem() == Items.leather_leggings) {
				final ResourceLocation tex = new ResourceLocation("textures/items/leather_leggings.png");
				mc.getTextureManager().bindTexture(tex);
				
				Gui.drawModalRectWithCustomSizedTexture(mc.currentScreen.width/2+95, mc.currentScreen.height-20, 0, 0, 10, 10, 10, 10);
				
			}else {
				
			}
		}
		if(mc.thePlayer.getCurrentArmor(2) != null){
			if(mc.thePlayer.getCurrentArmor(2).getItem() == Items.diamond_chestplate) {
				final ResourceLocation tex = new ResourceLocation("textures/items/diamond_chestplate.png");
				mc.getTextureManager().bindTexture(tex);
				
				Gui.drawModalRectWithCustomSizedTexture(mc.currentScreen.width/2+95, mc.currentScreen.height-30, 0, 0, 10, 10, 10, 10);
				
				
			}else if(mc.thePlayer.getCurrentArmor(2).getItem() == Items.iron_chestplate) {
				final ResourceLocation tex = new ResourceLocation("textures/items/iron_chestplate.png");
				mc.getTextureManager().bindTexture(tex);
				
				Gui.drawModalRectWithCustomSizedTexture(mc.currentScreen.width/2+95, mc.currentScreen.height-30, 0, 0, 10, 10, 10, 10);
				
			}else if(mc.thePlayer.getCurrentArmor(2).getItem() == Items.golden_chestplate) {
				final ResourceLocation tex = new ResourceLocation("textures/items/gold_chestplate.png");
				mc.getTextureManager().bindTexture(tex);
				
				Gui.drawModalRectWithCustomSizedTexture(mc.currentScreen.width/2+95, mc.currentScreen.height-30, 0, 0, 10, 10, 10, 10);
				
			}else if(mc.thePlayer.getCurrentArmor(2).getItem() == Items.chainmail_chestplate) {
				final ResourceLocation tex = new ResourceLocation("textures/items/chainmail_chestplate.png");
				mc.getTextureManager().bindTexture(tex);
				
				Gui.drawModalRectWithCustomSizedTexture(mc.currentScreen.width/2+95, mc.currentScreen.height-30, 0, 0, 10, 10, 10, 10);
				
			}else if(mc.thePlayer.getCurrentArmor(2).getItem() == Items.leather_chestplate) {
				final ResourceLocation tex = new ResourceLocation("textures/items/leather_chestplate.png");
				
				mc.getTextureManager().bindTexture(tex);
				
				Gui.drawModalRectWithCustomSizedTexture(mc.currentScreen.width/2+95, mc.currentScreen.height-30, 0, 0, 10, 10, 10, 10);
				
			}else {
				
			}
		}
		if(mc.thePlayer.getCurrentArmor(3) != null){
			if(mc.thePlayer.getCurrentArmor(3).getItem() == Items.diamond_helmet) {
				final ResourceLocation tex = new ResourceLocation("textures/items/diamond_helmet.png");
				mc.getTextureManager().bindTexture(tex);
				
				Gui.drawModalRectWithCustomSizedTexture(mc.currentScreen.width/2+95, mc.currentScreen.height-40, 0, 0, 10, 10, 10, 10);
				
			}else if(mc.thePlayer.getCurrentArmor(3).getItem() == Items.iron_helmet) {
				final ResourceLocation tex = new ResourceLocation("textures/items/iron_helmet.png");
				mc.getTextureManager().bindTexture(tex);
				
				Gui.drawModalRectWithCustomSizedTexture(mc.currentScreen.width/2+95, mc.currentScreen.height-40, 0, 0, 10, 10, 10, 10);
				
			}else if(mc.thePlayer.getCurrentArmor(3).getItem() == Items.golden_helmet) {
				final ResourceLocation tex = new ResourceLocation("textures/items/gold_helmet.png");
				mc.getTextureManager().bindTexture(tex);
				
				Gui.drawModalRectWithCustomSizedTexture(mc.currentScreen.width/2+95, mc.currentScreen.height-40, 0, 0, 10, 10, 10, 10);
				
			}else if(mc.thePlayer.getCurrentArmor(3).getItem() == Items.chainmail_helmet) {
				final ResourceLocation tex = new ResourceLocation("textures/items/chainmail_helmet.png");
				mc.getTextureManager().bindTexture(tex);
				
				Gui.drawModalRectWithCustomSizedTexture(mc.currentScreen.width/2+95, mc.currentScreen.height-40, 0, 0, 10, 10, 10, 10);
				
			}else if(mc.thePlayer.getCurrentArmor(3).getItem() == Items.leather_helmet) {
				final ResourceLocation tex = new ResourceLocation("textures/items/leather_helmet.png");
				mc.getTextureManager().bindTexture(tex);
				
				Gui.drawModalRectWithCustomSizedTexture(mc.currentScreen.width/2+95, mc.currentScreen.height-40, 0, 0, 10, 10, 10, 10);
				
			}else {
				
			}
		}
	}

}
