package util.io;

import blogic.model.Person;
import util.Constants;


import java.io.*;
import java.util.Iterator;
import java.util.List;

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

    public void idValidation(List<Person> personList, long id) {
        if (isIdLegal(personList, id)) {
            throw new IllegalArgumentException(Constants.Messages.ILLEGAL_PERSON_ID);
        }
    }

    public void ageValidation(byte age) {
        if (age > 100) {
            throw new IllegalArgumentException(Constants.Messages.ILLEGAL_AGE);
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


    public void writeToFile(String input) {
        try (FileWriter fileWriter = new FileWriter(filename)) {

            fileWriter.write(input);
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFile() {
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