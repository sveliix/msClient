package net.labymod.clanwar.installer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.labymod.clanwar.installer.frame.FrameInstall;
import net.labymod.clanwar.installer.json.JSONWriter;
import net.labymod.clanwar.installer.json.parser.ParseException;

import org.apache.commons.io.FileUtils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Installer extends Thread
{
    private FrameInstall frame;

    public Installer(FrameInstall frame)
    {
        this.frame = frame;
        new ProgressBarUpdater(frame.getProgressBar(), frame.getInstallTitle(), frame.getInstallSubTitle());
        this.start();
    }
    
    public Installer()
    {
    	this.start();
    }

    public void run()
    {
        File file1 = Main.path;
        Utils.dbg("Dir minecraft: " + file1);
        File file2 = new File(file1, "versions");
        Utils.dbg("Dir versions: " + file2);
        File file3 = new File(file1, "MSClient");

        if (!file3.exists() && !file3.mkdir())
        {
            Utils.dbg("Cannot create data folder");
        }

        File file4;

        try
        {
            file4 = getLabyModVersion();
        }
        catch (Exception var18)
        {
            file4 = this.fileChooser("Can\'t find currently running Installer jar! Please select the MSClient Installer", "jar", "MSClient Installer (jar file)", "Can\'t find currently running Installer jar!", "Invalid file!");
        }

        if (file4 == null)
        {
            this.close("Can\'t find the MSClient Installer!");
        }
        else
        {
            Utils.dbg("MSClient Version: " + file4.getName());
            String s = "MSClient";
            File file5 = new File(file2, s);

            try
            {
                File file6 = new File(file5, s + ".jar");
                Files.deleteIfExists(file6.toPath());
            }
            catch (IOException ioexception1)
            {
                ioexception1.printStackTrace();
                Object[] aobject = new Object[] {"Cancel", "Try again"};
                int i = JOptionPane.showOptionDialog((Component)null, "Please close Minecraft", "Warning", 0, 1, (Icon)null, aobject, aobject[1]);

                if (i == 1)
                {
                    this.run();
                    return;
                }

                System.exit(0);
                return;
            }

            this.copyMinecraftJson("1.8.8", s, file2);
            ArrayList<File> arraylist = new ArrayList();
            arraylist.add(file4);
            File file9 = new File(file2, s + "/temp");
            file9.mkdir();

            for (ModTemplate modtemplate : Main.modTempates)
            {
                if (modtemplate.isEnabled())
                {
                    try
                    {
                        File file7 = new File(file9, modtemplate.getModName().replace("/", "&") + ".zip");
                        Utils.downloadFile(modtemplate.getDownload(), file7);
                        arraylist.add(file7);
                    }
                    catch (IOException ioexception)
                    {
                        ioexception.printStackTrace();
                        Utils.showMessage("Error while installing " + modtemplate.getModName() + ": " + ioexception.getMessage() + "! Skipping..");
                    }
                }
            }

            if (Main.mods != null && Main.mods.length != 0)
            {
                arraylist.addAll(Arrays.<File>asList(Main.mods));
            }

            try
            {
                updateLauncherJson(file1, s);
            }
            catch (Exception exception1)
            {
                exception1.printStackTrace();
                Utils.showMessage("Oops! Failed while creating a MSClient profile for you! You have to create a profile manually. (" + exception1.getMessage() + ")");
            }

            File file10 = new File(file5, s + ".json");
            

            try
            {
                updateVersionJson(file10, s );
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
                this.close("Can\'t edit " + file10.getAbsolutePath());
                return;
            }

            //this.backupMods(arraylist, file4);
            File file11 = new File(file2, "1.8.8");
            File file12 = new File(file11, "1.8.8.jar");
            File file8 = new File(file5, s + ".jar");

            if (!file12.exists())
            {
                System.out.println("Can\'t find " + file12.getAbsolutePath());
                this.close("Minecraft version not found: " + file12.getName() + "\nYou need to start the version " + file12.getName() + " manually once.");
            }
            else
            {
                arraylist.add(file12);
                ProgressBarUpdater.setMax(Utils.count(arraylist));

                if (!Utils.copyJars(arraylist, file8, false))
                {
                    this.close("Error while installing MSClient!");
                }
                else
                {
                    if (file9.exists())
                    {
                        Utils.deleteDirSilent(file9);
                        ProgressBarUpdater.subTitle = "Delete " + file9.getName();
                        file9.delete();
                    }

                    try
                    {
                        if (!(new File(file3, ".update")).createNewFile())
                        {
                            ;
                        }
                    }
                    catch (Exception var14)
                    {
                        ;
                    }

                    ProgressBarUpdater.subTitle = "Done.";
                    this.setValue(100);
                    Utils.showMessage("MSClient is successfully installed.");
                    this.frame.dispose();
                    System.exit(0);
                }
            }
        }
    }

    private void setValue(int id)
    {
        ProgressBarUpdater.value = id;
    }

    /*private void backupMods(ArrayList<File> mods, File ignore)
    {
        if (mods != null && !mods.isEmpty())
        {
            File file1 = new File(Main.path.getAbsolutePath() + "/ClanWarMod/mods-" + "1.8.8" + "/");

            if (file1.exists())
            {
                Utils.deleteDir(file1);
            }

            if (file1.getParentFile().getParentFile().exists() && !file1.exists())
            {
                file1.mkdirs();
            }

            for (File file2 : mods)
            {
                if (!file2.getName().equals(ignore.getName()))
                {
                    Utils.copyFile(file2, new File(file1, file2.getName()));
                }
            }
        }
    }*/

    private void status(String string)
    {
        this.frame.getInstallTitle().setText(string);
    }

    private static File getLabyModVersion() throws URISyntaxException, IOException
    {
        URL url = Installer.class.getProtectionDomain().getCodeSource().getLocation();
        Utils.dbg("URL: " + url);
        URI uri = url.toURI();
        return new File(uri);
    }

    public File fileChooser(String title, String fileType, String fileDesc, String errorNotFound, String errorInvalid)
    {
        JFileChooser jfilechooser = new JFileChooser();
        FileNameExtensionFilter filenameextensionfilter = new FileNameExtensionFilter(fileDesc, new String[] {fileType});
        jfilechooser.setFileFilter(filenameextensionfilter);
        jfilechooser.setCurrentDirectory(new File(Utils.getDesktop()));
        jfilechooser.setDialogTitle(title);
        this.status("FileChooser: Selecting " + fileType);
        int i = jfilechooser.showOpenDialog(this.frame.getParent());

        if (i != 0)
        {
            this.close(errorNotFound);
        }
        else
        {
            if (jfilechooser.getSelectedFile() != null && jfilechooser.getSelectedFile().exists())
            {
                return jfilechooser.getSelectedFile();
            }

            this.close(errorInvalid);
        }

        return null;
    }

    private boolean copyMinecraftJson(String mcVer, String mcVerLabyMod, File dirMcVer)
    {
        File file1 = new File(dirMcVer, mcVer);
        System.out.println("Dir version MC: " + file1.getAbsolutePath() + " (" + file1.getName() + ")");

        if (!file1.exists())
        {
            System.out.println("Can\'t find " + dirMcVer.getAbsolutePath() + " (copy json)");
            this.close("Minecraft version not found: " + mcVer + "\nYou need to start the version " + mcVer + " manually once.");
            return false;
        }
        else
        {
            File file2 = new File(dirMcVer, mcVerLabyMod);

            if (file2.exists())
            {
                Utils.deleteDir(file2);
            }

            file2.mkdirs();
            Utils.dbg("Dir version MC-MSClient: " + file2);
            File file3 = new File(file1, mcVer + ".json");
            File file4 = new File(file2, mcVerLabyMod + ".json");
            Utils.copyFile(file3, file4);
            System.out.println("Test");
            return true;
        }
    }

    private static void updateLauncherJson(File dirMc, String mcVerLabyMod) throws IOException, ParseException
    {
        File file1 = new File(dirMc, "launcher_profiles.json");
        String s = Utils.readFile(file1);
        JsonParser jsonparser = new JsonParser();
        JsonObject jsonobject = jsonparser.parse(s).getAsJsonObject();
        JsonObject jsonobject1 = jsonobject.getAsJsonObject("profiles");
        List<String> list = new ArrayList();

        for (Entry<String, JsonElement> entry : jsonobject1.entrySet())
        {
            if (((String)entry.getKey()).startsWith("MSClient"))
            {
                list.add(entry.getKey());
            }
        }

        for (String s2 : list)
        {
            jsonobject1.remove(s2);
        }

        JsonObject jsonobject2 = jsonobject1.has(mcVerLabyMod) ? jsonobject1.get(mcVerLabyMod).getAsJsonObject() : null;

        if (jsonobject2 == null)
        {
            jsonobject2 = new JsonObject();
            jsonobject2.addProperty("name", mcVerLabyMod);
        }

        jsonobject2.addProperty("javaArgs", "-Xmx2G -Xms2G -Xmn768m -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+UseNUMA -XX:+CMSParallelRemarkEnabled -XX:MaxTenuringThreshold=15 -XX:MaxGCPauseMillis=30 -XX:GCPauseIntervalMillis=150 -XX:+UseAdaptiveGCBoundary -XX:-UseGCOverheadLimit -XX:+UseBiasedLocking -XX:SurvivorRatio=8 -XX:TargetSurvivorRatio=90 -XX:MaxTenuringThreshold=15 -Dfml.ignorePatchDiscrepancies=true -Dfml.ignoreInvalidMinecraftCertificates=true -XX:+UseFastAccessorMethods -XX:+UseCompressedOops -XX:+OptimizeStringConcat -XX:+AggressiveOpts -XX:ReservedCodeCacheSize=1024m -XX:+UseCodeCacheFlushing -XX:SoftRefLRUPolicyMSPerMB=10000 -XX:ParallelGCThreads=10");
        jsonobject2.addProperty("lastVersionId", mcVerLabyMod);
        jsonobject1.add(mcVerLabyMod, jsonobject2);
        jsonobject.addProperty("selectedProfile", mcVerLabyMod);
        Gson gson = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
        String s1 = gson.toJson((JsonElement)jsonobject);
        FileUtils.writeStringToFile(file1, s1, "UTF-8", false);
    }

    private static void updateVersionJson(File fileJson, String verLabyMod) throws IOException, ParseException, org.json.simple.parser.ParseException
    {
    	String s = Utils.readFile(fileJson);
    	
        JSONParser jsonparser = new JSONParser();
        JSONObject jsonobject = (JSONObject)jsonparser.parse(s);
        jsonobject.put("id", verLabyMod);
        jsonobject.remove("downloads");
        FileWriter filewriter = new FileWriter(fileJson);
        JSONWriter jsonwriter = new JSONWriter(filewriter);
        jsonwriter.writeObject(jsonobject);
        filewriter.flush();
        filewriter.close();      
    }

    private void close(String message)
    {
        if (message != null)
        {
            this.frame.hide();
            Utils.showErrorMessage(message);
            this.frame.dispose();
            System.exit(0);
        }
    }
}
