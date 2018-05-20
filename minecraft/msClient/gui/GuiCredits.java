package msClient.gui;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import msClient.main;
import msClient.config.Vbs;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiScreen;

public class GuiCredits extends GuiScreen{
	
	private GuiScreen parentScreen;

	private final Object threadLock = new Object();
	
	private String msYouTube = "Coded by MSES and Svelix";
	private String msTwitter = "";	
	private String msYouLink = "https://www.youtube.com/channel/UC9AwSWO6I7OuFljTdFusP1g";
	private String msTwitterLink = "https://twitter.com/M1chel0000";
	private String krumDe = "Designed by Kruemelmomster";
	private String krumYouT = "https://www.youtube.com/channel/UC2HtbZOZ6IhaYviUAfw5K1Q";
	private String krumTwit = "https://twitter.com/krumelmomster";
	private String discord = "Join our Discord now";
	private String URLDiscord = "https://discord.gg/TbhUVqy";
	private int ms;
	private int msY;
	private int msT;
	private int krum;
	private int krumY;
	private int krumT;
	private int youMinX;
	private int youMinY;
	private int youMaxX;
	private int youMaxY;
	private int twitX;
	private int twitY;
	private int disc;
	
	public GuiCredits(GuiScreen parentScreen) {
		this.parentScreen = parentScreen;
	}

	@Override
	public void initGui() {
		
		this.buttonList.clear();
		buttonList.add(new GuiButton(10, 2, 2, 50, 20, "Back"));
		this.buttonList.add(new GuiButton(01, width/2-25, height/2 +8,50, 15, "Youtube"));
		this.buttonList.add(new GuiButton(02, width/2-25, height/2 +25,50, 15, "Twitter"));
		this.buttonList.add(new GuiButton(03, width/2-25, height/2 +73,50, 15, "Youtube"));
		this.buttonList.add(new GuiButton(04, width/2-25, height/2 +90,50, 15, "Twitter"));
		this.buttonList.add(new GuiButton(05, width/2-25, height-30, 50, 15, "Discord"));
		
		super.initGui();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		/*if(MSColorGui.rainBow) {
			variables.MSColor = java.awt.Color.HSBtoRGB((float)(System.currentTimeMillis()/1.5 % 1000L) / 1000.0F, 0.8F, 0.8F);
    	}*/
		
		ms = this.mc.fontRendererObj.getStringWidth("" + msYouTube)/2;
		msY = this.mc.fontRendererObj.getStringWidth("YouTube: " + msYouLink)/2;
		msT = this.mc.fontRendererObj.getStringWidth("Twitter: " + msTwitterLink)/2;
		youMinX = width/2 - msY;
		youMinY = height/2 + 10;
		youMaxX = width/2+200 - msY;
		youMaxY = height/2 + 16;
		krum = this.mc.fontRendererObj.getStringWidth(krumDe)/2;
		krumY = this.mc.fontRendererObj.getStringWidth("YouTube: " + krumYouT)/2;
		krumT = this.mc.fontRendererObj.getStringWidth("Twitter: " + krumTwit)/2;
		disc = this.mc.fontRendererObj.getStringWidth(discord)/2;
		
		drawDefaultBackground();
		
		int t = this.fontRendererObj.getStringWidth("MSClient")/2;		
		
		GL11.glPushMatrix();
		GL11.glScalef(2F, 2F, 2F);
		this.drawString(this.fontRendererObj, "MSClient", Math.round((width/2 - t - 17)/2F), Math.round(75/2F), Color.WHITE.getRGB());
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glScalef(1.4F, 1.4F, 1.4F);
		this.drawString(this.fontRendererObj, msYouTube, Math.round((width/2 - ms-17)/1.4F), Math.round((height/2-10)/1.4F), Vbs.MSColor);
		GL11.glPopMatrix();
		//this.drawString(this.fontRendererObj, "YouTube: " , width/2 - 50, height/2 + 10, GuiIngame.MSColor);
		//this.drawString(this.fontRendererObj, "Twitter: " , width/2 - 50, height/2 + 20, GuiIngame.MSColor);
		

		GL11.glPushMatrix();
		GL11.glScalef(1.4F, 1.4F, 1.4F);
		this.drawString(this.fontRendererObj, krumDe, Math.round((width/2 - krum - 17)/1.4F), Math.round((height/2 + 55)/1.4F), Vbs.MSColor);		
		GL11.glPopMatrix();
		//this.drawString(this.fontRendererObj, "YouTube: " , width/2 - 50, height/2 + 55, GuiIngame.MSColor);
		//this.drawString(this.fontRendererObj, "Twitter: " , width/2 - 50, height/2 + 65, GuiIngame.MSColor);
		GL11.glPushMatrix();
		GL11.glScalef(1.1F, 1.1F, 1.1F);
		this.drawString(this.fontRendererObj, discord, Math.round((width/2 - disc - 5)/1.1F), Math.round((height-45)/1.1F), Vbs.MSColor);
		GL11.glPopMatrix();
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch(button.id) {
		case 10: this.mc.displayGuiScreen(this.parentScreen);
			break;
		case 01: main.instance.openWebpage(msYouLink, true);
			break;
		case 02: main.instance.openWebpage(msTwitterLink, true);
			break;
		case 03: main.instance.openWebpage(krumYouT, true);
			break;
		case 04:main.instance.openWebpage(krumTwit, true);
			break;
		case 05: main.instance.openWebpage(URLDiscord, true);
			break;
		}
		super.actionPerformed(button);
	}

	
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		
		
            
        
		
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

}
