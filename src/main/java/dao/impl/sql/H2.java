package dao.impl.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2 extends SQLBase {
    @Override
    public Connection getConnection() {
        //запусти бат в C:\Users\User\h2\bin для ремоут входа, для Embded
        Connection connection = null;
        String connectionUrl = "jdbc:h2:tcp://localhost/~/H2";
        String loginDb = "sa";
        String password = "";
        try {
            Class.forName("org.h2.Driver");
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