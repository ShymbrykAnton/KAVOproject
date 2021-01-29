package blogic.filetype.executor;

import blogic.model.Person;

import java.util.List;


public interface Executable {
    void create(String fileName, List<Person> persons);

    List<Person> read(String fileName);

    void update(long id, String[] updatingTypeValue, String[] newValue);
    //todo update default + разделить его на два метода

    void delete(long id);
}