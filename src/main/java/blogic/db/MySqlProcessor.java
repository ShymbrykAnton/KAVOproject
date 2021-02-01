package blogic.db;

import blogic.filetype.executor.Executable;
import blogic.model.Person;
import databases.sqldb.mysql.MySQL;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MySqlProcessor implements Executable {
    private final MySQL mySQL = new MySQL();

    @Override
    public void create(String fileName, List<Person> persons) {
        Iterator<Person> iterator = persons.iterator();
        while (iterator.hasNext()) {
            Person iterPerson = iterator.next();
            mySQL.createPerson(iterPerson);
        }
    }

    @Override
    public List<Person> read(String fileName) {
        List<Person> personList = new ArrayList<>();
        return mySQL.readTable(personList);
    }

    @Override
    public void update(long id, String[] newValue, List<Person> personList) {
        mySQL.update(id, newValue);
    }

    @Override
    public void delete(long id, List<Person> personList) {
        mySQL.delete(id);
    }
}
