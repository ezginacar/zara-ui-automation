package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {


    public static void deleteFileContent(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_PATH + fileName, false))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fillFile(String fileName, String fileText) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_PATH + fileName, false))) {
            writer.write(fileText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
