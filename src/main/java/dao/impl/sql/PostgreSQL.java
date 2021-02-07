package dao.impl.sql;

import dao.impl.sql.base.SQLBase;
import util.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQL extends SQLBase {
    //если сделать апдейт возраста(за пределами байта) то роизойдет какая то дичь
    @Override
    public Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(Constants.PostgreSQL.DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(
                    Constants.PostgreSQL.CONNECTION_URL,
                    Constants.PostgreSQL.LOGIN_DB,
                    Constants.PostgreSQL.PASSWORD_DB
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
