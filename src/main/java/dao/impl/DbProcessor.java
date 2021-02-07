package dao.impl;

import blogic.filetype.executor.Executable;
import blogic.model.Person;
import dao.IDatabaseController;

import java.util.List;

public class DbProcessor implements Executable {
    private final IDatabaseController iDatabaseController;

    public DbProcessor(IDatabaseController iDatabaseController) {
        this.iDatabaseController = iDatabaseController;
    }

    @Override
    public void create(String fileName, List<Person> persons) {
        for (Person iterPerson : persons) {
            iDatabaseController.addToDatabase(iterPerson);
        }
    }

    @Override
    public List<Person> read(String fileName) {
        return iDatabaseController.readFromDatabase();
    }

    @Override
    public void update(long id, String[] newValue, List<Person> personList, String filename) {
        iDatabaseController.updateDataInPerson(id, newValue);
    }

    @Override
    public void delete(long id, List<Person> personList, String filename) {
        iDatabaseController.removePersonsFromList(id);
    }
}
