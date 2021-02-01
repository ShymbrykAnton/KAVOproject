package dao.impl.sql;

import blogic.model.Person;
import dao.IDatabaseController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class SQLBase implements IDatabaseController {

    public abstract Connection getConnection();


    public void addToDatabase(Person person) {
        String insert = "INSERT INTO persons (id,first_name,last_name,age,city) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(insert)){
            ps.setLong(1, person.getId());
            ps.setString(2, person.getFName());
            ps.setString(3, person.getLName());
            ps.setByte(4, person.getAge());
            ps.setString(5, person.getCity());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public List<Person> readFromDatabase() {
        String select = "SELECT * FROM persons";
        List<Person> personList = new ArrayList<>();
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getLong(1));
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

    public void updateDataInPerson(long id, String[] newValue) {
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

    public void removePersonsFromList (long id) {
        String delete = "DELETE FROM persons WHERE id =" + id;
        try {
            PreparedStatement ps = getConnection().prepareStatement(delete);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}

