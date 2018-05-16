package msClient;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Logger;

import org.lwjgl.opengl.Display;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import msClient.command.CommandManager;
import msClient.utils.DrawUtils;
import msClient.utils.ToggleSprintManager;
import msClient.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.ChatComponentText;

public class main {

	 public static main instance;
	   public static String Client_Name = "MSClient";
	   public static String Client_Version = "v1.0";
	   public static String Client_Coder = "MSES";
	   public static String Client_Prefix = "§6[§2MSClient§7]";
	   public DrawUtils draw;
	   private static CommandManager cmdManager;
	   
	   public static ToggleSprintManager SprintM;
		
		//private static ModuleManager moduleManager;
	   
	   public Logger logger;
	   
	   public File directory;
	   
	   public JsonObject getOnlineClients() {
		   try {
			   URL url = new URL("https://api.planetteamspeak.com/serverstatus/62.104.20.200:10125");			   
			   try(BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))){
				   String inputLine = "";
				   if((inputLine = reader.readLine()) != null) {
					   return new JsonParser().parse(inputLine).getAsJsonObject();
				   }
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
		   return null;
	   }
	   
	   public String getTSClients() {
		   JsonObject object = this.getOnlineClients();
		   JsonObject result = object.getAsJsonObject("result");
		   String t;
		   return t = (result.get("users") + " " + result.get("slots"));
	   }
	   
	   public void startClient()
	   {		   		  
		   this.draw = new DrawUtils();
	     instance = this;
	     
	     cmdManager = new CommandManager();
	     
	     SprintM = new ToggleSprintManager();
	     
	     Display.setTitle(this.Client_Name + " " + this.Client_Version);
	     System.out.println(this.Client_Name + " wurde geladen!");
	     System.out.println("Coded by " + this.Client_Coder + "!");
	     this.directory = new File(Minecraft.getMinecraft().mcDataDir, instance.Client_Name);
	     
	     if (!this.directory.exists()) {
	       this.directory.mkdir();
	     }
	     	
	     	
			
			//moduleManager = new ModuleManager();
	     
	     
	   }
	   
	   public boolean openWebpage(String urlString, boolean request)
	    {
	        try
	        {
	            if (!urlString.toLowerCase().startsWith("https://") && !urlString.toLowerCase().startsWith("http://"))
	            {
	                urlString = "http://" + urlString;
	            }

	            Utils.openWebpage((new URL(urlString)).toURI(), request);
	            return true;
	        }
	        catch (Exception exception)
	        {
	            exception.printStackTrace();
	            return false;
	        }
	    }
	   
	   public static void addChatMessage(String s) {
		 Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§7[§6MSClient§7]§f " + s));  
	   }
	   
	   public static boolean onSendChatMessage(String s) {
		   if(s.startsWith(".")) {
			   cmdManager.callCommand(s.substring(1));
			   return false;
		   }
		   return true;
	   }
	   
	   public static boolean onRecieveChatMessage(S02PacketChat packet) {
		   return true;
	   }
	
}
