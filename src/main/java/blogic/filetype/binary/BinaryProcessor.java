package blogic.filetype.binary;

import blogic.filetype.executor.Executable;
import blogic.model.Person;

import java.io.IOException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static util.Constants.View.*;


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
    public void update(long id, String[] newValue, List<Person> personList) {
        updating(id, newValue, personList);
    }

    public static void updating(long id, String[] newValue, List<Person> personList) {
        Iterator<Person> iterator = personList.iterator();
        int personIndex = 0;
        Person person = new Person();
        Person item;
        while (iterator.hasNext()) {
            item = iterator.next();
            if (item.getId() == id) {
                personIndex = personList.indexOf(item);
                person = refactorPerson(item, newValue);
                break;
            }
        }
        if (!person.equals(new Person())) {
            personList.set(personIndex, person);
        }
    }

    private static Person refactorPerson(Person newPerson, String[] newValue) {
        String[] updatingTypeValue = new String[]{"Id", "Fname", "Lname", "Age", "City"};
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
        return newPerson;
    }

    @Override
    public void delete(long id, List<Person> personList) {
        personList.removeIf(person -> person.getId() == id);
    }
}