package com.capgemini.techday.util;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtils {

    public static String readFileToString(File file) {
        try {
            return new String(Files.readAllBytes(file.toPath()));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static File getFileFromClasspath(String classpath) {
        File file = findFileInClasspath(classpath);
        if (file == null) {
            throw new RuntimeException("File " + classpath + " was not found.");
        }
        return file;
    }

    public static File findFileInClasspath(String classpath) {
        File file = null;
        try {
            Resource resource = new DefaultResourceLoader()
                    .getResource(classpath);
            if (resource.exists()) {
                file = resource.getFile();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
}
