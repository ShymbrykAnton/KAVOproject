package dao.impl.nosql;

import blogic.model.Person;
import com.datastax.driver.core.*;
import dao.IDatabaseController;
import util.Constants;

import java.util.ArrayList;
import java.util.List;

public class Cassandra implements IDatabaseController {
    private Session session;
    private Cluster cluster;

    private Session getSession() {
        cluster = Cluster.builder().addContactPoint(Constants.Cassandra.LOGIN_DB).build();

        session = cluster.connect(Constants.Cassandra.KEYSPACE);

        return session;
    }

    @Override
    public void addToDatabase(Person person) {

        PreparedStatement preparedStatement = getSession().prepare(Constants.Cassandra.INSERT);

        BoundStatement boundStatement = new BoundStatement(preparedStatement);

        session.execute(
                boundStatement.bind(
                        (int) person.getId(),
                        person.getFName(),
                        person.getLName(),
                        (int) person.getAge(),
                        person.getCity()
                )
        );
        cluster.close();
    }

    @Override
    public List<Person> readFromDatabase() {
        List<Person> personList = new ArrayList<>();

        Person person;

        ResultSet resultset = getSession().execute(Constants.Cassandra.SELECT);

        for (Row row : resultset) {

            int id = (row.getInt(Constants.Cassandra.ID_FIELD));
            String first_name = row.getString(Constants.Cassandra.FIRST_NAME_FIELD);
            String last_name = row.getString(Constants.Cassandra.LAST_NAME_FIELD);
            int age = row.getInt(Constants.Cassandra.AGE_FIELD);
            String city = row.getString(Constants.Cassandra.CITY_FIELD);

            person = new Person(id, first_name, last_name, (byte) age, city);

            personList.add(person);
        }
        cluster.close();
        return personList;
    }

    @Override
    public void updateDataInPerson(long id, String[] newValue) {

        PreparedStatement preparedStatement = getSession().prepare(Constants.Cassandra.UPDATE);

        BoundStatement boundStatement = new BoundStatement(preparedStatement);

        session.execute(
                boundStatement.bind(
                        newValue[1],
                        newValue[2],
                        Integer.parseInt(newValue[3]),
                        newValue[4],
                        (int) id
                )
        );
        cluster.close();
    }

    @Override
    public void removePersonsFromList(long id) {

        PreparedStatement preparedStatement = getSession().prepare(Constants.Cassandra.DELETE);
        BoundStatement boundStatement = new BoundStatement(preparedStatement);
        session.execute(boundStatement.bind((int) id));
        cluster.close();
    }

    @Override
    public void clearAll(String filename) {
        ResultSet resultset = getSession().execute(Constants.Cassandra.SELECT);
        PreparedStatement preparedStatement = getSession().prepare(Constants.Cassandra.DELETE);
        BoundStatement boundStatement = new BoundStatement(preparedStatement);
        for (Row row : resultset) {
            int id = (row.getInt(Constants.Cassandra.ID_FIELD));
            session.execute(boundStatement.bind(id));
        }
        cluster.close();
    }
}

