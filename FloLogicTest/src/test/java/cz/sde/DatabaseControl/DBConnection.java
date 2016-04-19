package cz.sde.DatabaseControl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

    public static Connection getDBConnection() {

        Connection connection = null;


        try {
            connection = DriverManager
                    .getConnection(InitDB.getConnectionString());

            return connection;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
