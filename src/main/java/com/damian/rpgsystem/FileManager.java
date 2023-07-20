package com.damian.rpgsystem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static File createDataFile(String relativePath) {
        File f = new File(Main.getInstance().getDataFolder() + "/" + relativePath);
        f.mkdirs();
        f.delete();
        if(!f.exists()) {
            try {
                f.createNewFile();
            }
            catch(IOException e) {
                return null;
            }
        }
        return f;
    }

    public static File createDataFolder(String relativePath) {
        File dataFolder = new File(Main.getInstance().getDataFolder() + "/" + relativePath);
        if(!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        return dataFolder;
    }

    public static List<File> getFiles(String path, String fileExtension) {
        List<File> files = new ArrayList<>();
        File folder = new File(path);
        File[] temp = folder.listFiles();
        if(temp == null) return files;
        for(File f: temp) {
            if(!f.isDirectory()) {
                if(f.getName().contains("." + fileExtension)) {
                    files.add(f);
                }
            }
        }
        return files;
    }

    public static List<File> getFiles(String path) {
        List<File> files = new ArrayList<>();
        File folder = new File(path);
        File[] temp = folder.listFiles();
        if(temp == null) return files;
        for(File f: temp) {
            if(!f.isDirectory()) {
                files.add(f);
            }
        }
        return files;
    }

    public static File getFile(String relativePath) {
        File f = new File(Main.getInstance().getDataFolder() + "/" + relativePath);
        if(f.exists()) return f;
        else return null;
    }

    public static String getPath(String relativePath) {
        return Main.getInstance().getDataFolder() + "/" + relativePath;
    }
}
