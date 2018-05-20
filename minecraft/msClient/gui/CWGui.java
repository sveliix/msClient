package msClient.gui;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import org.apache.commons.io.IOUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import msClient.ParticleAPI.Client.Particle.ParticleGen;
import msClient.config.Vbs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ChatComponentText;

public class CWGui extends GuiScreen {
	
	private GuiScreen parentScreen;
	
	static int count = 0;
	
    static int jj = 0;
    static int jj1 = 250;
    static List<String> cws = Lists.newArrayList(new String[] {  });
    static List<String> cwList = Lists.newArrayList(new String[] { });
    
    private ParticleGen particleGen = new ParticleGen(50);
    
    public CWGui(GuiScreen parentScreen) {
    	this.parentScreen = parentScreen;
    }
    
    	ScaledResolution s = new ScaledResolution(Minecraft.getMinecraft());
		
		@Override
		public void initGui() {
			buttonList.add(new GuiButton(01, 2, 2, 50, 20, "Back"));
			buttonList.add(new GuiButton(02, s.getScaledWidth()-52, 2, 50, 20, "Refresh"));
			
			refreshCWs();
			
			draw();
			
			super.initGui();
		}
		
		public void draw() {
			int x = 10;
			int y = 30;
			int id = 10;
			for(String t : cwList) {
				if((y > s.getScaledHeight()-40 ) && t.startsWith(" ")) {
					x += 100;
					y = 30;
				}
				if(t.endsWith(".)")){
					buttonList.add(new GuiButton(id, x+40, y-5, 10, 10, "->", false));
					id++;
				}
				GL11.glPushMatrix();
				GL11.glScalef(0.6F, 0.6F, 0.6F);
				Minecraft.getMinecraft().fontRendererObj.drawString("" + t, Math.round(x/0.6F), Math.round(y/0.6F), Color.WHITE.getRGB());
				GL11.glPopMatrix();
				y += 6;
			}
		}
		
		@Override
		protected void keyTyped(char typedChar, int keyCode) throws IOException {
			if (keyCode == Keyboard.KEY_ESCAPE) {
				Minecraft.getMinecraft().displayGuiScreen(this);
				return;
			}
			
			super.keyTyped(typedChar, keyCode);
		}
		
		
		@Override
		protected void actionPerformed(GuiButton button) throws IOException {
			
			for(int j = 0;j < cws.size(); j++) {
	        	if(button.id > 9) {	      	        		
	        		try {
	    				if(mc.theWorld.isRemote) {	    					
	    	        		if(mc.thePlayer != null && mc.getCurrentServerData() != null && mc.getCurrentServerData().serverMOTD != null && mc.getCurrentServerData().serverIP != null && mc.getCurrentServerData().serverName != null 
	    	        				&& Minecraft.getMinecraft().getCurrentServerData().serverMOTD.toLowerCase().contains("gommehd") || Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("gomme") || Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("Gomme") || Minecraft.getMinecraft().getCurrentServerData().serverName.equalsIgnoreCase("GommeHD")) {
	    	        			mc.thePlayer.sendChatMessage("/cw jump " + cws.get(button.id-10));
	    	        		}
	    	        		break;
	    				}
	    			}catch(Exception e) {
	    				
	    			}	    			
	        	}
	        }
			
			switch (button.id)
	        {
	        case 01: mc.displayGuiScreen(this.parentScreen);break;
	        case 02: this.buttonList.clear(); initGui(); break;	        	       	        
	        
	        }
		}
		
		@Override
		public void updateScreen() {
		
			super.updateScreen();
		}
		
