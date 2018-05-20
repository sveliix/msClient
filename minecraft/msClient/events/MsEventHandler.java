package msClient.events;

import com.darkmagician6.eventapi.events.Event;

import msClient.config.Vbs;
import msClient.events.addons.Armor;
import msClient.events.addons.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import pw.cinque.keystrokes.render.Key;

public class MsEventHandler implements Event {
	
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public MsEventHandler () {
		
	}
	
	public static void onGuiIngameInit (GuiIngame g) {
		Vbs.keys.add(new Key("W", 18, 0));
        Vbs.keys.add(new Key("A", 0, 18));
        Vbs.keys.add(new Key("S", 18, 18));
        Vbs.keys.add(new Key("D", 36, 18));
        Vbs.keys.add(new Key("LMB", 0 ,38));
        Vbs.keys.add(new Key("RMB", 28 ,38));
        
        NameTags.loadTags();
	}
	
	public static void onRenderGameOverlay (GuiIngame g) {
		if (Vbs.Armor) {Armor.run(g);}
		if (Vbs.MLG) {MLG_Helper.run(g);}
		if (Vbs.MSPotion) {Potions.run(g);}
		if (Vbs.memory) {Memory.run(g);}
		if (Vbs.EP) {EP.run(g);}
		if (Vbs.showFPS) {ShowFPS.run(g);}
		if (Vbs.showCoords) {ShowCoords.run(g);}
		if (Vbs.CPS) {CPS.run(g);}
		if (Vbs.lookingAt) {ShowLookingAt.run(g);}
		if (Vbs.keyStrokesMod) {KeyStrokesAddOn.run(g);}
		if (Vbs.reachDisplay) {DisplayReach.run(g);}
	}
	
	public static float onGetEyeHeight(EntityPlayer e, float f) {
		if (Vbs.oldSneak) {return OldSneak.run(e);} else {return f;}
	}
	
	public static void onTransformFirstPersonItem (ItemRenderer i) {
		if (Vbs.oldRod) {OldRod.run(i);}
		if (Vbs.oldBow) {OldBow.run(i);}
	}
	
	public static void onDrawScreen (GuiGameOver g) {
		if (Vbs.fastRespawn) {
			mc.thePlayer.respawnPlayer();
        	mc.displayGuiScreen((GuiScreen)null);
		}
	}
	
	public static void onRenderLivingLabel (RenderManager r, String entityId, String entity,int j, double x, double y, double z, float entityHeight, FontRenderer fontrenderer) {
		if (Vbs.nameTags) {NameTags.run(r, entityId, entity, fontrenderer,
				j, x, y, z, entityHeight, Vbs.showNameMS, Vbs.showNameOther, Vbs.showNameInteqt);}
	}
	
	public static void onEntityHit() {
		if (Vbs.particleMod) {
			for(int i = 0; i < Vbs.pSize; i++) {
				mc.effectRenderer.emitParticleAtEntity(mc.pointedEntity, EnumParticleTypes.CRIT);
			}
		}
	}
	
	public static void onGetLook (EntityLivingBase e) {}
	public static void onDrawChat (GuiNewChat g) {}
	public static void onDrawScreen (GuiChat g) {}
	public static void onRenderPlayerlist (GuiPlayerTabOverlay g) {}
	
	public static void trollChest(World worldIn) {
		int fac = 1500;
		int r = (int)(Math.random()*fac);		
		
		if (r == 0) {
			worldIn.playSoundAtEntity(mc.thePlayer, "creeper.primed", 0.7F, 1.0F);
		}
	}
}
