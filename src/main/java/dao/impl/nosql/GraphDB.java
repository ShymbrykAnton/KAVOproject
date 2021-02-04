package dao.impl.nosql;

import blogic.model.Person;
import dao.IDatabaseController;
import org.neo4j.driver.*;

import java.util.*;

public class GraphDB implements IDatabaseController {
    static Driver driver;

    private static Driver getDriver() {
        driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "1234"));
        return driver;
    }

    @Override
    public void addToDatabase(Person person) {
        try (Session session = getDriver().session()) {
            Map<String, Object> params = new HashMap<>();
            params.put("id", person.getId());
            params.put("fname", person.getFName());
            params.put("lname", person.getLName());
            params.put("age", person.getAge());
            params.put("city", person.getCity());
            String update = "CREATE (n:persons {id: $id, fname: $fname, lname: $lname, age: $age, city: $city})";
            session.run(update, params);
        }
        driver.close();
    }

    @Override
    public List<Person> readFromDatabase() {
        List<Person> personList = new ArrayList<>();
        Person person;
        try (Session session = getDriver().session()) {
            Result result = session.run("MATCH (n) RETURN n.id, n.fname, n.lname, n.age, n.city");
            while (result.hasNext()) {
                Record record = result.next();
                person = new Person(record.values().get(0).asLong(),record.values().get(1).asString(),
                        record.values().get(2).asString(),record.values().get(3).asNumber().byteValue()
                ,record.values().get(4).asString());
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
            params.put("id", id);
            params.put("fname", newValue[1]);
            params.put("lname", newValue[2]);
            params.put("age", Integer.parseInt(newValue[3]));
            params.put("city", newValue[4]);
            String update = "MATCH (n { id: $id }) SET n.lname = $lname, n.fname = $fname, n.age = $age, n.city = $city";
            session.run(update, params);
        }
        driver.close();
    }

    @Override
    public void removePersonsFromList(long id) {
        try (Session session = getDriver().session()) {
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            String update = "MATCH (n { id: $id }) DETACH DELETE n";
            session.run(update, params);
        }
        driver.close();
    }
}