package dao.impl.nosql;

import blogic.model.Person;
import dao.IDatabaseController;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import util.Constants;

import java.util.*;

public class GraphDB implements IDatabaseController {
    static Driver driver;

    //зачем статик?
    private static Driver getDriver() {
        driver = GraphDatabase.driver(
                Constants.GraphDB.CONNECTION_URL,
                AuthTokens.basic(Constants.GraphDB.LOGIN_DB, Constants.GraphDB.PASSWORD_DB)
        );
        return driver;
    }

    @Override
    public void addToDatabase(Person person) {
        try (Session session = getDriver().session()) {
            Map<String, Object> params = new HashMap<>();

            params.put(Constants.GraphDB.ID_FIELD, person.getId());
            params.put(Constants.GraphDB.FIRST_NAME_FIELD, person.getFName());
            params.put(Constants.GraphDB.LAST_NAME_FIELD, person.getLName());
            params.put(Constants.GraphDB.AGE_FIELD, person.getAge());
            params.put(Constants.GraphDB.CITY_FIELD, person.getCity());

            session.run(Constants.GraphDB.CREATE, params);
        }
        driver.close();
    }

    @Override
    public List<Person> readFromDatabase() {
        List<Person> personList = new ArrayList<>();

        Person person;
        try (Session session = getDriver().session()) {

            Result result = session.run(Constants.GraphDB.SELECT);

            while (result.hasNext()) {
                Record record = result.next();
                person = new Person(
                        record.values().get(0).asLong(),
                        record.values().get(1).asString(),
                        record.values().get(2).asString(),
                        record.values().get(3).asNumber().byteValue(),
                        record.values().get(4).asString()
                );

                personList.add(person);
            }
        }
        driver.close();
        return personList;
    }

    @Override
    public void updateDataInPerson(long id, String[] newValue) {
        try (Session session = getDriver().session()) {

            Map<String, Object> params = new HashMap<>();

            params.put(Constants.GraphDB.ID_FIELD, id);
            params.put(Constants.GraphDB.FIRST_NAME_FIELD, newValue[1]);
            params.put(Constants.GraphDB.LAST_NAME_FIELD, newValue[2]);
            params.put(Constants.GraphDB.AGE_FIELD, Integer.parseInt(newValue[3]));
            params.put(Constants.GraphDB.CITY_FIELD, newValue[4]);

            session.run(Constants.GraphDB.UPDATE, params);
        }
        driver.close();
    }

    @Override
    public void removePersonsFromList(long id) {
        try (Session session = getDriver().session()) {
            Map<String, Object> params = new HashMap<>();
            params.put(Constants.GraphDB.ID_FIELD, id);
            session.run(Constants.GraphDB.DELETE, params);
        }
        driver.close();
    }

    @Override
    public void clearAll(String filename) {
        try (Session session = getDriver().session()){
            long id;
            Result result = session.run(Constants.GraphDB.SELECT);
            while (result.hasNext()) {
                Record record = result.next();
                id = record.values().get(0).asLong();
                Map<String, Object> params = new HashMap<>();
                params.put(Constants.GraphDB.ID_FIELD, id);
                session.run(Constants.GraphDB.DELETE, params);
            }
        }
        driver.close();
    }
}