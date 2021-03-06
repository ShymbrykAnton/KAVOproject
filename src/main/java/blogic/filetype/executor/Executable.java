package blogic.filetype.executor;

import blogic.model.Person;

import java.util.List;


public interface Executable {
    void create(String fileName, List<Person> persons);

    List<Person> read(String fileName);

    void update(long id, String[] newValue, List<Person> personList, String filename);

    void delete(long id, List<Person> personList, String filename);

    void clearAll(String filename);
}