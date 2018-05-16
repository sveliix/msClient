package net.labymod.clanwar.installer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;
import java.util.jar.JarEntry;
import java.util.jar.JarException;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipException;
import javax.swing.JOptionPane;

public class Utils
{
    static MessageDigest mdSha;
    static boolean shader = false;
    public static final String MAC_OS_HOME_PREFIX = "Library/Application Support";
    List<String> lines = new ArrayList();
    String line = null;

    public static File getWorkingDirectory()
    {
        return getWorkingDirectory(".minecraft");
    }

    public static File getWorkingDirectory(String applicationName)
    {
        String s = System.getProperty("user.home", ".");
        File file1 = null;

        switch (getPlatform())
        {
            case LINUX:
                file1 = new File(s, '.' + applicationName + '/');
                break;

            case UNKNOWN:
                file1 = new File(s, "Library/Application Support/" + applicationName);
                break;

            case MACOS:
                String s1 = System.getenv("APPDATA");

                if (s1 != null)
                {
                    file1 = new File(s1, "." + applicationName + '/');
                }
                else
                {
                    file1 = new File(s, '.' + applicationName + '/');
                }

                break;

            case WINDOWS:
                file1 = new File(s, "AppData/Roaming/" + applicationName);
                break;

            case SOLARIS:
                String s2 = System.getenv("APPDATA");

                if (s2 != null)
                {
                    file1 = new File(s2, "." + applicationName + '/');
                }
                else
                {
                    file1 = new File(s, '.' + applicationName + '/');
                }

                break;

            default:
                file1 = new File(s, applicationName + '/');
        }

        if (!file1.exists() && !file1.mkdirs())
        {
            throw new RuntimeException("The working directory could not be created: " + file1);
        }
        else
        {
            return file1;
        }
    }

    public static boolean copyFile(File from, File to)
    {
        if (!from.exists())
        {
            error(from.getName() + " doesn\'t exists");
            return false;
        }
        else
        {
            if (!to.getParentFile().exists())
            {
                to.getParentFile().mkdirs();
            }

            try
            {
                System.out.println("[DEBUG] copy " + from.getName());
                Files.copy(from.toPath(), to.toPath(), new CopyOption[0]);
                return true;
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
                error(ioexception.getMessage());
                return false;
            }
        }
    }

    public static void addToFile(File from, File to) throws IOException
    {
        if (!from.exists())
        {
            error(from.getName() + " doesn\'t exists");
        }
        else
        {
            if (!to.getParentFile().exists())
            {
                to.getParentFile().mkdirs();
            }

            if (!from.getCanonicalPath().equals(to.getCanonicalPath()))
            {
                FileInputStream fileinputstream = new FileInputStream(from);
                FileOutputStream fileoutputstream = new FileOutputStream(to, true);
                copyAll(fileinputstream, fileoutputstream);
                fileoutputstream.flush();
                fileinputstream.close();
                fileoutputstream.close();
            }
        }
    }

    public static void copyAll(InputStream is, OutputStream os) throws IOException
    {
        byte[] abyte = new byte[4096];

        while (true)
        {
            int i = is.read(abyte);

            if (i < 0)
            {
                return;
            }

            os.write(abyte, 0, i);
        }
    }

    public static void downloadFile(String s, File dest) throws IOException
    {
        URL url = new URL(s);
        HttpURLConnection httpurlconnection = (HttpURLConnection)url.openConnection();
        httpurlconnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        ReadableByteChannel readablebytechannel = Channels.newChannel(httpurlconnection.getInputStream());
        FileOutputStream fileoutputstream = new FileOutputStream(dest);
        fileoutputstream.getChannel().transferFrom(readablebytechannel, 0L, Long.MAX_VALUE);
        fileoutputstream.close();
    }

