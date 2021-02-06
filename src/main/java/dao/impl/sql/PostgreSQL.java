package dao.impl.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQL extends SQLBase {
    //если сделать апдейт возраста(за пределами байта) то роизойдет какая то дичь
    @Override
    public Connection getConnection() {
        Connection connection = null;
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
}
