package msClient.config;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import msClient.gui.MSMainMenu;
import msClient.sv.InGuiObject;
import net.minecraft.client.settings.KeyBinding;
import pw.cinque.keystrokes.render.Key;

public class Vbs {

	public static boolean oldBow = true;		//MSGuiSettings
	public static boolean oldRod = true;
	public static boolean oldSneak = true;
	public static boolean oldBlockHit = true;
	public static boolean oldBlockBuild = false;
	public static boolean oldDmg = true;
	public static boolean fastChat = true;
	public static boolean fastScore = true;
	public static boolean cleanTab = true;
	public static boolean pingOnTab = true;
	public static boolean showMyName = true;
	public static boolean nameTags = true;
	public static boolean showSneak = true;
	public static boolean mouseDelayFix = true;
	public static boolean fastRespawn = true;	
	public static boolean lookingAt = false;
	public static boolean scoreboard = true;
	
	public static boolean rainBow = false; //MSColorGui
	
	public static float x = 0.7F;	//GuiIngame
	public static boolean showFPS;	
	public static boolean showCoords;	
	public static boolean MLG;	
	public static boolean CPS;	
	public static boolean EP = false;	
	// public static boolean MSMOD;
	public static int MSColor = Color.WHITE.getRGB();	
	public static boolean memory;	
	public static boolean Armor;	
	public static  boolean MSPotion;	
	//public static boolean hasActivePotionEffects;
	public static boolean keyStrokesMod;	
	public static boolean reachDisplay;	
	public static boolean fastReach;	
	public static boolean fastKeyStrokes;		
	public static boolean funnyReach;	
	public static boolean funnyCPS;	
	public static List<Key> keys = new ArrayList<Key>();
	
	public static ArrayList<InGuiObject> objects = new ArrayList<InGuiObject>();	//SVGuiPos
	
	public static boolean particle = true;		//GuiScreen
	public static boolean oldParticles = false;
    public static boolean coverParticles = true;
    public static boolean particleOptions = true; // 
    
    public static float debugG = 0.6F;	//GuiOverlayDebug
    
    public static int location;		//KeyStrokesMod
    public static int keyStrokes_x;
    public static int keyStrokes_y;
    
    public static float radius = 2F;	//SvParticleGen
	public static float size = 2F;
	public static float speed = 1.5F;
	
	public static boolean showNameMS = true;
	public static boolean showNameInteqt = true;
	public static boolean showNameOther = false;
	
	public static boolean particleMod;		//PlayerControllerMP
	public static int pSize = 1;
	
	public static boolean isDeprecated = false;
    
}
