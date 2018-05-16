package msClient.command.commands;

import java.awt.Color;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

import msClient.main;

import msClient.command.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ChatComponentText;

public class Cw extends Command{

	static int count = 0;
	static List<String> cws = Lists.newArrayList(new String[] {  });
	static List<String> cws2 = Lists.newArrayList(new String[] {});
	public static ArrayList<ClansByID> cwinfo = new ArrayList<ClansByID>();
	
	private class ClansByID {
		
		
		String id;
		String clan1;
		String clan2;
		String time;
		String map;
		
		public ClansByID(String id, String clan1, String clan2, String time, String map) {
			this.id = id;
			this.clan1 = clan1;
			this.clan2 = clan2;
			this.time = time;
			this.map = map;
		}
		
		public ClansByID() {
			;
		}
		
	}
	
	@Override
	public String getAlias() {
		return "cwjump";
	}

	@Override
	public String getDescription() {
		return "Jump to a cw!";
	}

	@Override
	public String getSyntax() {
		return ".cwjump [Number of the CW]";
	}

	@Override
	public void onCommand(String command, String[] args) throws Exception {
		
		if(args.length<2) {
			try {
				TestCW(2, null, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(Minecraft.getMinecraft().getCurrentServerData().serverMOTD.toLowerCase().contains("gommehd") || Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("gomme") || Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("Gomme") || Minecraft.getMinecraft().getCurrentServerData().serverName.equalsIgnoreCase("GommeHD")) {
				Minecraft.getMinecraft().thePlayer.sendChatMessage(("/cw jump " + (String)cws.get(Integer.valueOf(args[0]).intValue() - 1)));
				System.out.println(Minecraft.getMinecraft().getCurrentServerData().serverMOTD.toLowerCase().contains("gommehd") + " Test");
			}else {
				main.addChatMessage("Du befindest dich nicht auf dem GommeHD.net Server!");
			}
			
		}else {
			main.addChatMessage("Invalid Command Usage!");
			main.addChatMessage(getSyntax());
		}
	}

	public static ArrayList<String>  TestCW(int method, String ID, String clan) throws IOException {
		 
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		
		cws.clear();
		
		int width = sr.getScaledWidth();
		int height = sr.getScaledHeight();
		
		URL url = null;
		
		try
	      {
	        switch (method)
	        {
	        case 1: 
	          url = new URL("https://www.gommehd.net/clans/get-matches");
	          break;
	        case 2: 
	          url = new URL("https://www.gommehd.net/clans/get-matches");
	          break;
	        case 3: 
	          url = new URL("https://www.gommehd.net/clan-match/get-players?matchId=" + ID);
	          break;
	        case 4: 
	          
	          url = new URL("https://www.gommehd.net/clans/get-matches");
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
	        		int s2 = 0;
	        		int s3 = 0;
	        		String s4 = null;
	        		String s5;
	        		String s6;
	        		if(s.contains("<") && s.contains(">")) {
	        			s2 = s.lastIndexOf("<");
	    		
	        			s3 = s.indexOf(">");
	        			if(s2 < s3) {
	        				s4 = s.substring(s3);
	        			} else {
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
	        				GL11.glPopMatrix();
	        				j+=8;
	        				i++;
	        				GL11.glPushMatrix();
	        				GL11.glScalef(0.6F, 0.6F, 0.6F);
	        				GL11.glPopMatrix();
	        				cws2.add(s6);
	        				cws2.add(s5);
	        				j+=8;
	        			}
	        		}
	        		if(s4 != null && !s4.isEmpty() && !s4.endsWith(">") && !s4.contains("Matchpage") && !s4.contains("<")) {
	        			GL11.glPushMatrix();
	        			GL11.glScalef(0.6F, 0.6F, 0.6F);
	        			GL11.glPopMatrix();
	        			cws2.add(s4);
	        			j+=8;
	        		}
	        	}
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
	        		if(s5 != null) {
	        			cws.add(s5);
	        		}
	        	}
	        	count++;
	        }
	        r.close();
	        System.out.println(cwinfo);
	      } 
	      catch (MalformedURLException e)
	      {
	        e.printStackTrace();
	      }
		return null;
	}
}
