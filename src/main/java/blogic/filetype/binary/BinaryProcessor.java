package blogic.filetype.binary;

import blogic.filetype.executor.Executable;
import blogic.model.Person;

import java.io.IOException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static util.Constants.View.*;
import static util.Constants.Config.*;


public class BinaryProcessor implements Executable {
    @Override
    public void create(String fileName, List<Person> persons) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(fileName);
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(persons);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Person> read(String fileName) {
        FileInputStream fis;
        List<Person> person1 = new ArrayList<>();
        try {
            fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            @SuppressWarnings("unchecked")
            List<Person> person = (List<Person>) ois.readObject();
            return person;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return person1;
    }

    @Override
    public void update(long id, String[] updatingTypeValue, String[] newValue, List<Person> personList) {
        updating(id, updatingTypeValue, newValue, personList);
    }

    public static void updating(long id, String[] updatingTypeValue, String[] newValue, List<Person> personList) {
        Iterator<Person> iterator = personList.iterator();
        Person newPerson = new Person();
        int personIndex = 0;
        while (iterator.hasNext()) {
            Person item = iterator.next();
            if (item.getId() == id) {
                newPerson = item;
                personIndex = personList.indexOf(newPerson);
                for (int count = 0; count < 5; count++) {
                    if (!newValue[count].equals("")) {
                        switch (updatingTypeValue[count]) {
                            case ID:
                                newPerson.setId(Long.parseLong(newValue[count]));
                                break;
                            case FIRST_NAME:
                                newPerson.setFName(newValue[count]);
                                break;
                            case LAST_NAME:
                                newPerson.setLName(newValue[count]);
                                break;
                            case AGE:
                                newPerson.setAge(Integer.parseInt(newValue[count]));
                                break;
                            case CITY:
                                newPerson.setCity(newValue[count]);
                                break;
                            default:
                                throw new IllegalArgumentException();
                        }
                    }
                }
            }
        }
        if (!newPerson.equals(new Person())) {
            personList.set(personIndex, newPerson);
        }
    }

    @Override
    public void delete(long id, List<Person> personList) {
        personList.removeIf(item -> item.getId() == id);
    }
}