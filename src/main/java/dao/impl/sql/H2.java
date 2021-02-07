package dao.impl.sql;

import dao.impl.sql.base.SQLBase;
import util.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class H2 extends SQLBase {
    @Override
    public Connection getConnection() {
        //запусти бат в C:\Users\User\h2\bin для ремоут входа, для Embded
        Connection connection = null;

        try {
            Class.forName(Constants.H2.DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(
                    Constants.H2.CONNECTION_URL,
                    Constants.H2.LOGIN_DB,
                    Constants.H2.PASSWORD_DB
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}