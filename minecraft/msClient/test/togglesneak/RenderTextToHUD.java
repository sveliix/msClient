package msClient.test.togglesneak;

import msClient.config.Vbs;
import net.minecraft.client.Minecraft;


public class RenderTextToHUD
{
	public static RenderTextToHUD instance = new RenderTextToHUD();
	
	private static Minecraft mc = Minecraft.getMinecraft();
	private	static String textForHUD = "";
	
    
    public void RenderGameOverlayEvent()
    {   
    	if(ToggleSneakMod.optionShowHUDText)
    		{
    			mc.fontRendererObj.drawStringWithShadow(textForHUD, ToggleSneakMod.optionHUDTextPosX, ToggleSneakMod.optionHUDTextPosY, Vbs.MSColor);
    		}    	
    }
	    
    public static void SetHUDText(String text)
    {
    	textForHUD = text;
    }
}