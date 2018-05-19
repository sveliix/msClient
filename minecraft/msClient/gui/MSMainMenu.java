package msClient.gui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;
import com.ibm.icu.impl.ICUService.Key;

import msClient.main;
import msClient.ParticleAPI.Client.Particle.SvParticleGen;
import msClient.ParticleAPI.Client.Particle.Impl.SvAutismParticle;
import msClient.config.Variables;
import msClient.events.addons.NameTags;
import msClient.events.addons.NameTags.Tag;
import msClient.sv.SVGuiPos;
import msClient.utils.GuiSliderFixed;
import msClient.utils.SliderSize;
import msClient.utils.ToggleSprintManager;
import msClient.utils.ToggleSprintMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentText;

public class MSMainMenu extends GuiScreen implements GuiYesNoCallback {
	
	private final GuiScreen parentScreen; // parent screen
	
	private int tabY = 0;
	int sy = 40;
	int ey = 21;
	int sx = 120;
	int x;
	int y;
	
	private String link_yt_mses = "https://www.youtube.com/channel/UC9AwSWO6I7OuFljTdFusP1g";
	private String link_yt_kmom = "https://www.youtube.com/channel/UC2HtbZOZ6IhaYviUAfw5K1Q";
	private String link_tw_mses = "https://twitter.com/M1chel0000";
	private String link_tw_kmom = "https://twitter.com/krumelmomster";
	private String link_discord = "https://discord.gg/TbhUVqy";
	
	public MSMainMenu(GuiScreen parentScreen) {
		this.parentScreen = parentScreen;
	}
	
	private ArrayList<SvLabel> llCurrent = new ArrayList<SvLabel>();
	
	private List<GuiButton> blDefault = Lists.<GuiButton>newArrayList();
	private ArrayList<SvLabel> llDefault = new ArrayList<SvLabel>();

	private List<GuiButton> blMain = Lists.<GuiButton>newArrayList();
	private ArrayList<SvLabel> llMain = new ArrayList<SvLabel>();
	
	private List<GuiButton> blGameplay = Lists.<GuiButton>newArrayList();
	private ArrayList<SvLabel> llGameplay = new ArrayList<SvLabel>();
	
	private List<GuiButton> blOverlay = Lists.<GuiButton>newArrayList();
	private ArrayList<SvLabel> llOverlay = new ArrayList<SvLabel>();
	
	private List<GuiButton> blIngame = Lists.<GuiButton>newArrayList();
	private ArrayList<SvLabel> llIngame = new ArrayList<SvLabel>();
	
	private List<GuiButton> blMenues = Lists.<GuiButton>newArrayList();
	private ArrayList<SvLabel> llMenues = new ArrayList<SvLabel>();
	
	private List<GuiButton> blCommands = Lists.<GuiButton>newArrayList();
	private ArrayList<GuiTextField> tlCommands = new ArrayList<GuiTextField>();
	private ArrayList<SvLabel> llCommands = new ArrayList<SvLabel>();
	
	private List<GuiButton> blNameTags = Lists.<GuiButton>newArrayList();
	private ArrayList<GuiTextField> tlNameTagsName = new ArrayList<GuiTextField>();
	private ArrayList<GuiTextField> tlNameTagsTag = new ArrayList<GuiTextField>();
	private ArrayList<SvLabel> llNameTags = new ArrayList<SvLabel>();
	
	private ArrayList<GuiButton> delButtons = new ArrayList<GuiButton>();
	
	private int currentTab = 0;
	private boolean isRecordingKey = false;
	private int currKey = -1;
	
	public static int 
	ytLength = Minecraft.getMinecraft().fontRendererObj.getStringWidth("Youtube"),
	twLength = Minecraft.getMinecraft().fontRendererObj.getStringWidth("Twitter");
	
	static GuiButton 
		ba00, ba01, ba02, ba03, ba04, ba05, ba06,
		bb00, bb01, bb02, bb03, bb04, bb05,
		bc00, bc01, bc02, bc03, bc04, bc05, bc06, bc07, bc09, bc10, bc11, bc12, bc13, bc14, bc15,
		bd00, bd01, bd02,
		be00, be01, be02, be03, be04, be05, be06, be07, be08, be09, be10, be11, be12, be13, be14, be15, be17,
		bf00, bf01, bf02, bf03, bf04, bf05, bf06, bf07, bf08, bf09,
		bg00, bg01, bg02,
		bn00,
		bdel00, bdel01, bdel02, bdel03, bdel04, bdel05, bdel06, bdel07, bdel08, bdel09;
	static GuiTextField
		tg00, tg01, tg02;
	
	static GuiSliderFixed
		bf10, bf11, bf12,
		sd00, se00, se02;
	
	@Override
	public void setWorldAndResolution(Minecraft mc, int width, int height) {
		
		super.setWorldAndResolution(mc, width, height);
	}

	@Override
	public void onResize(Minecraft mcIn, int x, int y) {
		super.onResize(mcIn, x, y);
	}
	
