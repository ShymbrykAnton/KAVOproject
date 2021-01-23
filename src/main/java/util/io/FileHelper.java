package util.io;

import java.io.*;

import static util.Constants.View.*;

public class FileHelper {
    public boolean fileExists() {
        File tempFile = new File(filename);
        return tempFile.exists();
    }

    public boolean isFileEmpty(){
        File file = new File(filename);
        if (file.length() != 0){
            return false;
        } else{
            return true;
        }
    }

    public void writeToFile(String input) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filename)) {

            fileWriter.write(input);
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFile() throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

}
    //запись и чтение в файл и из файла FileWriter и FileReader
    //проверка на существование файла (метод exists класса File по имени файла)
    //проверка на то пустой ли файл по его длине
