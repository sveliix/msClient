package msClient.utils;

import java.util.ArrayList;

public class ToggleSprintManager {

	public static ArrayList<ToggleSprintMod> modules = new ArrayList<ToggleSprintMod>();
	
	public ToggleSprintManager()
	{
		
		//add modules 
	}
	
	public static ToggleSprintMod getModule(Class<? extends ToggleSprintMod> c)
	{
		for (ToggleSprintMod mod : getModules())
		{
			if (c == mod.getClass())
			{
				return mod;
			}
		}
		
		return null;
	}

	public static ArrayList<ToggleSprintMod> getModules() {
		return modules;
	}
	
}
