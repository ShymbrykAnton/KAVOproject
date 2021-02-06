package util.io;

import blogic.model.Person;

import java.io.*;
import java.util.Iterator;
import java.util.List;

import static util.Constants.Messages.*;

public class FileHelper {
    public boolean fileExists(String filename) {
        File tempFile = new File(filename);
        return tempFile.exists();
    }

    public boolean isFileEmpty(String filename) {
        File file = new File(filename);
        return file.length() == 0;
    }

    public void ageValidation(int age) throws IllegalArgumentException {
        if (age > 120) {
            throw new IllegalArgumentException(ILLEGAL_AGE);
        }
    }

    public void idValidation(List<Person> personList, long id) throws IllegalArgumentException {
        if (isIdLegal(personList, id)) {
            throw new IllegalArgumentException(ILLEGAL_PERSON_ID);
        }
    }

    private boolean isIdLegal(List<Person> personList, long id) {
        Iterator<Person> iterator = personList.iterator();
        while (iterator.hasNext()) {
            Person iterPerson = iterator.next();
            if (iterPerson.getId() == id) {
                return true;
            }
        }
        return false;
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