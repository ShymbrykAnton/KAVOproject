package dao;

import blogic.model.Person;

import java.util.List;

public interface IDatabaseController {
    public abstract void addToDatabase(Person person);

    List<Person> readFromDatabase();

    void updateDataInPerson(long id, String[] newValue);

    void removePersonsFromList(long id);
}