	@Override
	public void initGui() {
		
		this.buttonList.clear();
		this.blCommands.clear();
		this.blDefault.clear();
		this.blGameplay.clear();
		this.blIngame.clear();
		this.blMain.clear();
		this.blMenues.clear();
		this.blNameTags.clear();
		this.blOverlay.clear();
		this.delButtons.clear();
		this.labelList.clear();
		this.llCommands.clear();
		this.llCurrent.clear();
		this.llDefault.clear();
		this.llGameplay.clear();
		this.llIngame.clear();
		this.llMain.clear();
		this.llMenues.clear();
		this.llNameTags.clear();
		this.llOverlay.clear();
		this.textfieldList.clear();
		this.tlCommands.clear();
		this.tlNameTagsName.clear();
		this.tlNameTagsTag.clear();
		
		this.tabY = 28;
        this.sy = this.height / 14;

        if (this.sy > 30)
        {
            this.sy = 30;
        }else if(this.sy < 21) {
        	this.sy = 21;
        }

        this.ey = this.height / 9;

        if (this.ey > 25)
        {
            this.ey = 25;
        }
        else if(this.ey < 21) {
        	this.ey = 21;
        }
        
        this.sx = this.width / 5;
        
        if(this.sx > 100) {
        	this.sx = 101;
        }else if(this.sx < 81) {
        	this.sx = 81;
        }
		
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		int scaledX = sr.getScaledWidth();
		int scaledY = sr.getScaledHeight();
		
		Keyboard.enableRepeatEvents(true);
		
		GuiPageButtonList.GuiResponder sliderResponder = new GuiPageButtonList.GuiResponder() {
			
			@Override
			public void onTick(int id, float value) {
			}
			
			@Override
			public void func_175321_a(int p_175321_1_, boolean p_175321_2_) {
			}
			
			@Override
			public void func_175319_a(int p_175319_1_, String p_175319_2_) {
			}
		};
		this.y = 0;
		this.x = 0;
		blDefault.add(new GuiButton(000, 2, this.tabY, 50, 20, "Back"));
		blDefault.add(ba00 = new GuiButton(001, 2, this.tabY += this.ey, 50, 20, "Main"));
		blDefault.add(ba01 = new GuiButton(002, 2, this.tabY += this.ey, 50, 20, "Gameplay"));
		blDefault.add(ba02 = new GuiButton(003, 2, this.tabY += this.ey, 50, 20, "Overlay"));
		blDefault.add(ba03 = new GuiButton(004, 2, this.tabY += this.ey, 50, 20, "Ingame"));
		blDefault.add(ba04 = new GuiButton(005, 2, this.tabY += this.ey, 50, 20, "Menues"));
		blDefault.add(ba05 = new GuiButton(006, 2, this.tabY += this.ey, 50, 20, "Commands"));		
		blDefault.add(ba06 = new GuiButton(007, 2, this.tabY += this.ey, 50, 20, "NameTags"));
		
		this.x = (int)(this.width/2+10);
		this.y = (int)(this.y + 1.2F*this.sy);
			
		llMain.add(new SvLabel("MSClient", this.x, this.y, 2.0F, true));
		llMain.add(new SvLabel("Coded by MSES and Svelix", this.x, this.y += (int)(1.3F*this.sy), 1.4F, true));
		blMain.add(bb00 = new GuiButton(101, this.x-25, this.y += this.sy, 50, 15, "Youtube"));
		blMain.add(bb01 = new GuiButton(102, this.x-25, this.y += 20, 50, 15, "Twitter"));
		
		llMain.add(new SvLabel("Designed by Kruemelmomster", this.x, this.y += 2*this.sy, 1.4F, true));
		blMain.add(bb02 = new GuiButton(103, this.x-25, this.y += this.sy, 50, 15, "Youtube"));
		blMain.add(bb03 = new GuiButton(104, this.x-25, this.y += 20, 50, 15, "Twitter"));
		
		llMain.add(new SvLabel("Join our Discord now!", this.x, this.y += 2*this.sy, 1.4F, true));
		blMain.add(bb04 = new GuiButton(105, this.x-25, this.y += this.sy, 50, 15, "Discord"));
		
		
		//llMain.add(new SvLabel("Main", scaledX/2-180, scaledY/2-140, true));		
		this.y = 0;
		this.x = 0;
		this.x += this.sx;
		llGameplay.add(new SvLabel("Animations", this.x + 40, this.y += this.sy, true));
		blGameplay.add(bc00 = new GuiButton(201, this.x, this.y += this.sy, 80, 20, "1.7 Rod"));
		blGameplay.add(bc01 = new GuiButton(202, this.x, this.y += this.sy, 80, 20, "1.7 Bow"));
		blGameplay.add(bc02 = new GuiButton(203, this.x, this.y += this.sy, 80, 20, "1.7 BlockBuild"));
		bc02.enabled = true;
		blGameplay.add(bc03 = new GuiButton(204, this.x, this.y += this.sy, 80, 20, "1.7 BlockHit"));
		blGameplay.add(bc04 = new GuiButton(205, this.x, this.y += this.sy, 80, 20, "1.7 Sneak"));
		blGameplay.add(bc14 = new GuiButton(215, this.x, this.y += this.sy, 80, 20, "1.7 Damage"));
		this.y = 0;
		this.x += this.sx;
		llGameplay.add(new SvLabel("Appearance", this.x + 40, this.y += this.sy, true));
		blGameplay.add(bc05 = new GuiButton(206, this.x, this.y += this.sy, 80, 20, "Show My Name"));
		blGameplay.add(bc06 = new GuiButton(207, this.x, this.y += this.sy, 80, 20, "Name Tags"));
		blGameplay.add(bc11 = new GuiButton(212, this.x, this.y += this.sy, 80, 20, "Show MS-Name Tags"));
		blGameplay.add(bc12 = new GuiButton(213, this.x, this.y += this.sy, 80, 20, "Show Inteqt-Name Tags"));
		blGameplay.add(bc13 = new GuiButton(214, this.x, this.y += this.sy, 80, 20, "Show Other-Name Tags"));
		blGameplay.add(bc15 = new GuiButton(801, this.x, this.y += this.sy, 80, 20, "IngameEditor"));
		this.y = 0;
		this.x += this.sx;
		llGameplay.add(new SvLabel("Other", this.x + 40, this.y += this.sy, true));
		blGameplay.add(bc07 = new GuiButton(208, this.x, this.y += this.sy, 80, 20, "MouseDelayFix"));		
		blGameplay.add(bc09 = new GuiButton(210, this.x, this.y += this.sy, 80, 20, "Funny CPS"));
		blGameplay.add(bc10 = new GuiButton(211, this.x, this.y += this.sy, 80, 20, "Funny Reach"));
		this.y = 0;
		this.x = 0;
		this.x += this.sx;
		llOverlay.add(new SvLabel("Tab Menu", this.x + 40, this.y += this.sy, true));
		blOverlay.add(bd00 = new GuiButton(301, this.x, this.y += this.sy, 80, 20, "Clean Tab"));
		blOverlay.add(bd01 = new GuiButton(302, this.x, this.y += this.sy, 80, 20, "Show Ping"));
		this.y = 0;
		this.x += this.sx;
		llOverlay.add(new SvLabel("F3 Menu", this.x + 40, this.y += this.sy, true));
		blOverlay.add(sd00 = new GuiSliderFixed(330, this.x - 10 , this.y += this.sy, "Grösse F3", Variables.debugG/1.5F, 1.5F, 0.4F));
		this.y = 0;
		this.x += this.sx;
		llOverlay.add(new SvLabel("Scoreboard", this.x + 40, this.y += this.sy, true));
		blOverlay.add(bd02 = new GuiButton(303, this.x, this.y += this.sy, 80, 20, "Enabled"));
		
		
		this.y = 0;
		this.x = 0;
		this.x += this.sx;
		llIngame.add(new SvLabel("Ingame-Gui", this.x + 40, this.y += this.sy, true));
		
		blIngame.add(se00 = new GuiSliderFixed(360, this.x - 10, this.y += this.sy, "Grösse", Variables.x/1.5F, 1.5F, 0.4F));
			
		blIngame.add(be00 = new GuiButton(401, this.x, this.y += this.sy, 80, 20, "Show FPS"));
		blIngame.add(be01 = new GuiButton(402, this.x, this.y += this.sy, 80, 20, "Show Coords"));
		blIngame.add(be02 = new GuiButton(403, this.x, this.y += this.sy, 80, 20, "Show CPS"));
		blIngame.add(be03 = new GuiButton(404, this.x, this.y += this.sy, 80, 20, "Show Potion"));
		blIngame.add(be04 = new GuiButton(405, this.x, this.y += this.sy, 80, 20, "Show Armor"));
		blIngame.add(be05 = new GuiButton(406, this.x, this.y += this.sy, 80, 20, "MLG Helper"));
		//blIngame.add(be06 = new GuiButton(407, this.x, this.y += this.sy, 80, 20, "EP Detector"));	
		blIngame.add(be07 = new GuiButton(408, this.x, this.y += this.sy, 80, 20, "Show Memory"));
		this.y = 0;
		
		blIngame.add(be08 = new GuiButton(409, this.x += this.sx, this.y += 2*this.sy, 80, 20, "Show LookingAt"));
		blIngame.add(be09 = new GuiButton(410, this.x, this.y += this.sy, 80, 20, "Display Reach"));
		blIngame.add(be17 = new GuiButton(417, this.x, this.y += this.sy, 80, 20, "Particle Mod"));
		blIngame.add(se02 = new GuiSliderFixed(418, this.x - 10, this.y += this.sy, "Particle Multiplier", Variables.pSize/10F, 10F, 0F));
		this.y = 0;
		this.x += this.sx;
		llIngame.add(new SvLabel("FastMod", this.x + 40, this.y += this.sy, true));
		blIngame.add(be10 = new GuiButton(411, this.x, this.y += this.sy, 80, 20, "Fast Chat"));
		blIngame.add(be11 = new GuiButton(412, this.x, this.y += this.sy, 80, 20, "Fast Scoreboard"));
		blIngame.add(be12 = new GuiButton(413, this.x, this.y += this.sy, 80, 20, "Fast KeyStrokes"));
		blIngame.add(be13 = new GuiButton(414, this.x, this.y += this.sy, 80, 20, "Fast ReachDisplay"));
		this.y = 0;
		this.x += this.sx;
		llIngame.add(new SvLabel("KeyStrokesMod", this.x + 40, this.y += this.sy, true));
		blIngame.add(be14 = new GuiButton(415, this.x, this.y += this.sy, 80, 20, "Show"));
		blIngame.add(be15 = new GuiButton(416, this.x, this.y += this.sy, 80, 20, "Position:"));
		this.y = 0;
		this.x = 0;
		this.x += this.sx;
		llMenues.add(new SvLabel("GuiParticleMod", this.x + 40, this.y += this.sy, true));
		blMenues.add(bf00 = new GuiButton(501, this.x, this.y += this.sy, 80, 20, "Show"));
		blMenues.add(bf01 = new GuiButton(502, this.x, this.y += this.sy, 80, 20, "Mode:"));
		blMenues.add(bf02 = new GuiButton(503, this.x, this.y += this.sy, 80, 20, "Cover Gui"));
		
		blMenues.add(bf10 = new GuiSliderFixed(511, this.x - 10, this.y += this.sy, "Radius", Variables.radius/5, 5F, 0.1F));
		blMenues.add(bf11 = new GuiSliderFixed(512, this.x - 10, this.y += this.sy, "Size", Variables.size/5, 5F, 0.1F));
		blMenues.add(bf12 = new GuiSliderFixed(513, this.x - 10, this.y += this.sy, "Speed", Variables.speed/5, 5F, 0.1F));
		this.y = 0;
		this.x += this.sx;
		llMenues.add(new SvLabel("Color", this.x + 40, this.y += this.sy, true));
		blMenues.add(bf03 = new GuiButton(504, this.x, this.y += this.sy, 80, 20, "Red"));
		blMenues.add(bf04 = new GuiButton(505, this.x, this.y += this.sy, 80, 20, "Yellow"));
		blMenues.add(bf05 = new GuiButton(506, this.x, this.y += this.sy, 80, 20, "Green"));
		blMenues.add(bf06 = new GuiButton(507, this.x, this.y += this.sy, 80, 20, "Blue"));
		blMenues.add(bf07 = new GuiButton(508, this.x, this.y += this.sy, 80, 20, "White"));
		blMenues.add(bf08 = new GuiButton(509, this.x, this.y += this.sy, 80, 20, "Black"));
		blMenues.add(bf09 = new GuiButton(510, this.x, this.y += this.sy, 80, 20, "Rainbow"));
		this.y = 0;
		this.x += this.sx;
		llMenues.add(new SvLabel("Clan Wars", this.x + 40, this.y += this.sy, true));
		blMenues.add(new GuiButton(803, this.x, this.y += this.sy, 80, 20, "Clan War Info"));
		this.y = 0;
		this.x = 0;
		this.x += this.sx;
		llCommands.add(new SvLabel("Key", this.x + 40, this.y += this.sy, true));
		this.x += this.sx;
		llCommands.add(new SvLabel("Command", this.x + 90, this.y, true));
		this.x = 0;
		blCommands.add(bg00 = new GuiButton(601, this.x += this.sx, this.y += this.sy, 80, 20, "Record Key"));
		tlCommands.add(tg00 = new GuiTextField(602, fontRendererObj, this.x += this.sx, this.y , 180, 20));
		tg00.setFocused(false);
		blCommands.add(bg01 = new GuiButton(603, this.x += 100 + this.sx, this.y, 50, 20, "Add"));
		this.y = 0;
		this.x = 0;
		this.x += this.sx;
		llNameTags.add(new SvLabel("PlayerName", this.x + 40, this.y += this.sy, true));
		this.x += this.sx;
		llNameTags.add(new SvLabel("PlayerTag", this.x + 90, this.y , true));
		this.x = 0;
		tlNameTagsName.add(tg01 = new GuiTextField(701, fontRendererObj, this.x += this.sx, this.y += this.sy, 80, 20));
		tg01.setFocused(false);
		tg01.setCanLoseFocus(true);
		tg01.setMaxStringLength(200);
		tlNameTagsTag.add(tg02 = new GuiTextField(702, fontRendererObj, this.x += this.sx, this.y, 180, 20));
		tg02.setFocused(false);
		tg02.setCanLoseFocus(true);		
		blNameTags.add(bn00 = new GuiButton(703, this.x += 100 + this.sx, this.y, 50, 20, "Add"));
		
		delButtons.add(bdel00 = new GuiButton(901, this.x , this.y += this.sy, 50, 20, "Delete"));
		delButtons.add(bdel01 = new GuiButton(902, this.x, this.y += this.sy, 50, 20, "Delete"));
		delButtons.add(bdel02 = new GuiButton(903, this.x, this.y += this.sy, 50, 20, "Delete"));
		delButtons.add(bdel03 = new GuiButton(904, this.x, this.y += this.sy, 50, 20, "Delete"));
		delButtons.add(bdel04 = new GuiButton(905, this.x, this.y += this.sy, 50, 20, "Delete"));
		delButtons.add(bdel05 = new GuiButton(906, this.x, this.y += this.sy, 50, 20, "Delete"));
		delButtons.add(bdel06 = new GuiButton(907, this.x, this.y += this.sy, 50, 20, "Delete"));
		delButtons.add(bdel07 = new GuiButton(908, this.x, this.y += this.sy, 50, 20, "Delete"));
		delButtons.add(bdel08 = new GuiButton(909, this.x, this.y += this.sy, 50, 20, "Delete"));
		delButtons.add(bdel09 = new GuiButton(910, this.x, this.y += this.sy, 50, 20, "Delete"));
		
		loadButtonColors();
		
		buttonList = blMain;
		llCurrent = llMain;
		for (GuiButton b : blDefault) {buttonList.add(b);}
		for (SvLabel l : llDefault) {llCurrent.add(l);}
		
		switch(currentTab) {
		
		case 0:
			buttonList = blMain;
			llCurrent = llMain;
			for (GuiButton b : blDefault) {buttonList.add(b);}
			for (SvLabel l : llDefault) {llCurrent.add(l);}
			break;
		case 1:
			buttonList = blGameplay;
			llCurrent = llGameplay;
			for (GuiButton b : blDefault) {buttonList.add(b);}
			for (SvLabel l : llDefault) {llCurrent.add(l);}
			break;
		case 2:
			buttonList = blOverlay;
			llCurrent = llOverlay;
			for (GuiButton b : blDefault) {buttonList.add(b);}
			for (SvLabel l : llDefault) {llCurrent.add(l);}
			break;
		case 3:
			buttonList = blIngame;
			llCurrent = llIngame;
			for (GuiButton b : blDefault) {buttonList.add(b);}
			for (SvLabel l : llDefault) {llCurrent.add(l);}
			break;
		case 4:
			buttonList = blMenues;
			llCurrent = llMenues;
			for (GuiButton b : blDefault) {buttonList.add(b);}
			for (SvLabel l : llDefault) {llCurrent.add(l);}
			break;
		case 5:
			buttonList = blCommands;
			llCurrent = llCommands;
			for (SvLabel l : llDefault) {llCurrent.add(l);}
			
			resetDelButtons();
			break;
		case 6:			
			buttonList = blNameTags;
			llCurrent = llNameTags;
			for(GuiButton b : blDefault) {buttonList.add(b);}
			for (SvLabel l : llDefault) {llCurrent.add(l);}
			
			resetDelButtons();
			break;
		}
		
		super.initGui();
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		
		//System.out.println(button.id);
		
		if (isRecordingKey) {return;}
		
		switch (button.id) {
		
		case 000:
			Minecraft.getMinecraft().displayGuiScreen(this.parentScreen);
			break;
		case 001:
			if (currentTab == 0) {break;}
			currentTab = 0;
			buttonList = blMain;
			llCurrent = llMain;
			for (GuiButton b : blDefault) {buttonList.add(b);}
			for (SvLabel l : llDefault) {llCurrent.add(l);}
			break;
		case 002:
			if (currentTab == 1) {break;}
			currentTab = 1;
			buttonList = blGameplay;
			llCurrent = llGameplay;
			for (GuiButton b : blDefault) {buttonList.add(b);}
			for (SvLabel l : llDefault) {llCurrent.add(l);}
			break;
		case 003:
			if (currentTab == 2) {break;}
			currentTab = 2;
			buttonList = blOverlay;
			llCurrent = llOverlay;
			for (GuiButton b : blDefault) {buttonList.add(b);}
			for (SvLabel l : llDefault) {llCurrent.add(l);}
			break;
		case 004:
			if (currentTab == 3) {break;}
			currentTab = 3;
			buttonList = blIngame;
			llCurrent = llIngame;
			for (GuiButton b : blDefault) {buttonList.add(b);}
			for (SvLabel l : llDefault) {llCurrent.add(l);}
			break;
		case 005:
			if (currentTab == 4) {break;}
			currentTab = 4;
			buttonList = blMenues;
			llCurrent = llMenues;
			for (GuiButton b : blDefault) {buttonList.add(b);}
			for (SvLabel l : llDefault) {llCurrent.add(l);}
			break;
		case 006:
			if (currentTab == 5) {break;}
			currentTab = 5;
			buttonList = blCommands;
			llCurrent = llCommands;
			for (SvLabel l : llDefault) {llCurrent.add(l);}
			
			resetDelButtons();
			break;
		case 007:
			if (currentTab == 6) {break;}
			currentTab = 6;	
			buttonList = blNameTags;
			llCurrent = llNameTags;
			for(GuiButton b : blDefault) {buttonList.add(b);}
			for (SvLabel l : llDefault) {llCurrent.add(l);}
			
			resetDelButtons();
			break;
			
		case 101:
			main.instance.openWebpage(link_yt_mses, true);
			break;
		case 102:
			main.instance.openWebpage(link_tw_mses, true);
			break;
		case 103:
			main.instance.openWebpage(link_yt_kmom, true);
			break;
		case 104:
			main.instance.openWebpage(link_tw_kmom, true);
			break;
		case 105:
			main.instance.openWebpage(link_discord, true);
			break;
			
		case 201:
			Variables.oldRod = !Variables.oldRod; 
			bc00.displayString = (Variables.oldRod) ? ("§21.7 Rod") : ("§41.7 Rod");
			break;
		case 202:
			Variables.oldBow = !Variables.oldBow; 
			bc01.displayString = (Variables.oldBow) ? ("§21.7 Bow") : ("§41.7 Bow");
			break;
		case 203:
			Variables.oldBlockBuild = !Variables.oldBlockBuild; 
			bc02.displayString = (Variables.oldBlockBuild) ? ("§21.7 BlockBuild") : ("§41.7 BlockBuild");
			break;
		case 204:
			Variables.oldBlockHit = !Variables.oldBlockHit; 
			bc03.displayString = (Variables.oldBlockHit) ? ("§21.7 BlockHit") : ("§41.7 BlockHit");
			break;
		case 205:
			Variables.oldSneak = !Variables.oldSneak; 
			bc04.displayString = (Variables.oldSneak) ? ("§21.7 Sneak") : ("§41.7 Sneak");
			break;
		case 206:
			Variables.showMyName = !Variables.showMyName; 
			bc05.displayString = (Variables.showMyName) ? ("§2Show my Name") : ("§4Show my Name");
			break;
		case 207:
			Variables.nameTags = !Variables.nameTags; 
			bc06.displayString = (Variables.nameTags) ? ("§2Name Tags") : ("§4Name Tags");
			break;
		case 208:
			Variables.mouseDelayFix = !Variables.mouseDelayFix; 
			bc07.displayString = (Variables.mouseDelayFix) ? ("§2MouseDelayFix") : ("§4MouseDelayFix");
			break;
		
		case 210:
			Variables.funnyCPS = !Variables.funnyCPS; 
			bc09.displayString = (Variables.funnyCPS) ? ("§2Funny CPS") : ("§4Funny CPS");
			break;
		case 211:
			Variables.funnyReach = !Variables.funnyReach; 
			bc10.displayString = (Variables.funnyReach) ? ("§2Funny Reach") : ("§4Funny Reach");
			break;
		case 212:
			Variables.showNameMS = !Variables.showNameMS; 
			bc11.displayString = (Variables.showNameMS) ? ("§2Show MS-Name Tags") : ("§4Show MS-Name Tags");
			break;
		case 213:
			Variables.showNameInteqt = !Variables.showNameInteqt; 
			bc12.displayString = (Variables.showNameInteqt) ? ("§2Show Inteqt-Name Tags") : ("§4Show Inteqt-Name Tags");
			break;
		case 214:
			Variables.showNameOther = !Variables.showNameOther; 
			bc13.displayString = (Variables.showNameOther) ? ("§2Show Other-Name Tags") : ("§4Show Other-Name Tags");
			break;
		case 215:
			Variables.oldDmg = !Variables.oldDmg;
			bc14.displayString = (Variables.oldDmg) ? ("§21.7 Damage") : ("§41.7 Damage");
			
		case 301:
			Variables.cleanTab = !Variables.cleanTab;
			bd00.displayString = (Variables.cleanTab) ? ("§2Clean Tab") : ("§4Clean Tab");
			break;
		case 302:
			Variables.pingOnTab = !Variables.pingOnTab;
			bd01.displayString = (Variables.pingOnTab) ? ("§2Show Ping") : ("§4Show Ping");
			break;
		case 303:
			Variables.scoreboard = !Variables.scoreboard;
			bd02.displayString = (Variables.scoreboard) ? ("§2Enabled") : ("§4Disabled");
			break;
			
		case 401:
			Variables.showFPS = !Variables.showFPS;
			be00.displayString = (Variables.showFPS) ? ("§2Show FPS") : ("§4Show FPS");
			break;
		case 402:
			Variables.showCoords = !Variables.showCoords;
			be01.displayString = (Variables.showCoords) ? ("§2Show Coords") : ("§4Show Coords");
			break;
		case 403:
			Variables.CPS = !Variables.CPS;
			be02.displayString = (Variables.CPS) ? ("§2Show CPS") : ("§4Show CPS");
			break;
		case 404:
			Variables.MSPotion = !Variables.MSPotion;
			be03.displayString = (Variables.MSPotion) ? ("§2Show Potion") : ("§4Show Potion");
			break;
		case 405:
			Variables.Armor = !Variables.Armor;
			be04.displayString = (Variables.Armor) ? ("§2Show Armor") : ("§4Show Armor");
			break;
		case 406:
			Variables.MLG = !Variables.MLG;
			be05.displayString = (Variables.MLG) ? ("§2MLG Helper") : ("§4MLG Helper");
			break;
		case 407:
			Variables.EP = !Variables.EP;
			be06.displayString = (Variables.EP) ? ("§2EP Detector") : ("§4EP Detector");
			break;
		case 408:
			Variables.memory = !Variables.memory;
			be07.displayString = (Variables.memory) ? ("§2Show Memory") : ("§4Show Memory");
			break;
		case 409:
			Variables.lookingAt = !Variables.lookingAt;
			be08.displayString = (Variables.lookingAt) ? ("§2Show LookingAt") : ("§4Show LookingAt");
			break;
		case 410:
			Variables.reachDisplay = !Variables.reachDisplay;
			be09.displayString = (Variables.reachDisplay) ? ("§2Display Reach") : ("§4Display Reach");
			break;
		case 411:
			Variables.fastChat = !Variables.fastChat;
			be10.displayString = (Variables.fastChat) ? ("§2Fast Chat") : ("§4Fast Chat");
			break;
		case 412:
			Variables.fastScore = !Variables.fastScore;
			be11.displayString = (Variables.fastScore) ? ("§2Fast Scoreboard") : ("§4Fast Scoreboard");
			break;
		case 413:
			Variables.fastKeyStrokes = !Variables.fastKeyStrokes;
			be12.displayString = (Variables.fastKeyStrokes) ? ("§2Fast KeyStrokes") : ("§4Fast KeyStrokes");
			break;
		case 414:
			Variables.fastReach = !Variables.fastReach;
			be13.displayString = (Variables.fastReach) ? ("§2Fast ReachDisplay") : ("§4Fast ReachDisplay");
			break;
		case 415:
			Variables.keyStrokesMod = !Variables.keyStrokesMod;
			be14.displayString = (Variables.keyStrokesMod) ? ("§2Show") : ("§4Show");
			break;
		case 416:
			if (Variables.location < 4) {Variables.location++;} else {Variables.location = 0;}
			
			String s = "Position: ";
			switch (Variables.location) {
			case 0:
				s += "Links Oben";
				break;
			case 1:
				s += "Rechts Oben";
				break;
			case 2:
				s += "Links Unten";
				break;
			case 3:
				s += "Rechts Unten";
				break;
			case 4:
				s += "Oben Mitte";
				break;
			}
			be15.displayString = s;
			break;
		case 417:
			Variables.particleMod = !Variables.particleMod;
			be17.displayString = (Variables.particleMod) ? ("§2Particle Mod") : ("§4Particle Mod");
			break;
			
		case 501:
			Variables.particle = !Variables.particle;
			bf00.displayString = (Variables.particle) ? ("§2Show") : ("§4Show");
			break;		
		case 503:
			Variables.coverParticles = !Variables.coverParticles;
			bf02.displayString = (Variables.coverParticles) ? ("§4Cover Gui") : ("§2Cover Gui");
			break;
		case 504:
			Variables.MSColor = Color.RED.getRGB();
			Variables.rainBow = false;
			break;
		case 505:
			Variables.MSColor = Color.YELLOW.getRGB();
			Variables.rainBow = false;
			break;
		case 506:
			Variables.MSColor = Color.GREEN.getRGB();
			Variables.rainBow = false;
			break;
		case 507:
			Variables.MSColor = Color.BLUE.getRGB();
			Variables.rainBow = false;
			break;
		case 508:
			Variables.MSColor = Color.WHITE.getRGB();
			Variables.rainBow = false;
			break;
		case 509:
			Variables.MSColor = Color.BLACK.getRGB();
			Variables.rainBow = false;
			break;
		case 510:
			Variables.rainBow = true;
			break;
		case 512:
			SvParticleGen.particles.clear();
			SvParticleGen.particles.add(new SvAutismParticle(Variables.size));
			break;
		case 513:
			SvParticleGen.particles.clear();
			SvParticleGen.particles.add(new SvAutismParticle(Variables.size));
			break;
			
		case 601:
			bg00.displayString = "§4" + bg00.displayString;
			isRecordingKey = true;
			break;
		case 603:
			if (currKey != -1 && tg00.getText().trim() != "") {
				//Variables.msBindings.add(new KeyBinding("MS_" + tg00.getText().trim(), currKey, "key.category.ms"));
				ToggleSprintManager.modules.add(new ToggleSprintMod(tg00.getText().trim(), currKey) {
					public void onEnable() {
						Minecraft.getMinecraft().thePlayer.sendChatMessage(this.getName());
					}
					public void onDisable() {
						Minecraft.getMinecraft().thePlayer.sendChatMessage(this.getName());
					}
				});
				bg00.displayString = "Record Key";
				currKey = -1;
				resetDelButtons();
				tg00.setText("");				
			}
			break;		
		case 703: 
			if(tg01.getText().length() > 1 && tg02.getText().length() > 0) {
				NameTags.otherTags.add(new Tag(tg01.getText(), tg02.getText()));
			}
			resetDelButtons();
			tg01.setText("");	
			tg02.setText("");
			break;	
			
		case 801:
			currentTab = 0;
			Minecraft.getMinecraft().displayGuiScreen(new SVGuiPos(this));
			break;
		case 802:
			Minecraft.getMinecraft().displayGuiScreen(new GuiCredits(this));
			break;
		case 803:
			Minecraft.getMinecraft().displayGuiScreen(new CWGui(this));
			break;		
		case 901:			
			//System.out.println("Vorher: " + ToggleSprintManager.modules.size());
			if(currentTab == 5) {
				if (ToggleSprintManager.modules.size() > 0) {ToggleSprintManager.modules.remove(0); bdel00.visible = false;}
			}else if(currentTab == 6) {
				if(NameTags.otherTags.size() > 0) {NameTags.otherTags.remove(0);bdel00.visible = false;}
			}
			//System.out.println("Nachher: " + ToggleSprintManager.modules.size());
			break;
		case 902:
			if(currentTab == 5) {
				if (ToggleSprintManager.modules.size() > 1) {ToggleSprintManager.modules.remove(1); bdel01.visible = false;}
			}else if(currentTab == 6) {
				if(NameTags.otherTags.size() > 1) {NameTags.otherTags.remove(1);bdel01.visible = false;}
			}
			break;
		case 903:
			if(currentTab == 5) {
				if (ToggleSprintManager.modules.size() > 2) {ToggleSprintManager.modules.remove(2); bdel02.visible = false;}
			}else if(currentTab == 6) {
				if(NameTags.otherTags.size() > 2) {NameTags.otherTags.remove(2);bdel02.visible = false;}
			}
			break;
		case 904:
			if(currentTab == 5) {
				if (ToggleSprintManager.modules.size() > 3) {ToggleSprintManager.modules.remove(3);bdel03.visible = false;}
			}else if(currentTab == 6) {
				if(NameTags.otherTags.size() > 3) {NameTags.otherTags.remove(3);bdel03.visible = false;}
			}
			break;
		case 905:
			if(currentTab == 5) {
				if (ToggleSprintManager.modules.size() > 4) {ToggleSprintManager.modules.remove(4);bdel04.visible = false;}
			}else if(currentTab == 6) {
				if(NameTags.otherTags.size() > 4) {NameTags.otherTags.remove(4);bdel04.visible = false;}
			}
			break;
		case 906:
			if(currentTab == 5) {
				if (ToggleSprintManager.modules.size() > 5) {ToggleSprintManager.modules.remove(5);bdel05.visible = false;}
			}else if(currentTab == 6) {
				if(NameTags.otherTags.size() > 5) {NameTags.otherTags.remove(5);bdel05.visible = false;}
			}
			break;
		case 907:
			if(currentTab == 5) {
				if (ToggleSprintManager.modules.size() > 6) {ToggleSprintManager.modules.remove(6);bdel06.visible = false;}
			}else if(currentTab == 6) {
				if(NameTags.otherTags.size() > 6) {NameTags.otherTags.remove(6);bdel06.visible = false;}
			}
			break;
		case 908:
			if(currentTab == 5) {
				if (ToggleSprintManager.modules.size() > 7) {ToggleSprintManager.modules.remove(7);bdel07.visible = false;}
			}else if(currentTab == 6) {
				if(NameTags.otherTags.size() > 7) {NameTags.otherTags.remove(7);bdel07.visible = false;}
			}
			break;
		case 909:
			if(currentTab == 5) {
				if (ToggleSprintManager.modules.size() > 8) {ToggleSprintManager.modules.remove(8);bdel08.visible = false;}
			}else if(currentTab == 6) {
				if(NameTags.otherTags.size() > 8) {NameTags.otherTags.remove(8);bdel08.visible = false;}
			}
			break;
		case 910:
			if(currentTab == 5) {
				if (ToggleSprintManager.modules.size() > 9) {ToggleSprintManager.modules.remove(9);bdel09.visible = false;}
			}else if(currentTab == 6) {
				if(NameTags.otherTags.size() > 9) {NameTags.otherTags.remove(9);bdel09.visible = false;}
			}
			break;
		default:
			break;
		}
		this.mc.gameSettings.saveMSOptions();
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		
		if (isRecordingKey) {
			currKey = keyCode;
			isRecordingKey = false;
			bg00.displayString = Keyboard.getKeyName(keyCode);
			return;
		}
		
		if (keyCode == Keyboard.KEY_ESCAPE) {
			this.mc.displayGuiScreen(this.parentScreen);
		}
		
		if (keyCode == Keyboard.KEY_RETURN && currentTab == 5) {
			if (currKey != -1 && tg00.getText().trim() != "") {
				ToggleSprintManager.modules.add(new ToggleSprintMod(tg00.getText().trim(), currKey) {
					public void onEnable() {
						Minecraft.getMinecraft().thePlayer.sendChatMessage(this.getName());
					}
					public void onDisable() {
						Minecraft.getMinecraft().thePlayer.sendChatMessage(this.getName());
					}
				});
				bg00.displayString = "Record Key";
				currKey = -1;
				resetDelButtons();	
				
				tg00.setText("");
			}
		}
		
		if(keyCode == Keyboard.KEY_RETURN && currentTab == 6) {
			if(tg01.getText().length() > 1 && tg02.getText() != null) {
				NameTags.otherTags.add(new Tag(tg01.getText(), tg02.getText()));
				resetDelButtons();	
				
				tg01.setText("");
				tg02.setText("");
			}
		}
		
		tg00.textboxKeyTyped(typedChar, keyCode);
		tg01.textboxKeyTyped(typedChar, keyCode);
		tg02.textboxKeyTyped(typedChar, keyCode);
		
		super.keyTyped(typedChar, keyCode);
	}
	
