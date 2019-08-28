package com.pingCAP.findFirstNonRepeatingWord.util;

import java.util.Properties;

public class ConfigUtils {
    public static Properties p =new Properties();

    static {
        try{
            p.load(ClassLoader.getSystemResourceAsStream("setting.properties"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getFileName() {
        return p.getProperty("fileName");
    }

    public static String getHashFileNamePrefix(){
        return p.getProperty("hashFileNamePrefix");
    }

    public static String getBlockFilePrefix(){
        return p.getProperty("blockFilePrefix");
    }

    public static int getHashNum(){
        return Integer.parseInt(p.getProperty("hashNum"));
    }

    public static int getBlockNum(){
        return Integer.parseInt(p.getProperty("blockNum"));
    }

    public static int getEachNum(){
        return Integer.parseInt(p.getProperty("eachNum"));
    }
}
