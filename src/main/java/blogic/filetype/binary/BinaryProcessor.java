package blogic.filetype.binary;

import blogic.filetype.executor.Executable;
import blogic.model.Person;
import java.io.IOException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryProcessor implements Executable {
    @Override
    public void write(String fileName, List<Person> personList) throws IOException {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(personList);
        }

    }

    @Override
    public List<Person> read(String fileName) throws IOException {
            List<Person> newPerson = new ArrayList<>();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                newPerson = (ArrayList) ois.readObject();
                ois.close();

                return newPerson;
            } catch (Exception ex) {

                System.out.println(ex.getMessage());

                return newPerson;


            }}

    @Override
    public void delete(long id) throws IOException, ClassNotFoundException {

    }
}