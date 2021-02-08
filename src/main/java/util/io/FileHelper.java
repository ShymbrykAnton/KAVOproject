package util.io;

import blogic.model.Person;

import java.io.*;
import java.util.List;

import static util.Constants.Messages.*;

public class FileHelper {
    public boolean fileExists(String filename) {
        File tempFile = new File(filename);
        return tempFile.exists();
    }

    public void ageValidation(int age) throws IllegalArgumentException {

        if (age > 100) {
            throw new IllegalArgumentException(ILLEGAL_AGE);
        }
    }

    public void idValidationForCreate(List<Person> personList, long id) throws IllegalArgumentException {
        if (isIdLegal(personList, id)) {
            throw new IllegalArgumentException(ILLEGAL_PERSON_ID);
        }
    }

    public void idValidationForUpdDel(List<Person> personList, long id) throws IllegalArgumentException {
        if (!isIdLegal(personList, id)) {
            throw new IllegalArgumentException(ILLEGAL_PERSON_ID);
        }
    }

    private boolean isIdLegal(List<Person> personList, long id) {
        for (Person iterPerson : personList) {
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

    public String getFile(String filename) {
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