		public void refreshCWs() {
			try {					
				TestCW(1, null, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			int q1 = 0;
			for(String q : cws) {
				//buttonList.add(new GuiButton(q1 + 100, eventButton, eventButton, eventButton, eventButton, q));
				q1++;
			}
			
		}
		
		@Override
		public void drawScreen(int mouseX, int mouseY, float partialTicks) {
			
			drawDefaultBackground();
			int x = 10;
			int y = 30;
			int id = 10;			
						
			for(String t : cwList) {
				if((y > s.getScaledHeight()-40 ) && t.startsWith(" ")) {
					x += 100;
					y = 30;
				}
				if(t.endsWith(".)")){
					//buttonList.add(new GuiButton(id, x-7, y, 10, 10, "Jump"));
					id++;
				}
				GL11.glPushMatrix();
				GL11.glScalef(0.6F, 0.6F, 0.6F);
				Minecraft.getMinecraft().fontRendererObj.drawString("" + t, Math.round(x/0.6F), Math.round(y/0.6F), Color.WHITE.getRGB());
				GL11.glPopMatrix();
				y += 6;
			}			
			
			int o = width;
			int j = height;											
			
			super.drawScreen(mouseX, mouseY, partialTicks);
		}
		
		
	    static String t;
	    
	   
		public ArrayList<String>  TestCW(int method, String ID, String clan) throws IOException {
			
			
			cws.clear();
			cwList.clear();
			
			URL url = null;
			
			try
		      {
		        switch (method)
		        {
		        case 1: 
		          //ClanwarSystem.CW.clear();
		          url = new URL("https://www.gommehd.net/clans/get-matches");
		          break;
		        case 2: 
		          url = new URL("https://www.gommehd.net/clans/get-matches");
		          break;
		        case 3: 
		          url = new URL("https://www.gommehd.net/clan-match/get-players?matchId=" + ID);
		          break;
		        case 4: 
		          
		          //url = new URL("https://www.gommehd.net/clan-match/get-players?matchId=%id%");
		          url = new URL("https://www.gommehd.net/clan-match/get-players?matchId=%id%".replace("%id%", ID));
		          
		          //System.out.println(convertWebsitePlayersData(fetchContents("https://www.gommehd.net/clan-match/get-players?matchId=%id%".replace("%id%", ID), new HashMap() {})));
		          
		        }
		        URLConnection connection = url.openConnection();
		        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		        try
		        {
		          connection.connect();
		        }
		        catch (ConnectException e)
		        {
		          Minecraft.getMinecraft().thePlayer.sendChatMessage("�cDu bist nicht mit dem Internet verbunden!");
		        }
		        BufferedReader r = null;
		        try
		        {
		        	
		          r = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
		        }
		        catch (FileNotFoundException e)
		        {
		        	Minecraft.getMinecraft().thePlayer.sendChatMessage("�cBitte achte auf Gro�- und Kleinschreibung!");
		        }
		        String s;
		        int j = 0;
		        int j1 = 200;
		        int i = 1;
		        
		        while((s = r.readLine()) != null) {
		        	if(method == 1) {		        	
		        		
		        		int b2 = 0;
			        	int b3 = 0;
			        	int i4 = 0;
			        	String b4 = null;
			        	String b5 = null;
			        	String b6;
		        	
		        		if(s.contains("/clan-match?id=")) {
		        			
		        			b3 = s.indexOf('"');
		        			b4 = s.substring(b3+1);
		        			i4 = b4.indexOf("?");
		        			b2 = b4.indexOf('"');
		        			b5 = b4.substring(i4+4,b2);
		        			
		        		}
		        		//main.addChatMessage(s);
		        		if(b5 != null) {
		        			cws.add(b5);
		        		}		        				        	
		        		
		        		int s2 = 0;
			        	int s3 = 0;
			        	String s4 = null;
			        	String s5;
			        	String s6;
			        	if(s.contains("<") && s.contains(">")) {
			        		s2 = s.lastIndexOf("<");
			        		//System.out.println(s2);
			    		
			    			s3 = s.indexOf(">");
			    			//System.out.println(s3);
			    			if(s2 < s3) {
			    				s4 = s.substring(s3);
			    			}else {
			    				s4 = s.substring(s3+1, s2);
			    			}
			        	
			    			if(s4.contains("?name=")) {
			    			
			    				int t = s4.indexOf("?name=");
			    				int t1 = s4.lastIndexOf("\"");
			    				s5 = s4.substring(t+6, t1);
			    				int t2 = s4.indexOf("<");
			    				s6 = s4.substring(0, t2);
			    				String s10 = s6 + " vs " + s5;
			    				int k = s10.length();
			    				GL11.glPushMatrix();
			    				GL11.glScalef(0.6F, 0.6F, 0.6F);
			    				//Minecraft.getMinecraft().fontRendererObj.drawString(i + ".)", Math.round((width/2-j1)/0.6F), Math.round((20+j)/0.6F), Color.RED.getRGB());
			    				cwList.add(" ");
			    				cwList.add(i + ".)");
			    				GL11.glPopMatrix();
			    				j+=8;
			    				i++;
			    				GL11.glPushMatrix();
			    				GL11.glScalef(0.6F, 0.6F, 0.6F);
			    				//Minecraft.getMinecraft().fontRendererObj.drawString(s10, Math.round(((width/2-k-5)-j1)/0.6F), Math.round((20+j)/0.6F), Color.BLUE.getRGB());
			    				GL11.glPopMatrix();
			    				cwList.add(s10);
			    				j+=8;
			    				//Minecraft.getMinecraft().thePlayer.sendChatMessage("&l" + s6 + " vs " + s5);
			    			}
			        	}
			        	if(s4 != null && !s4.isEmpty() && !s4.endsWith(">") && !s4.contains("Matchpage") && !s4.contains("<")) {
			        		GL11.glPushMatrix();
			        		GL11.glScalef(0.6F, 0.6F, 0.6F);
			        		//Minecraft.getMinecraft().fontRendererObj.drawString(s4, Math.round(((width/2-20)-j1)/0.6F), Math.round((20+j)/0.6F), Color.GRAY.getRGB());
			        		GL11.glPopMatrix();
			        		cwList.add(s4);
			        		j+=8;		        		
			        	}
		        		
			        }
			        count++;
		        	if(method == 2) {
		        		
		        		int s2 = 0;
			        	int s3 = 0;
			        	int i4 = 0;
			        	String s4 = null;
			        	String s5 = null;
			        	String s6;
		        	
		        		if(s.contains("/clan-match?id=")) {
		        			
		        			s3 = s.indexOf('"');
		        			s4 = s.substring(s3+1);
		        			i4 = s4.indexOf("?");
		        			s2 = s4.indexOf('"');
		        			s5 = s4.substring(i4+4,s2);
		        			
		        		}
		        		//main.addChatMessage(s);
		        		if(s5 != null) {
		        			cws.add(s5);
		        		}
		        		
		        	}
		        	if(method == 4) {
		        		System.out.println("TEst");
		        		System.out.println(s);
		        	}
		        }
		        r.close();
		        
		        /*String s = "<Hallo> Tag <Hallo2>";
	        	
	        	int s2;
	        	int s3;
	        	s2 = s.lastIndexOf("<");
	    		s3 = s.indexOf(">");
	    		String s4 = s.substring(s3+1, s2);
	    		System.out.println(s4);*/
	    		
		      } 
		      catch (MalformedURLException e)
		      {
		        e.printStackTrace();
		      }
			return null;
			
			/*int s2 = 0;
			        	int s3 = 0;
			        	String s4 = null;
			        	String s5;
			        	String s6;
			        	if(s.contains("<") && s.contains(">")) {
			        		s2 = s.lastIndexOf("<");
			        		//System.out.println(s2);
			    		
			    			s3 = s.indexOf(">");
			    			//System.out.println(s3);
			    			if(s2 < s3) {
			    				s4 = s.substring(s3);
			    			}else {
			    				s4 = s.substring(s3+1, s2);
			    			}
			        	
			    			if(s4.contains("?name=")) {
			    				if(j >= height-40) {
			    					j1 -= 100;
			    					j = 0;
			    				}
			        		int t = s4.indexOf("?name=");
			        		int t1 = s4.lastIndexOf("\"");
			        		s5 = s4.substring(t+6, t1);
			        		int t2 = s4.indexOf("<");
			        		s6 = s4.substring(0, t2);
			        		String s10 = s6 + " vs " + s5;
			        		int k = s10.length();
			        		GL11.glPushMatrix();
			        		GL11.glScalef(0.6F, 0.6F, 0.6F);
			        		//Minecraft.getMinecraft().fontRendererObj.drawString(i + ".)", Math.round((width/2-j1)/0.6F), Math.round((20+j)/0.6F), Color.RED.getRGB());
			        		GL11.glPopMatrix();
			        		j+=8;
			        		i++;
			        		GL11.glPushMatrix();
			        		GL11.glScalef(0.6F, 0.6F, 0.6F);
			        		//Minecraft.getMinecraft().fontRendererObj.drawString(s10, Math.round(((width/2-k-5)-j1)/0.6F), Math.round((20+j)/0.6F), Color.BLUE.getRGB());
			        		GL11.glPopMatrix();
			        		cwList.add(s10);
			        		j+=8;
			        		//Minecraft.getMinecraft().thePlayer.sendChatMessage("&l" + s6 + " vs " + s5);
			    			}
			        	}
			        	if(s4 != null && !s4.isEmpty() && !s4.endsWith(">") && !s4.contains("Matchpage") && !s4.contains("<")) {
			        		GL11.glPushMatrix();
			        		GL11.glScalef(0.6F, 0.6F, 0.6F);
			        		//Minecraft.getMinecraft().fontRendererObj.drawString(s4, Math.round(((width/2-20)-j1)/0.6F), Math.round((20+j)/0.6F), Color.GRAY.getRGB());
			        		GL11.glPopMatrix();
			        		cwList.add(s4);
			        		j+=8;		        		
			        	}
			        	*/
		}
	}


