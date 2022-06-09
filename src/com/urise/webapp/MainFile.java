package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) throws IOException {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File(".\\");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        listFiles(".\\src");

    }

    static void listFiles(String path) throws IOException {
        File file = new File(path);
        File[] files = file.listFiles();
        String[] list = file.list();
        if (files != null && list != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    String filePath = files[i].getCanonicalPath();
                    System.out.println();
                    //System.out.println(list[i]);
                    listFiles(filePath);
                } else System.out.println(list[i]);
            }
        }
    }
}

