package de.resourcepacks24.utils;

public class Color
{
    public static String c = "\u00a7";

    public static String cl(String color)
    {
        return c + color;
    }

    public static int toRGB(int r, int g, int b, int a)
    {
        return (a & 255) << 24 | (r & 255) << 16 | (g & 255) << 8 | (b & 255) << 0;
    }
    
    public static String fix(String i)
    {
        return i.replace("\u00c2", "");
    }
    
    public static String booleanToColor(Boolean b)
    {
        return b.booleanValue() ? fix("" + c + "a") : fix("" + c + "c");
    }
    
}
