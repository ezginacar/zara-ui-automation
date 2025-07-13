package utils;


import java.io.*;

public class FileUtil {

    private final String filePath;

    public FileUtil(String fileName) {
        this.filePath = Constants.FILE_PATH + fileName;
    }

    public void deleteAllFileContent() {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(filePath, false))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fillFile(String fileText) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(fileText);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String readFile() {
            StringBuilder content = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content.toString().trim();
    }


}
