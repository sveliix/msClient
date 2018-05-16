package net.labymod.clanwar.installer;

import java.io.File;
import java.util.ArrayList;
import net.labymod.clanwar.installer.frame.FrameMain;

public class Main
{
    public static final String clientBrand = "MSClient";
    public static final String modVersion = "1.2.3";
    public static final String mcVersion = "1.8.8";
    public static File[] mods = null;
    public static File path = Utils.getWorkingDirectory();
    public static ArrayList<ModTemplate> modTempates = new ArrayList();

    private static void setupTemplates()
    {
        String s = "http://www.labymod.net/mods/";
        //modTempates.add(new ModTemplate("OptiFine 1.8.8 HD U G7", "sp614x", true, s + "OptiFine_1.8.8_HD_U_G7.jar", false));
    }

    public static void main(String[] args)
    {
        System.setProperty("java.net.preferIPv4Stack", "true");
        setupTemplates();
        new FrameMain();
    }
}
