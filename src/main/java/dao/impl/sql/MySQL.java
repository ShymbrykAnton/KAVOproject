package dao.impl.sql;

import dao.impl.sql.base.SQLBase;
import util.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL extends SQLBase {
    @Override
    public Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(Constants.MySQL.DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(
                    Constants.MySQL.CONNECTION_URL,
                    Constants.MySQL.LOGIN_DB,
                    Constants.MySQL.PASSWORD_DB
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