    public static boolean copyJars(ArrayList<File> mods, File destinationJar, boolean reinstalling)
    {
        ProgressBarUpdater.title = "Install MSClient in " + destinationJar.getName();

        try
        {
            ArrayList<String> arraylist = new ArrayList();
            JarOutputStream jaroutputstream = new JarOutputStream(new FileOutputStream(destinationJar));
            byte[] abyte = new byte[63];

            for (File file1 : mods)
            {
                ProgressBarUpdater.title = "Install " + file1.getName();
                System.out.println("[DEBUG] Install " + file1.getName());

                try
                {
                    JarFile jarfile = new JarFile(file1);
                    Enumeration<JarEntry> enumeration = jarfile.entries();

                    while (enumeration.hasMoreElements())
                    {
                        JarEntry jarentry = new JarEntry(((JarEntry)enumeration.nextElement()).getName());

                        if (!jarentry.getName().startsWith("META-INF/") && !jarentry.getName().startsWith("Updater.jar") && !arraylist.contains(jarentry.getName()))
                        {
                            ProgressBarUpdater.subTitle = "Copy " + jarentry.getName();
                            arraylist.add(jarentry.getName());
                            InputStream inputstream = jarfile.getInputStream(jarentry);
                            jaroutputstream.putNextEntry(jarentry);
                            int i;

                            while ((i = inputstream.read(abyte)) != -1)
                            {
                                jaroutputstream.write(abyte, 0, i);
                            }

                            inputstream.close();
                            jaroutputstream.flush();
                            jaroutputstream.closeEntry();
                            ProgressBarUpdater.next();
                        }
                    }

                    ProgressBarUpdater.subTitle = "[1/2] Complete installation..";
                    jarfile.close();
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();

                    if ((exception instanceof ZipException || exception instanceof JarException) && file1.getName().equals("1.8.8.jar") && reinstallVersion())
                    {
                    	jaroutputstream.close();
                        return copyJars(mods, destinationJar, true);
                    }

                    error("Error while installing " + file1.getName() + " (" + exception.getMessage() + ")");

                    if (file1.getName().equals("1.8.8.jar"))
                    {
                        showMessage("Failed to read vanilla 1.8.8 jar file! You need to run Minecraft 1.8.8 vanilla manually once.");
                    }

                    jaroutputstream.close();
                    return false;
                }
            }

            ProgressBarUpdater.subTitle = "[2/2] Complete installation..";
            jaroutputstream.close();
        }
        catch (Exception exception1)
        {
            exception1.printStackTrace();
            error("Error while installing " + mods.size() + " Mods (" + exception1.getMessage() + ")");
            return false;
        }

        ProgressBarUpdater.subTitle = "";
        return true;
    }

    public static boolean reinstallVersion()
    {
        File file1 = new File(getWorkingDirectory(), "versions/1.8.8");
        File file2 = new File(file1, "1.8.8.jar");

        if (file2.exists() && !file2.delete())
        {
            error("[REINSTALLING] Error while trying to delete version 1.8.8");
            return false;
        }
        else
        {
            try
            {
                downloadFile("https://launcher.mojang.com/mc/game/1.8.8/client/0983f08be6a4e624f5d85689d1aca869ed99c738/client.jar", file2);
                return true;
            }
            catch (IOException ioexception)
            {
                error("[REINSTALLING] Error while trying to download version 1.8.8 (" + ioexception.getMessage() + ")");
                return false;
            }
        }
    }

