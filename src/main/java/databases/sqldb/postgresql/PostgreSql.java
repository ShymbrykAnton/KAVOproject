package databases.sqldb.postgresql;

import blogic.model.Person;

import java.sql.*;
import java.util.List;

public class PostgreSql {
    private Connection connection;

    private Connection getConnection() {
        String connectionUrl = "jdbc:postgresql://localhost:5432/person";
        String loginDb = "postgres";
        String password = "11.04shekel1948";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(connectionUrl, loginDb, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void createPerson(Person person) {
        //INSERT INTO persons(id_person, first_name, last_name, age, city) VALUES (1,'Tony','Tester',12,'Gavno')
        String insert = "INSERT INTO persons (id_person,first_name,last_name,age,city) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = getConnection().prepareStatement(insert);
            ps.setLong(1, person.getId());
            ps.setString(2, person.getFName());
            ps.setString(3, person.getLName());
            ps.setInt(4, person.getAge());
            ps.setString(5, person.getCity());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Person> readTable(List<Person> personList) {
        String select = "SELECT * FROM persons";
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            while (resultSet.next()) {
                Person person = new Person(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getByte(4),resultSet.getString(5));
                personList.add(person);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return personList;
    }

    public void update(long id, String[] newValue) {
        String update = "";
        if (!newValue[1].equals("")) {
            update += "first_name=" + "'" + newValue[1] + "',";
        }
        if (!newValue[2].equals("")) {
            update += "last_name=" + '\'' + newValue[2] + "',";
        }
        if (!newValue[3].equals("")) {
            update += "age=" + '\'' + newValue[3] + "',";
        }
        if (!newValue[4].equals("")) {
            update += "city=" + '\'' + newValue[4] + "'";
        }
        //UPDATE persons SET first_name='asd',last_name='asd',age =15,city='asd' WHERE id_person = 2
        String finalUpdate = "UPDATE persons SET " + update + " WHERE id_person = " + id;
        try {
            PreparedStatement ps = getConnection().prepareStatement(finalUpdate);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void delete (long id) {
        String delete = "DELETE FROM persons WHERE id_person =" + id;
        try {
            PreparedStatement ps = getConnection().prepareStatement(delete);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}