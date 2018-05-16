package msClient;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.Charset;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;

import com.google.gson.stream.JsonReader;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class MinecraftLogin {
	
	static String accessToken;
	static String clientToken;
	static String profileID;
	static String response;
	static String username;
	
	public static Session login(String usernameIn, String password) throws Exception{
		String payload = String.format("{\"agent\": {\"name\": \"Minecraft\",\"version\": 1},\"username\": \"" + usernameIn + "\",\"password\": \"" + password + "\",\"clientToken\": \"" + Minecraft.getMinecraft().clientToken + "\"}");
		
		URL authenticationURL = new URL("https://authserver.mojang.com/authenticate");
		
		HttpsURLConnection connection = (HttpsURLConnection)authenticationURL.openConnection();
		byte payloadAsBytes[] = payload.getBytes(Charset.forName("UTF-8"));
		
		connection.setConnectTimeout(15000);
		connection.setReadTimeout(15000);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("charset", "UTF-8");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Content-Length", Integer.toString(payloadAsBytes.length));
		connection.setDoInput(true);
		connection.setDoOutput(true);
		
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.write(payloadAsBytes);
		wr.flush();
		wr.close();
		
		InputStream inputStream = null;
		try {
			response = IOUtils.toString(connection.getInputStream());
		}
		catch(IOException e) {
			System.out.println("Test");
			System.out.println(inputStream);
			e.printStackTrace();
		}
		System.out.println(response);
		
		parseJSON(response);
		System.out.println(usernameIn);
		System.out.println(username);
		System.out.println(profileID);
		System.out.println(accessToken);

		return new Session(username,profileID,accessToken,"mojang");
	}
	
	public static void parseJSON(String toParse) throws IOException{
		JsonReader reader = new JsonReader(new StringReader(toParse));
		reader.beginObject();
		while(reader.hasNext()) {
			String name = reader.nextName();
			if(name.equals("accessToken")) {
				accessToken = reader.nextString();
			} 
			else if(name.equals("clientToken")){
				System.out.println(reader.nextString() + "V" + Minecraft.getMinecraft().clientToken); 
			} 
			else if (name.equals("selectedProfile")) {
				reader.beginObject();
				while(reader.hasNext()){
					if(reader.nextName().equals("id")) {
						profileID = reader.nextString();
					}
					if(reader.nextName().equals("name")) {
						username = reader.nextString();
					}
					
				}
				reader.endObject();
			} 
			else if(name.equals("availableProfiles")) {
				reader.beginArray();
				while(reader.hasNext()) {
					reader.skipValue();
				}
				reader.endArray();
			}
		}
		reader.endObject();
		reader.close();
		
		
	}
}
