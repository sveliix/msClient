package msClient.utils;

import msClient.config.Variables;
import net.minecraft.client.gui.GuiIngame;

public class ClickCounter {

	static long time = 0L;
    static int clicks = 0;
    static int result = 0;

    public static int getClickResult()
    {
    	if(Variables.funnyCPS) {
    		return (int) ((int) result*11.4F);
    	}
        return result;
    }

    public static void click()
    {
        if (Variables.CPS)
        {
            ++clicks;
            //System.out.println("Test");
        }
    }

    public static void tick()
    {
    	//result = clicks;
        if (Variables.CPS && time < System.currentTimeMillis())
        {
            result = clicks;
            time = System.currentTimeMillis() + 1000L;
            clicks = 0;
        }
    }
	
}
