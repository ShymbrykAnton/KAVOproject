package util.io;

import java.io.*;

import static util.Constants.Config.*;

public class FileHelper {
    public boolean fileExists() {
        File tempFile = new File(filename);
        return tempFile.exists();
    }

    public boolean isFileEmpty() {
        File file = new File(filename);
        return file.length() == 0;
    }

    public void writeToFile(String input) {
        try (FileWriter fileWriter = new FileWriter(filename)) {

            fileWriter.write(input);
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFile()  {
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