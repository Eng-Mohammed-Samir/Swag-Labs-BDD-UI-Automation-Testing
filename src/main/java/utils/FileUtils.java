package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtils {

    public static void deleteFilesInDirectory(File directory) throws IOException {
        try {
            if (directory.exists() && directory.isDirectory()) {
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isDirectory()) {
                            deleteFilesInDirectory(file);
                            file.delete();
                        }
                        else {
                            Files.delete(file.toPath());
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