    public static int count(ArrayList<File> mods)
    {
        System.out.println("Reading all files..");
        ProgressBarUpdater.title = "Reading all files..";
        int i = 0;

        try
        {
            new ArrayList();

            for (File file1 : mods)
            {
                try
                {
                    JarFile jarfile = new JarFile(file1);
                    ProgressBarUpdater.subTitle = "Read " + jarfile.getName();
                    i += jarfile.size();
                    jarfile.close();
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }
        }
        catch (Exception exception1)
        {
            exception1.printStackTrace();
        }

        return i;
    }

    private static void installShaderMod(File jarFile)
    {
        File file1 = getWorkingDirectory("minecraft");
        String s = "shadersmodcore";
        String s1 = "ShadersModCore";
        String s2 = "2.4.12mc1.8";
        File file2 = new File(file1, "libraries/" + s + "/" + s1 + "/" + s2);
        File file3 = new File(file2, s1 + "-" + s2 + ".jar");
        InputStream inputstream = null;

        try
        {
            inputstream = new FileInputStream(jarFile);
        }
        catch (FileNotFoundException filenotfoundexception)
        {
            filenotfoundexception.printStackTrace();
        }

        file2.mkdirs();
        byte[] abyte = readByteArray(inputstream);

        if (abyte != null && writeFileWithSha(file3, abyte))
        {
            shader = true;
        }
    }

    private static boolean isShaderMod(File jarFile)
    {
        try
        {
            JarFile jarfile = new JarFile(jarFile.getAbsolutePath());
            JarEntry jarentry = jarfile.getJarEntry("shadersmod/installer/Installer.class");
            jarfile.close();
            return jarentry != null;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return false;
        }
    }

    public static String toShaString(byte[] abyte)
    {
        mdSha.reset();
        byte[] abyte1 = mdSha.digest(abyte);
        StringBuilder stringbuilder = new StringBuilder();

        for (byte b0 : abyte1)
        {
            stringbuilder.append(String.format("%02x", new Object[] {Byte.valueOf(b0)}));
        }

        return stringbuilder.toString();
    }

    public static boolean writeFileWithSha(File dst, byte[] abyte)
    {
        boolean flag = false;
        OutputStream outputstream = null;
        Writer writer = null;

        try
        {
            FileOutputStream fileoutputstream = new FileOutputStream(dst);
            fileoutputstream.write(abyte);
            fileoutputstream.close();
            fileoutputstream = null;
            flag = true;
            FileWriter filewriter = new FileWriter(dst.getPath() + ".sha");
            filewriter.write(toShaString(abyte));
            filewriter.close();
            filewriter = null;
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }

        return flag;
    }

    static void close(Closeable closeable)
    {
        try
        {
            closeable.close();
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    static byte[] readByteArray(InputStream is)
    {
        try
        {
            int i = 1024;

            if (is instanceof ByteArrayInputStream)
            {
                i = is.available();
                byte[] abyte1 = new byte[i];
                is.read(abyte1, 0, i);
                return abyte1;
            }
            else
            {
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                byte[] abyte = new byte[i];
                int j;

                while ((j = is.read(abyte, 0, i)) != -1)
                {
                    bytearrayoutputstream.write(abyte, 0, j);
                }

                return bytearrayoutputstream.toByteArray();
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
    }

    public static File[] concat(File[] a, File[] b)
    {
        int i = a.length;
        int j = b.length;
        File[] afile = new File[i + j];
        System.arraycopy(a, 0, afile, 0, i);
        System.arraycopy(b, 0, afile, i, j);
        return afile;
    }

    public static boolean copyFiles(File from, File to)
    {
        if (!from.exists())
        {
            error(from.getName() + " doesn\'t exists");
            return false;
        }
        else
        {
            if (!to.getParentFile().exists())
            {
                to.getParentFile().mkdirs();
            }

            try
            {
                if (from.listFiles() != null)
                {
                    File[] afile;

                    for (File file1 : afile = from.listFiles())
                    {
                        File file2 = new File(to.toPath() + "/" + file1.getName());

                        if (file2.exists() && !file2.isDirectory())
                        {
                            deleteDir(file2);
                        }

                        copyFiles(file1, file2);
                    }
                }
                else
                {
                    System.out.println("[DEBUG] copy " + from.getName());
                    Files.copy(from.toPath(), to.toPath(), new CopyOption[0]);
                }

                return true;
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
                error(ioexception.getMessage());
                return false;
            }
        }
    }

    public static boolean deleteDir(File dir)
    {
        if (dir.exists())
        {
            if (dir.isDirectory())
            {
                String[] astring = dir.list();

                for (int i = 0; i < astring.length; ++i)
                {
                    boolean flag = deleteDir(new File(dir, astring[i]));

                    if (!flag)
                    {
                        return false;
                    }
                }
            }

            System.out.println("[DEBUG] delete " + dir.getName());

            if (!dir.delete())
            {
                error("Delete " + dir.getName() + " failed");
                return false;
            }
        }

        return true;
    }

    public static boolean deleteDirSilent(File dir)
    {
        if (dir.exists())
        {
            if (dir.isDirectory())
            {
                String[] astring = dir.list();

                for (int i = 0; i < astring.length; ++i)
                {
                    boolean flag = deleteDir(new File(dir, astring[i]));

                    if (!flag)
                    {
                        return false;
                    }
                }
            }

            ProgressBarUpdater.subTitle = "Delete " + dir.getName();
            System.out.println("[DEBUG] delete " + dir.getName());

            if (!dir.delete())
            {
                return false;
            }
        }

        return true;
    }

    public static void error(String message)
    {
        if (message != null)
        {
            showErrorMessage(message);
        }
    }

    public static String getDesktop()
    {
        return System.getProperty("user.home") + "/Desktop/";
    }

    public static void editFile(File file, String replaceFrom, String replaceTo)
    {
        try
        {
            List<String> list = new ArrayList();
            String s = null;
            FileReader filereader = new FileReader(file);
            BufferedReader bufferedreader = new BufferedReader(filereader);
            System.out.println("[DEBUG] edit " + file.getName());

            for (; (s = bufferedreader.readLine()) != null; list.add(s))
            {
                if (s.contains(replaceFrom))
                {
                    s = s.replace(replaceFrom, replaceTo);
                }
            }

            filereader.close();
            bufferedreader.close();
            FileWriter filewriter = new FileWriter(file);
            BufferedWriter bufferedwriter = new BufferedWriter(filewriter);

            for (String s1 : list)
            {
                bufferedwriter.write(s1);
            }

            bufferedwriter.flush();
            bufferedwriter.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public static void editFileFirst(File file, String replaceFrom, String replaceTo)
    {
        try
        {
            List<String> list = new ArrayList();
            String s = null;
            boolean flag = false;
            FileReader filereader = new FileReader(file);
            BufferedReader bufferedreader = new BufferedReader(filereader);
            System.out.println("[DEBUG] edit " + file.getName());

            for (; (s = bufferedreader.readLine()) != null; list.add(s))
            {
                if (s.contains(replaceFrom) && !flag)
                {
                    flag = true;
                    s = s.replaceFirst(replaceFrom, replaceTo);
                }
            }

            filereader.close();
            bufferedreader.close();
            FileWriter filewriter = new FileWriter(file);
            BufferedWriter bufferedwriter = new BufferedWriter(filewriter);

            for (String s1 : list)
            {
                bufferedwriter.write(s1);
            }

            bufferedwriter.flush();
            bufferedwriter.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH)
    {
        Image image = img.getScaledInstance(newW, newH, 4);
        BufferedImage bufferedimage = new BufferedImage(newW, newH, 2);
        Graphics2D graphics2d = bufferedimage.createGraphics();
        graphics2d.drawImage(image, 0, 0, (ImageObserver)null);
        graphics2d.dispose();
        return bufferedimage;
    }

    public static Utils.OS getPlatform()
    {
        String s = System.getProperty("os.name").toLowerCase();
        return s.contains("win") ? Utils.OS.WINDOWS : (s.contains("mac") ? Utils.OS.MACOS : (s.contains("solaris") ? Utils.OS.SOLARIS : (s.contains("sunos") ? Utils.OS.SOLARIS : (s.contains("linux") ? Utils.OS.LINUX : (s.contains("unix") ? Utils.OS.LINUX : Utils.OS.UNKNOWN)))));
    }

    public static int find(byte[] buf, byte[] pattern)
    {
        return find(buf, 0, pattern);
    }

    public static int find(byte[] buf, int index, byte[] pattern)
    {
        for (int i = index; i < buf.length - pattern.length; ++i)
        {
            boolean flag = true;

            for (int j = 0; j < pattern.length; ++j)
            {
                if (pattern[j] != buf[i + j])
                {
                    flag = false;
                    break;
                }
            }

            if (flag)
            {
                return i;
            }
        }

        return -1;
    }

    public static byte[] readAll(InputStream is) throws IOException
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        byte[] abyte = new byte[63];

        while (true)
        {
            int i = is.read(abyte);

            if (i < 0)
            {
                is.close();
                byte[] abyte1 = bytearrayoutputstream.toByteArray();
                return abyte1;
            }

            bytearrayoutputstream.write(abyte, 0, i);
        }
    }

    public static void dbg(String str)
    {
        System.out.println(str);
    }

    public static String[] tokenize(String str, String delim)
    {
        List<String> list = new ArrayList();
        StringTokenizer stringtokenizer = new StringTokenizer(str, delim);

        while (stringtokenizer.hasMoreTokens())
        {
            String s = stringtokenizer.nextToken();
            list.add(s);
        }

        String[] astring = (String[])((String[])list.toArray(new String[list.size()]));
        return astring;
    }

    public static String getExceptionStackTrace(Throwable e)
    {
        StringWriter stringwriter = new StringWriter();
        PrintWriter printwriter = new PrintWriter(stringwriter);
        e.printStackTrace(printwriter);
        printwriter.close();

        try
        {
            stringwriter.close();
        }
        catch (IOException var4)
        {
            ;
        }

        return stringwriter.getBuffer().toString();
    }

    public static void showMessage(String msg)
    {
        JOptionPane.showMessageDialog((Component)null, msg, "MSClient", 1);
    }

    public static void showErrorMessage(String msg)
    {
        JOptionPane.showMessageDialog((Component)null, msg, "Error", 0);
    }

    public static String readFile(File file) throws IOException
    {
        return readFile(file, "ASCII");
    }

    public static String readFile(File file, String encoding) throws IOException
    {
        FileInputStream fileinputstream = new FileInputStream(file);
        InputStreamReader inputstreamreader = new InputStreamReader(fileinputstream, encoding);
        BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
        StringBuffer stringbuffer = new StringBuffer();

        while (true)
        {
            String s = bufferedreader.readLine();

            if (s == null)
            {
                bufferedreader.close();
                inputstreamreader.close();
                fileinputstream.close();
                return stringbuffer.toString();
            }

            stringbuffer.append(s);
            stringbuffer.append("\n");
        }
    }

    public static void centerWindow(Component c, Component par)
    {
        if (c != null)
        {
            Rectangle rectangle = c.getBounds();
            Rectangle rectangle1;

            if (par != null && par.isVisible())
            {
                rectangle1 = par.getBounds();
            }
            else
            {
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                rectangle1 = new Rectangle(0, 0, dimension.width, dimension.height);
            }

            int j = rectangle1.x + (rectangle1.width - rectangle.width) / 2;
            int i = rectangle1.y + (rectangle1.height - rectangle.height) / 2;

            if (j < 0)
            {
                j = 0;
            }

            if (i < 0)
            {
                i = 0;
            }

            c.setBounds(j, i, rectangle.width, rectangle.height);
        }
    }

    static
    {
        try
        {
            mdSha = MessageDigest.getInstance("SHA");
        }
        catch (NoSuchAlgorithmException var1)
        {
            System.err.println("No SHA algorithm");
            mdSha = null;
        }
    }

    public static enum OS
    {
        LINUX,
        SOLARIS,
        WINDOWS,
        MACOS,
        UNKNOWN;
    }
}
