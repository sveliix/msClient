package msClient.utils;

import net.minecraft.client.Minecraft;

public class ToggleSprintMod {

	protected static Minecraft mc = Minecraft.getMinecraft();

	private String name;
	private int bind;
	
	public boolean isEnabled;
	
	public ToggleSprintMod(String n, int b )
	{
		this.name = n;
		this.bind = b;		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBind() {
		return bind;
	}

	public void setBind(int bind) {
		this.bind = bind;
	}

	
	public void toggle()
	{
		isEnabled = !isEnabled;
		
		if (isEnabled)
		{
			this.onEnable();
		}
		else
		{
			this.onDisable();
		}
			
	}
	
	public void onEnable() {
		
		mc.thePlayer.sendChatMessage(this.getName());
		
	}
	public void onDisable() {
		
		mc.thePlayer.sendChatMessage(this.getName());
		
	}
	public void onTick() {}
	
}
