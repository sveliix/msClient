package msClient.updater;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import msClient.config.Variables;
import net.minecraft.client.Minecraft;

public class UpdaterCheck extends Thread 
{
	public final String url = "http://msclient.de/currentVersionFile.zip";
	// TODO update reference
	
	public UpdaterCheck()
    {
    }
	
	public void run() 
	{
		if (!this.isDeprecated())
		{
			Variables.isDeprecated = false; return;
		}
		String s = "filePath"; // TODO ENTER REAL FILE PATH HERE
		File file1 = new File(s);
		
		try
		{
			downloadFile(url, file1);
			Variables.isDeprecated = true;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private static void downloadFile(String s, File dest) throws IOException
    {
        URL url = new URL(s);
        URLConnection urlconnection = url.openConnection();
        urlconnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        BufferedInputStream bufferedinputstream = new BufferedInputStream(urlconnection.getInputStream());
        FileOutputStream fileoutputstream = new FileOutputStream(dest);
        BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(fileoutputstream, 1024);
        byte[] abyte = new byte[1024];
        int k = 0;

        while ((k = bufferedinputstream.read(abyte, 0, 1024)) >= 0)
        {
            bufferedoutputstream.write(abyte, 0, k);
        }

        bufferedoutputstream.close();
        bufferedinputstream.close();
    }
	
	
	public boolean isDeprecated()
	{
		String v0 = msClient.main.Client_Version;
		
		String s = "http://msclient.de/currVersion/";
		File dest = new File("currentversion.txt");
		// TODO update references
		
		try {
			URL url = new URL(s);
	        URLConnection urlconnection = url.openConnection();
	        urlconnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
	        BufferedInputStream bufferedinputstream = new BufferedInputStream(urlconnection.getInputStream());
	        FileOutputStream fileoutputstream = new FileOutputStream(dest);
	        BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(fileoutputstream, 1024);
	        byte[] abyte = new byte[1024];
	        int k = 0;

	        while ((k = bufferedinputstream.read(abyte, 0, 1024)) >= 0)
	        {
	            bufferedoutputstream.write(abyte, 0, k);
	        }

	        bufferedoutputstream.close();
	        bufferedinputstream.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		String s0 = "";

		try {
			BufferedReader br = new BufferedReader(new FileReader(dest));

			while ((s0 = br.readLine()) != null) {
				if (s0.contains("version")) {
					s0 = s0.substring(s0.indexOf("[")+1, s0.indexOf("]"));
				}
			}
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return (!v0.equalsIgnoreCase(s0));
	}
	
}
