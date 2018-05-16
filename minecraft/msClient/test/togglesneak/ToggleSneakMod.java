package msClient.test.togglesneak;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import net.minecraft.client.settings.GameSettings;
import optfine.Config;


public class ToggleSneakMod
{
	
	public static boolean		optionToggleSprint		= true;
	public static boolean		optionToggleSneak		= true;
	public static boolean		optionShowHUDText		= true;
	public static int			optionHUDTextPosX		= 1;
	public static int			optionHUDTextPosY		= 1;
	public static boolean		optionDoubleTap			= false;
	public static boolean		optionEnableFlyBoost	= false;
	public static double		optionFlyBoostAmount	= 4.0;
	public static int			optionButtonPosition	= 1;
	
	public static boolean		wasSprintDisabled		= false;
	
	public static void realoadConfig() {
		updateConfig();
	}
	
	public static void saveConfig() {
		updateConfig();
	}
	
	public static void updateConfig() {
		
		
		try
        {
            PrintWriter p = new PrintWriter(new FileWriter(GameSettings.msToggle));
            
            p.println("optionToggleSprint:" + optionToggleSprint);
            p.println("optionToggleSneak:" + optionToggleSneak);
            p.println("optionShowHUDText:" + optionShowHUDText);
            p.println("optionHUDTextPosX:" + optionHUDTextPosX);
            p.println("optionHUDTextPosY:" + optionHUDTextPosY);
            p.println("optionDoubleTap:" + optionDoubleTap);
            p.println("optionEnableFlyBoost:" + optionEnableFlyBoost);
            p.println("optionFlyBoostAmount:" + optionFlyBoostAmount);
            p.println("optionButtonPosition:" + optionButtonPosition);
            
            p.close();
        }catch(Exception exception) {
       	 Config.Mwarn("Failed to save options");
         exception.printStackTrace();
        }
	}
	
	public static void loadToggleOptions() {
		try {
    		File file1 = GameSettings.msToggle;
    		
    		if(!file1.exists()) {
    			return;
    		}
    		
    		BufferedReader bufferedreader = new BufferedReader(new FileReader(file1));
    		String s = "";
    		
    		while ((s = bufferedreader.readLine()) != null)
            {
                try
                {
                    String[] astring = s.split(":");
                    if (astring[0].equals("optionToggleSprint")) {
                    	optionToggleSprint = astring[1].equals("true");
                    }
                    if (astring[0].equals("optionToggleSneak")) {
                    	optionToggleSneak = astring[1].equals("true");
                    }
                    if (astring[0].equals("optionShowHUDText")) {
                    	optionShowHUDText = astring[1].equals("true");
                    }
                    if (astring[0].equals("optionHUDTextPosX")) {
                    	optionHUDTextPosX = Integer.parseInt(astring[1]);
                    }
                    if (astring[0].equals("optionHUDTextPosY")) {
                    	optionHUDTextPosY = Integer.parseInt(astring[1]);
                    }
                    if (astring[0].equals("optionDoubleTap")) {
                    	optionDoubleTap = astring[1].equals("true");
                    }
                    if (astring[0].equals("optionEnableFlyBoost")) {
                    	optionEnableFlyBoost = astring[1].equals("true");
                    }
                    if (astring[0].equals("optionFlyBoostAmount")) {
                    	optionFlyBoostAmount = Double.parseDouble(astring[1]);
                    }
                    if (astring[0].equals("optionButtonPosition")) {
                    	optionButtonPosition = Integer.parseInt(astring[1]);
                    }
                }catch (Exception exception)
                {
                    Config.dbm("Skipping bad option: " + s);
                    exception.printStackTrace();
                }
            }
    		bufferedreader.close();
		}
		catch (Exception exception1)
        {
            Config.Mwarn("Failed to load options");
            exception1.printStackTrace();
        }
	}
	
}