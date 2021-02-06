package dao.impl.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL extends SQLBase {
    @Override
    public Connection getConnection() {
        Connection connection = null;

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
}
