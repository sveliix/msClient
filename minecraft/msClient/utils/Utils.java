package msClient.utils;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.net.URI;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;

public class Utils {
	
	 public static class ConvertJsonToObject
	    {
	        private static Gson gson = (new GsonBuilder()).create();

	        public static final <T> T getFromJSON(String json, Class<T> clazz)
	        {
	            return (T)gson.fromJson(json, clazz);
	        }

	        public static final <T> String toJSON(T clazz)
	        {
	            return gson.toJson(clazz);
	        }
	    }

	 public static ArrayList<String> extractDomains(String value)
	    {
	        value = value.replaceAll(/*Color.c + */"[a-z0-9]", "");
	        ArrayList<String> arraylist = new ArrayList();

	        if (value == null)
	        {
	            return arraylist;
	        }
	        else
	        {
	            String s = "(?i)\\b((?:[a-z][\\w-]+:(?:\\/{1,3}|[a-z0-9%])|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}\\/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:\'\".,<>?\u00ab\u00bb\u201c\u201d\u2018\u2019]))";
	            Pattern pattern = Pattern.compile(s, 2);
	            Matcher matcher = pattern.matcher(value);

	            while (matcher.find())
	            {
	                arraylist.add(value.substring(matcher.start(0), matcher.end(0)));
	            }

	            return arraylist;
	        }
	    }
	 
	public static void openWebpage(final URI uri, boolean request)
    {
        if (request)
        {
            final GuiScreen guiscreen = Minecraft.getMinecraft().currentScreen;
            Minecraft.getMinecraft().displayGuiScreen(new GuiYesNo(new GuiYesNoCallback()
            {
                public void confirmClicked(boolean result, int id)
                {
                    if (result)
                    {
                        Desktop desktop1 = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;

                        if (desktop1 != null && desktop1.isSupported(Action.BROWSE))
                        {
                            try
                            {
                                desktop1.browse(uri);
                            }
                            catch (Exception exception1)
                            {
                                exception1.printStackTrace();
                            }
                        }
                    }

                    Minecraft.getMinecraft().displayGuiScreen(guiscreen);
                }
            }, "Do you want to open this link in your default browser?", Color.RED + ("b") + uri.toString(), 31102009));
        }
        else
        {
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;

            if (desktop != null && desktop.isSupported(Action.BROWSE))
            {
                try
                {
                    desktop.browse(uri);
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }
        }
    }
	
}
