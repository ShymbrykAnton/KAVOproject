package blogic.db;

import blogic.filetype.executor.Executable;
import blogic.model.Person;
import databases.sqldb.mysql.MySQL;
import databases.sqldb.postgresql.PostgreSql;
import util.io.FileHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PostgreSQLProcessor implements Executable {
    private final PostgreSql postgreSql = new PostgreSql();
    FileHelper fileHelper = new FileHelper();

    @Override
    public void create(String fileName, List<Person> persons) {
        for (Person iterPerson : persons) {
            postgreSql.createPerson(iterPerson);
        }
    }

    @Override
    public List<Person> read(String fileName) {
        List<Person> personList = new ArrayList<>();
        return postgreSql.readTable(personList);
    }

    @Override
    public void update(long id, String[] newValue, List<Person> personList) {
        postgreSql.update(id, newValue);
    }

    @Override
    public void delete(long id, List<Person> personList) {
        postgreSql.delete(id);
    }
}
