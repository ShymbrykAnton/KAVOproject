package dao;

import blogic.model.Person;

import java.util.List;

public interface IDatabaseController {
    void addToDatabase(Person person);

    List<Person> readFromDatabase();

    void updateDataInPerson(long id, String[] newValue);

    void removePersonsFromList(long id);

    void clearAll(String filename);
}