	@Override
	public void updateScreen() {
		tg00.updateCursorCounter();
		tg01.updateCursorCounter();
		tg02.updateCursorCounter();
		
		super.updateScreen();
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {		
		
		if(currentTab == 5) {
			tg00.mouseClicked(mouseX, mouseY, mouseButton);
		}
		if(currentTab == 6) {
			tg01.mouseClicked(mouseX, mouseY, mouseButton);
			tg02.mouseClicked(mouseX, mouseY, mouseButton);
		}				
		
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
						
		drawDefaultBackground();
		if(currentTab == 5) {
			for(int i = 0; i < 10; i++) {
				if(i<ToggleSprintManager.modules.size()) {
					delButtons.get(i).visible = true;
				}else {
					delButtons.get(i).visible = false;
				}
			}
		}else if(currentTab == 6) {
			for(int i = 0; i < 10; i++) {
				if(i<NameTags.otherTags.size()) {
					delButtons.get(i).visible = true;
				}else {
					delButtons.get(i).visible = false;
				}
			}
		}
		
		
		Variables.radius = (Float)bf10.sliderValue*5;		
		
		Variables.size = (Float)bf11.sliderValue*5;
		
		Variables.speed = (Float)bf12.sliderValue*5;
		
		Variables.pSize = (int)(se02.sliderValue*10);		
				
		this.drawCenteredString(this.fontRendererObj, main.Client_Name + " " + main.Client_Version, width/2, height, Color.WHITE.getRGB());
		
		Variables.x = (float) (se00.sliderValue*1.5);
		Variables.debugG = (float) (sd00.sliderValue*1.5);
		
		for (SvLabel l : llCurrent) {
			if(l.fac != 0) {
				GL11.glPushMatrix();
				GL11.glScalef(l.fac, l.fac, l.fac);
				if (l.isCentered) {
					this.drawCenteredString(fontRendererObj, l.s, (int)(l.x/l.fac), (int)(l.y/l.fac), Variables.MSColor);
				} else {
					this.drawString(fontRendererObj, l.s, (int)(l.x/l.fac), (int)(l.y/l.fac), Variables.MSColor);
				}
				GL11.glPopMatrix();
			}else {			
				if (l.isCentered) {
					this.drawCenteredString(fontRendererObj, l.s, l.x, l.y, Variables.MSColor);
				} else {
					this.drawString(fontRendererObj, l.s, l.x, l.y, Variables.MSColor);
				}
			}
		}
		
		if(currentTab == 4) {
			if(bf11.dragging) {
				SvParticleGen.particles.clear();
				SvParticleGen.particles.add(new SvAutismParticle(Variables.size));
			}
			if(bf12.dragging) {
				SvParticleGen.particles.clear();
				SvParticleGen.particles.add(new SvAutismParticle(Variables.size));
			}
		}
		
		if (currentTab == 5) {
			for (GuiTextField t : tlCommands) {t.drawTextBox();}
			this.x = 0;
			this.y = 0;
			for (int i = 0; i < ToggleSprintManager.modules.size(); i++) {
				this.drawCenteredString(fontRendererObj, Keyboard.getKeyName(ToggleSprintManager.modules.get(i).getBind()), 
						this.x + this.sx + 40, this.y + this.sy * 3 + 5 + (i*this.sy), Variables.MSColor);
				this.drawCenteredString(fontRendererObj, ToggleSprintManager.modules.get(i).getName(), 
						this.x + 2 * this.sx + 90, this.y + this.sy * 3 + 5 + (i*this.sy), Variables.MSColor);
			}
		}
		
		if(currentTab == 6) {
			for(GuiTextField t : tlNameTagsName) {t.drawTextBox();}
			for(GuiTextField t : tlNameTagsTag) {t.drawTextBox();}
			this.x = 0;
			this.y = 0;
			for(int i = 0; i < NameTags.otherTags.size(); i++) {
				this.drawCenteredString(fontRendererObj, NameTags.otherTags.get(i).getUUID(), 
						this.x + this.sx + 40, this.y + this.sy * 3 + 5 + (i*this.sy), Variables.MSColor);
				this.drawCenteredString(fontRendererObj, NameTags.otherTags.get(i).getTag(), 
						this.x + 2 * this.sx + 90, this.y + this.sy * 3 + 5 + (i*this.sy), Variables.MSColor);
			}
		}
		
		
		this.mc.gameSettings.saveMSOptions();
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	
	public static void loadButtonColors() {
		bc00.displayString = (Variables.oldRod) ? ("§21.7 Rod") : ("§41.7 Rod");
		bc01.displayString = (Variables.oldBow) ? ("§21.7 Bow") : ("§41.7 Bow");
		bc02.displayString = (Variables.oldBlockBuild) ? ("§21.7 BlockBuild") : ("§41.7 BlockBuild");
		bc03.displayString = (Variables.oldBlockHit) ? ("§21.7 BlockHit") : ("§41.7 BlockHit");
		bc04.displayString = (Variables.oldSneak) ? ("§21.7 Sneak") : ("§41.7 Sneak");
		bc05.displayString = (Variables.showMyName) ? ("§2Show my Name") : ("§4Show my Name");
		bc06.displayString = (Variables.nameTags) ? ("§2Name Tags") : ("§4Name Tags");
		bc07.displayString = (Variables.mouseDelayFix) ? ("§2MouseDelayFix") : ("§4MouseDelayFix");		
		bc09.displayString = (Variables.funnyCPS) ? ("§2Funny CPS") : ("§4Funny CPS");
		bc10.displayString = (Variables.funnyReach) ? ("§2Funny Reach") : ("§4Funny Reach");
		bc11.displayString = (Variables.showNameMS) ? ("§2Show MS-Name Tags") : ("§4Show MS-Name Tags");
		bc12.displayString = (Variables.showNameInteqt) ? ("§2Show Inteqt-Name Tags") : ("§4Show Inteqt-Name Tags");
		bc13.displayString = (Variables.showNameOther) ? ("§2Show Other-Name Tags") : ("§4Show Other-Name Tags");		
		bc14.displayString = (Variables.oldDmg) ? ("§21.7 Damage") : ("§41.7 Damage");
		
		bd00.displayString = (Variables.cleanTab) ? ("§2Clean Tab") : ("§4Clean Tab");
		bd01.displayString = (Variables.pingOnTab) ? ("§2Show Ping") : ("§4Show Ping");
		bd02.displayString = (Variables.scoreboard) ? ("§2Enabled") : ("§4Disabled");
		
		be00.displayString = (Variables.showFPS) ? ("§2Show FPS") : ("§4Show FPS");
		be01.displayString = (Variables.showCoords) ? ("§2Show Coords") : ("§4Show Coords");
		be02.displayString = (Variables.CPS) ? ("§2Show CPS") : ("§4Show CPS");
		be03.displayString = (Variables.MSPotion) ? ("§2Show Potion") : ("§4Show Potion");
		be04.displayString = (Variables.Armor) ? ("§2Show Armor") : ("§4Show Armor");
		be05.displayString = (Variables.MLG) ? ("§2MLG Helper") : ("§4MLG Helper");
		//be06.displayString = (Variables.EP) ? ("§2EP Detector") : ("§4EP Detector");
		be07.displayString = (Variables.memory) ? ("§2Show Memory") : ("§4Show Memory");
		be08.displayString = (Variables.lookingAt) ? ("§2Show LookingAt") : ("§4Show LookingAt");
		be09.displayString = (Variables.reachDisplay) ? ("§2Display Reach") : ("§4Display Reach");
		be10.displayString = (Variables.fastChat) ? ("§2Fast Chat") : ("§4Fast Chat");
		be11.displayString = (Variables.fastScore) ? ("§2Fast Scoreboard") : ("§4Fast Scoreboard");
		be12.displayString = (Variables.fastKeyStrokes) ? ("§2Fast KeyStrokes") : ("§4Fast KeyStrokes");
		be13.displayString = (Variables.fastReach) ? ("§2Fast ReachDisplay") : ("§4Fast ReachDisplay");
		be14.displayString = (Variables.keyStrokesMod) ? ("§2Show") : ("§4Show");
		String s = "Position: ";
		switch (Variables.location) {
		case 0:
			s += "Links Oben";
			break;
		case 1:
			s += "Rechts Oben";
			break;
		case 2:
			s += "Links Unten";
			break;
		case 3:
			s += "Rechts Unten";
			break;
		case 4:
			s += "Oben Mitte";
			break;
		}
		be15.displayString = s;
		be17.displayString = (Variables.particleMod) ? ("§2Particle Mod") : ("§4Particle Mod");
		
		bf00.displayString = (Variables.particle) ? ("§2Show") : ("§4Show");
		bf01.displayString = (Variables.oldParticles) ? ("Mode: Standart") : ("Mode: Custom");
		bf02.displayString = (Variables.coverParticles) ? ("§4Cover Gui") : ("§2Cover Gui");
	}	
	
	private void resetDelButtons() {
		if(currentTab == 5) { this.buttonList = blCommands;}
		if(currentTab == 7) { this.buttonList = blNameTags;}
		
		for (GuiButton b : blDefault) {this.buttonList.add(b);}
		
		if (!ToggleSprintManager.modules.isEmpty() && currentTab == 5) {
			if (ToggleSprintManager.modules.size() > 10) {
				for (GuiButton b : delButtons) {this.buttonList.add(b);}
			} else {
				for (GuiButton b : delButtons.subList(0, ToggleSprintManager.modules.size())) {this.buttonList.add(b);}
			}
		}
		if(!NameTags.otherTags.isEmpty() && currentTab == 6) {
			if(NameTags.otherTags.size() > 10) {
				for(GuiButton b : delButtons) {this.buttonList.add(b);}
			}else {
				for(GuiButton b : delButtons.subList(0, NameTags.otherTags.size())) {this.buttonList.add(b);}
			}
		}
	}	
	
	private class SvLabel {
		String s;
		int x, y;
		float fac = 0;
		boolean isCentered;
		
		public SvLabel (String s, int x, int y, float fac, boolean isCentered) {
			this.s = s;
			this.x = x; this.y = y;
			this.fac = fac;
			this.isCentered = isCentered;
		}
		
		public SvLabel (String s, int x, int y, boolean isCentered) {
			this.s = s;
			this.x = x; this.y = y;
			this.isCentered = isCentered;
		}
	}
}
