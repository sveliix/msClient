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

import org.apache.commons.io.IOUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import msClient.ParticleAPI.Client.Particle.ParticleGen;
import msClient.config.Variables;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
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
		
		@Override
		public void initGui() {
			buttonList.add(new GuiButton(01, 2, 2, 50, 20, "Back"));
			
			super.initGui();
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
			switch (button.id)
	        {
	        case 01: mc.displayGuiScreen(this.parentScreen);break;
	        case 02: mc.thePlayer.sendChatMessage("/cw jump " + cws.get(0));break;
	        case 03: mc.thePlayer.sendChatMessage("/cw jump " + cws.get(1));break;
	        case 04: mc.thePlayer.sendChatMessage("/cw jump " + cws.get(2));break;
	        case 05: mc.thePlayer.sendChatMessage("/cw jump " + cws.get(3));break;
	        case 06: mc.thePlayer.sendChatMessage("/cw jump " + cws.get(4));break;
	        
	        }
		}
		
		@Override
		public void updateScreen() {
		
			super.updateScreen();
		}
		
		
		@Override
		public void drawScreen(int mouseX, int mouseY, float partialTicks) {
			
			drawDefaultBackground();
			
			try {					
				TestCW(1, null, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			/*try {					
				TestCW(2, null, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			t = cws.get(0);
    		//System.out.println(t);
			try {					
				TestCW(4, t, null);
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			
			
			
			//drawTeamSpeak();
			
			int o = width;
			int j = height;
			
			
			
			//drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 1920, 1080, 100, 20);
			//System.out.println(cws.size());
			/*for(int k=1;k<=count;k++) {
				if(jj >= height-40) {
	        		jj1 -= 100;
	        		jj = 0;
	        	}
				this.buttonList.add(new GuiButton(k+1, width/2-jj1, jj+20, 10, 10, "->"));
				jj+=40;
			}*/
			
			super.drawScreen(mouseX, mouseY, partialTicks);
		}
		
		
	    static String t;
	    
	    
	    /*public static String fetchContents(String url, Map<String, String> properties)
	    {
	      try
	      {
	        URL url1 = new URL(url);
	        HttpURLConnection connection = (HttpURLConnection)url1.openConnection();
	        for (Map.Entry<String, String> entry : properties.entrySet()) {
	          connection.setRequestProperty((String)entry.getKey(), (String)entry.getValue());
	        }
	        if (connection.getResponseCode() == 404) {
	          return null;
	        }
	        return IOUtils.toString(connection.getInputStream(), "UTF-8");
	      }
	      catch (Exception localException) {}
	      return null;
	    }
	    
	    public static String fetchContents(String url)
	    {
	      return fetchContents(url, new HashMap());
	    }
	    
	    public static JsonElement fetchJsonContent(String url)
	    {
	      String content = fetchContents(url);
	      if (content == null) {
	        return new JsonObject();
	      }
	      if (content.contains("<code>")) {
	        content = content.substring(content.indexOf("<code>") + 7);
	      }
	      if (content.contains("</code>")) {
	        content = content.substring(0, content.indexOf("</code>") - 1);
	      }
	      JsonParser parser = new JsonParser();
	      return parser.parse(content).getAsJsonObject();
	    }
	    
	    private static Pattern PLAYERS_NAME = Pattern.compile("[a-zA-Z0-9_]{3,16} </td>");
	    private static Pattern PLAYERS_CLAN = Pattern.compile("<a href=\"/clan-profile\\?name=.{3,16}\" style=\"color: #[0-9a-fA-F]{6}\"><strong style=\"font-size: 18px;\">.{3,16}</strong></a>");
	    
	    public static Map<String, List<String>> convertWebsitePlayersData(String html)
	    {
	      String[] data = html.split("\n");
	      
	      Map<String, List<String>> teamPlayerMap = new HashMap();
	      String clan = null;
	      for (String s : data)
	      {
	        if (PLAYERS_NAME.matcher(s).matches())
	        {
	          if ((clan != null) && (!teamPlayerMap.containsKey(clan))) {
	            teamPlayerMap.put(clan, new ArrayList());
	          }
	          ((List)teamPlayerMap.get(clan)).add(s.replaceAll(" </td>", ""));
	        }
	        if (PLAYERS_CLAN.matcher(s).matches()) {
	          clan = s.replaceFirst("<a href=\"/clan-profile\\?name=.{3,16}\" style=\"color: #[0-9a-fA-F]{6}\"><strong style=\"font-size: 18px;\">", "").replaceFirst("</strong></a>", "");
	        }
	      }
	      return teamPlayerMap;
	    }*/
		
		public static ArrayList<String>  TestCW(int method, String ID, String clan) throws IOException {
			
			
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
		        	//if(Arrays.toString(t).contains(">")) {
		        		//Minecraft.getMinecraft().thePlayer.sendChatMessage(Arrays.toString(t));
		        	//}
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
		        		Minecraft.getMinecraft().fontRendererObj.drawString(i + ".)", Math.round((width/2-j1)/0.6F), Math.round((20+j)/0.6F), Color.RED.getRGB());
		        		GL11.glPopMatrix();
		        		j+=8;
		        		i++;
		        		GL11.glPushMatrix();
		        		GL11.glScalef(0.6F, 0.6F, 0.6F);
		        		Minecraft.getMinecraft().fontRendererObj.drawString(s10, Math.round(((width/2-k-5)-j1)/0.6F), Math.round((20+j)/0.6F), Color.BLUE.getRGB());
		        		GL11.glPopMatrix();
		        		cwList.add(s10);
		        		j+=8;
		        		//Minecraft.getMinecraft().thePlayer.sendChatMessage("&l" + s6 + " vs " + s5);
		        	}
		        	}
		        	if(s4 != null && !s4.isEmpty() && !s4.endsWith(">") && !s4.contains("Matchpage") && !s4.contains("<")) {
		        		GL11.glPushMatrix();
		        		GL11.glScalef(0.6F, 0.6F, 0.6F);
		        		Minecraft.getMinecraft().fontRendererObj.drawString(s4, Math.round(((width/2-20)-j1)/0.6F), Math.round((20+j)/0.6F), Color.GRAY.getRGB());
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
		}
	}


