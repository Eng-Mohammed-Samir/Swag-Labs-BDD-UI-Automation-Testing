package utils;

import io.qameta.allure.Allure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureUtils {

    public static void attachFileAsTextToAllureReport(File file) throws IOException {
        try{
            if (file.isDirectory()){
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        Allure.addAttachment(f.getName(), Files.readString(Path.of(f.getPath())));
                    }
                }
            }
            else {
                Allure.addAttachment(file.getName(), Files.readString(Path.of(file.getPath())));
            }
        }
        catch (IOException e) {
            throw new IOException("Failed to read or attach file: " + file.getPath(), e);
        }

    }

    public static void attachFileToAllureReport(String fileName, String file_Path) throws IOException {
       try{
           File fileToAttach = new File(file_Path);
           if (fileToAttach.isDirectory()){
               File[] files = fileToAttach.listFiles();
               if (files != null) {
                   for (File f : files) {
                       Allure.addAttachment(f.getName(), new FileInputStream(f));
                   }
               }
           }
           else {
               Allure.addAttachment(fileName, new FileInputStream(new File(file_Path)));
           }
       }
       catch (IOException e) {
           throw new IOException("Failed to read or attach file: " + file_Path, e);
       }
    }
}
