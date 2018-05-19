package msClient.events.addons;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.IChatComponent;

public class NameTags {
	
	public static ArrayList<Tag> msTags = new ArrayList<Tag>();
	public static ArrayList<Tag> inteqtTags = new ArrayList<Tag>();
	public static ArrayList<Tag> otherTags = new ArrayList<Tag>();

	public static Minecraft mc = Minecraft.getMinecraft();
	
	// TODO implement method to load tags and uuids from website
	
	public static void loadTags () {
		msTags.add(new Tag("f259c4cf-ead1-4c30-98ff-afd3a070af03", "MSClient-Admin")); // MSES
		msTags.add(new Tag("70715015-d692-464c-8e93-39e103823eb0", "MSClient-Admin")); // MS3
		msTags.add(new Tag("247b3708-3b98-4831-9a61-8966a9a3db4e", "MSClient-Admin")); // Sven
		
		msTags.add(new Tag("4bdadd8a-9620-48ed-a406-791529226f57", "MSClient-Mod")); // Erik
		msTags.add(new Tag("afc4364b-2abf-4120-bf77-6fd54ad729c6", "MSClient-Mod")); /*AAAAAAAALDAAAA*/
		
		msTags.add(new Tag("ee8efb6a-7881-4f88-addc-651b19044574", "MSClient-Supporter")); //Domi
		
		inteqtTags.add(new Tag("10ffba84-8a52-4600-864d-c3a34189b480", "Inteqt-Leader")); // Miguel
		
		inteqtTags.add(new Tag("9c83fb69-a0c7-4cbb-a045-27bf5b70dff6", "Inteqt-Mod")); // Nico
		inteqtTags.add(new Tag("1daf1cff-6da1-4786-afd5-2509f1ad52b3", "Inteqt-Mod")); // Danny
		inteqtTags.add(new Tag("66f8760a-bfa1-424e-b63d-ac9c3081a318", "Inteqt-Mod")); // Tobi
		inteqtTags.add(new Tag("1b0796c8-ccc3-4639-bc64-e8e47d5abd5c", "Inteqt-Mod")); // Liiinuus
		
		inteqtTags.add(new Tag("e37008f0-ced4-422a-8b22-3dad94997d37", "Inteqt-Member")); // SupremeNico
		inteqtTags.add(new Tag("65cbdde9-484b-428a-8a74-64a1ec1835a4", "Inteqt-Member")); // Clem
		inteqtTags.add(new Tag("7391a830-2033-4e96-9903-7453d8fd633e", "Inteqt-Member")); // Lukas
		inteqtTags.add(new Tag("a9ac4d8e-0a38-4e4a-91b7-016254b28e0c", "Inteqt-Member")); // Jay
		inteqtTags.add(new Tag("da14a496-724a-470f-9b8d-dbe6ab87ca3e", "Inteqt-Member")); // Vini
		
		inteqtTags.add(new Tag("64e25295-adb6-4264-a65f-2755809599b4", "Inteqt-Spec"));
	}	
	public static void run (RenderManager r, 
			String entityId, String entity, FontRenderer fontrenderer, 
			int j, double x, double y, double z, float entityHeight,
			boolean showMS, boolean showOther, boolean showInteqt) {			
		
        float f = 1.6F;
        float f1 = 0.016666668F * f;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x + 0.0F, (float)y + entityHeight + 0.5F, (float)z);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-r.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(r.playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(-f1, -f1, f1);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(false);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        int i = 0;
		
        GL11.glPushMatrix(); 
        GL11.glScalef(0.7F, 0.7F, 0.7F);
        
		if (showMS) {
		    for (Tag t : msTags) {
		    	if (entityId.equalsIgnoreCase(t.uuid)) {
		    		fontrenderer.drawString(t.tag, Math.round(-fontrenderer.getStringWidth(t.tag)/2), i-10, 553648127);
		    	}
		    }
		}
		
		if(showInteqt) {
			for (Tag t : inteqtTags) {
				if (entityId.equalsIgnoreCase(t.uuid)) {
					fontrenderer.drawString(t.tag, Math.round(-fontrenderer.getStringWidth(t.tag)/2), i-10, 553648127);
				}
			}
		}

		if (showOther) {			
			for (Tag t : otherTags) {				
				if (entity.equalsIgnoreCase(t.uuid)) {
					
					fontrenderer.drawString(t.tag + "✔", Math.round(-fontrenderer.getStringWidth(t.tag)/2), i-10, 553648127);
				}
			}
		}
		
        GL11.glPopMatrix();
            
        GlStateManager.enableDepth();
        GlStateManager.depthMask(true);
        GL11.glPushMatrix();
        GL11.glScalef(0.7F, 0.7F, 0.7F);
        
		if (showMS) {
	    	for (Tag t : msTags) {
		    	if (entityId.equalsIgnoreCase(t.uuid)) {
		    		fontrenderer.drawString(t.tag, Math.round(-fontrenderer.getStringWidth(t.tag)/2), i-10, -1);
		    	}
		    }
		}
		
		if(showInteqt) {
			for (Tag t : inteqtTags) {
				if (entityId.equalsIgnoreCase(t.uuid)) {
					fontrenderer.drawString(t.tag, Math.round(-fontrenderer.getStringWidth(t.tag)/2), i-10, -1);
				}
			}
		}
		
		if (showOther) {			
			for (Tag t : otherTags) {
				if (entity.equalsIgnoreCase(t.uuid)) {
					fontrenderer.drawString(t.tag + "✔", Math.round(-fontrenderer.getStringWidth(t.tag)/2), i-10, -1);
				}
			}
		}
		
        GL11.glPopMatrix();
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();
	}
	
	public static class Tag {
		String uuid, tag;
		
		public Tag (String uuid, String tag) {
			this.uuid = uuid;
			this.tag = tag;
		}
		
		public String getUUID() {
			return this.uuid;
		}
		
		public String getTag() {
			return this.tag;
		}
	}
	
}
