package blogic.filetype.binary;

import blogic.filetype.executor.Executable;
import blogic.model.Person;

import java.io.IOException;

import java.io.*;
import java.util.Iterator;
import java.util.List;

import static util.Constants.View.*;


public class BinaryProcessor implements Executable {
    @Override
    public void create(String fileName, List<Person> persons) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream outputStream = new ObjectOutputStream(fos);
        outputStream.writeObject(persons);
        outputStream.close();
    }

    @Override
    public List<Person> read(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        @SuppressWarnings("unchecked")
        List<Person> person = (List<Person>) ois.readObject();
        return person;
    }

    @Override
    public void update(long id, String valueToBeUpdated, String valueToChange) throws IOException, ClassNotFoundException {
        Iterator<Person> iterator = personList.iterator();
        Person reqPerson;
        while (iterator.hasNext()) {
            Person item = iterator.next();
            if (item.getId() == id) {
                reqPerson = item;
                switch (valueToBeUpdated) {
                    case ID:
                        reqPerson.setId(Long.parseLong(valueToChange));
                    case FIRST_NAME:
                        reqPerson.setFName(valueToChange);
                    case LAST_NAME:
                        reqPerson.setLName(valueToChange);
                    case AGE:
                        reqPerson.setAge(Integer.parseInt(valueToChange));
                    case CITY:
                        reqPerson.setCity(valueToChange);
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }
        }
    }

    @Override
    public void delete(long id)  {
        personList.removeIf(item -> item.getId() == id);
    }
}