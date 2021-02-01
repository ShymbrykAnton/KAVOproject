package databases.sqldb.mysql;

import blogic.model.Person;

import java.sql.*;
import java.util.List;

public class MySQL {

    private Connection connection;

    private Connection getConnection() {

        String connectionUrl = "jdbc:mysql://localhost:3306/public";
        String loginDb = "root";
        String password = "1234";
        try {
            Class.forName("com.mysql.jdbc.Driver");
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
        String insert = "INSERT INTO persons (id,first_name,last_name,age,city) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = getConnection().prepareStatement(insert);
            ps.setString(1, String.valueOf(person.getId()));
            ps.setString(2, person.getFName());
            ps.setString(3, person.getLName());
            ps.setString(4, String.valueOf(person.getAge()));
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
                Person person = new Person();
                person.setId(resultSet.getInt(1));
                person.setFName(resultSet.getString(2));
                person.setLName(resultSet.getString(3));
                person.setAge(resultSet.getByte(4));
                person.setCity(resultSet.getString(5));
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
        String finalUpdate = "UPDATE persons SET " + update + " WHERE id = " + id;
        try {
            PreparedStatement ps = getConnection().prepareStatement(finalUpdate);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void delete (long id) {
        String delete = "DELETE FROM persons WHERE id =" + id;
        try {
            PreparedStatement ps = getConnection().prepareStatement(delete);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
