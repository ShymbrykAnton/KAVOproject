package blogic.filetype.binary;

import blogic.filetype.executor.Executable;
import blogic.model.Person;
import util.update.Update;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


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
    public void update(long id, String[] newValue, List<Person> personList, String filename) {
        Update update = new Update();
        update.updating(id, newValue, personList);
        create(filename, personList);
    }

    @Override
    public void delete(long id, List<Person> personList, String filename) {
        personList.removeIf(person -> person.getId() == id);
        create(filename, personList);
    }

    @Override
    public void clearAll(String filename) {
        create(filename, new ArrayList<>());
    }
}