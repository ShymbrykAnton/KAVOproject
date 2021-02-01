package dao;

import blogic.filetype.executor.Executable;
import blogic.model.Person;

import java.util.Iterator;
import java.util.List;

public class DbProcessor implements Executable {
    private final IDatabaseController iDatabaseController;

    public DbProcessor(IDatabaseController iDatabaseController) {
        this.iDatabaseController = iDatabaseController;
    }

    @Override
    public void create(String fileName, List<Person> persons) {
        Iterator<Person> iterator = persons.iterator();
        while (iterator.hasNext()) {
            Person iterPerson = iterator.next();
            iDatabaseController.addToDatabase(iterPerson);
        }
    }

    @Override
    public List<Person> read(String fileName) {
        return iDatabaseController.readFromDatabase();
    }

    @Override
    public void update(long id, String[] newValue, List<Person> personList) {
        iDatabaseController.updateDataInPerson(id, newValue);
    }

    @Override
    public void delete(long id, List<Person> personList) {
        iDatabaseController.removePersonsFromList(id);
    }
}
