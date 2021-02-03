package dao.impl.nosql;

import blogic.model.Person;
import com.datastax.driver.core.*;
import dao.IDatabaseController;

import java.util.ArrayList;
import java.util.List;

public class Cassandra implements IDatabaseController {
    private Session session;
    private Cluster cluster;

    private Session getSession() {
        cluster = Cluster.builder().addContactPoint("localhost").build();
        session = cluster.connect("cassandra");
        return session;
    }

    @Override
    public void addToDatabase(Person person) {
        String create = "INSERT INTO persons (id,first_name,last_name,age,city) VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = getSession().prepare(create);
        BoundStatement boundStatement = new BoundStatement(preparedStatement);
        session.execute(boundStatement.bind((int)person.getId(), person.getFName(), person.getLName(),(int)person.getAge(), person.getCity()));
        cluster.close();
    }

    @Override
    public List<Person> readFromDatabase() {
        List<Person> personList = new ArrayList<>();
        Person person;
        ResultSet resultset = getSession().execute("SELECT * FROM persons");
        for (Row row : resultset) {
            int id = (row.getInt("id"));
            String first_name = row.getString("first_name");
            String last_name = row.getString("last_name");
            int age = row.getInt("age");
            String city = row.getString("city");
            person = new Person(id,first_name,last_name, (byte) age,city);
            personList.add(person);
        }
        cluster.close();
        return personList;
    }

    @Override
    public void updateDataInPerson(long id, String[] newValue) {
        String update = "";
        if (!newValue[1].equals("")) {
            update += "first_name=" + "'" + newValue[1] + "',";
        }
        if (!newValue[2].equals("")) {
            update += "last_name=" + '\'' + newValue[2] + "',";
        }
        if (!newValue[3].equals("")) {
            update += "age="  + newValue[3] + ",";
        }
        if (!newValue[4].equals("")) {
            update += "city=" + '\'' + newValue[4] + "'";
        }
        String finalUpdate = "UPDATE persons SET " + update + " WHERE id = " + (int)id;
        getSession().execute(finalUpdate);
        cluster.close();
    }

    @Override
    public void removePersonsFromList(long id) {
        String delete = "DELETE FROM persons WHERE id = ?";
        PreparedStatement preparedStatement = getSession().prepare(delete);
        BoundStatement boundStatement = new BoundStatement(preparedStatement);
        session.execute(boundStatement.bind((int)id));
        cluster.close();
    }
}