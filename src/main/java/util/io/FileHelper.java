package util.io;

import java.io.*;

public class FileHelper {
    public void saveToFile(String content, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));
        writer.write(content);
        writer.close();

    }

    public String readFromFile(String fileName) throws IOException {
        StringBuilder outputString = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                outputString.append(line);

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return outputString.toString();
    }

}
    //запись и чтение в файл и из файла FileWriter и FileReader
    //проверка на существование файла (метод exists класса File по имени файла)
    //проверка на то пустой ли файл по его длине
