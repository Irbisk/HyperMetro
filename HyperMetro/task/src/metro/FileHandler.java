package metro;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    public static String handleFile(String fileName) {
        String fileContent = "";
        try {

            fileContent = readFileAsString(fileName);
        } catch (IOException e) {
            System.out.println("Error! Such a file doesn't exist!");

        }
        return fileContent;
    }

    private static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

}
