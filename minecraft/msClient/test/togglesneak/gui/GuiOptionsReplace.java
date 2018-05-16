package msClient.test.togglesneak.gui;

import java.io.IOException;

import msClient.test.togglesneak.ToggleSneakMod;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;

public class GuiOptionsReplace extends GuiScreen
{
	private GuiButton btnToggleSneakOptions;
	
	public GuiOptionsReplace(GuiScreen parentScreen, GameSettings settings)
	{	
		super();
	}
	
	
	public void initGui()
	{
		super.initGui();
		
		int yPos;
		
		if(ToggleSneakMod.optionButtonPosition == 1)	yPos = -155;
		else 											yPos = 5;
				
		btnToggleSneakOptions = new GuiButton(9999, this.width /2 + yPos, this.height / 6 + 24 - 6, 150, 20, "ToggleSneak Options");
		this.buttonList.add(btnToggleSneakOptions);
		
	}
	
	
	protected void actionPerformed(GuiButton buttonPressed)
	{
		try
		{
			super.actionPerformed(buttonPressed);
			
			if(buttonPressed.id == 9999)
			{
				this.mc.gameSettings.saveOptions();
				this.mc.displayGuiScreen(new TSGuiConfig(this));
			}
		}
		catch (IOException ex)
		{
			;
		}
	}
}