package msClient.events.addons;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import msClient.config.Variables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import pw.cinque.keystrokes.render.Key;
import pw.cinque.keystrokes.settings.Location;

public class KeyStrokesAddOn {
	
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static void run (GuiIngame g) {
		ScaledResolution sr = new ScaledResolution(mc);
        int i1 = 5;
        int j1 = 5;
        
        
        switch (Location.values()[Variables.location])
        {
            case TOP_LEFT:
                break;
            case TOP_RIGHT:
                i1 += sr.getScaledWidth() - 64;
                break;
            case BOTTOM_LEFT:
                j1 += sr.getScaledHeight() - 66;
                break;
            case BOTTOM_RIGHT:
                i1 += sr.getScaledWidth() - 64;
                j1 += sr.getScaledHeight() - 66;
                break;
            case TOP_MIDDLE:
            	i1 += (sr.getScaledWidth()/2 - 32);
            	break;
            case CUSTOM:
            	i1 = Variables.keyStrokes_x;
            	j1 = Variables.keyStrokes_y;
            	break;
            default:
                return;
        }

        for (Key key : Variables.keys)
        {
        	if (key.getName().equals("LMB")) {
        		key.setPressed(Mouse.isButtonDown(0));
        	}
        	
        	if (key.getName().equals("RMB")) {
        		key.setPressed(Mouse.isButtonDown(1));
        	}
        	
            int k = mc.fontRendererObj.getStringWidth(key.getName());
            if(!Variables.fastKeyStrokes) {
            	Gui.drawRect(i1 + key.getX(), j1 + key.getY(), i1 + key.getX() + key.getWidth(), j1 + key.getY() + key.getHeight(), key.isPressed() ? 1728053247 : 1711276032);
            }
            mc.fontRendererObj.drawString(key.getName(), i1 + key.getX() + key.getWidth() / 2 - k / 2, j1 + key.getY() + key.getHeight() / 2 - 4, key.isPressed() ? -16777216 : Variables.MSColor);
            
        }
        
        for (Key key : Variables.keys)
        {
            if (!key.isMouseKey())
            {
                key.setPressed(Keyboard.isKeyDown(key.getKey()));
            }
        }

        for (Key key1 : Variables.keys)
        {
            if (key1.isMouseKey())
            {
                key1.setPressed(Mouse.isButtonDown(key1.getKey()));
            }
            if(key1.getName() == "LMB") {
            	key1.setPressed(Mouse.isButtonDown(0));
            }
            if(key1.getName() == "RMB") {
            	key1.setPressed(Mouse.isButtonDown(1));
            }
        }

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableLighting();
        GlStateManager.enableAlpha();   
	}
}
