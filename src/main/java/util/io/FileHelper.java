package util.io;

import java.io.*;

public class FileHelper {
    public boolean fileExists(String filename) {
        File tempFile = new File(filename);
        return tempFile.exists();
    }

    public boolean isFileEmpty(String filename) {
        File file = new File(filename);
        return file.length() == 0;
    }

    public void writeToFile(String input, String filename) {
        try (FileWriter fileWriter = new FileWriter(filename)) {

            fileWriter.write(input);
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFile(String filename)  {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultStringBuilder.toString();
    }
}