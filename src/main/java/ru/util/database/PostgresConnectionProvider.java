package ru.util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnectionProvider {
    public static Connection getConnection() {
        try {
            Class.forName(PostgresDBConnectionData.DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to find driver class :(");
        }

        try {
            return DriverManager.getConnection(PostgresDBConnectionData.URL,
                    PostgresDBConnectionData.USERNAME,
                    PostgresDBConnectionData.PASSWORD);
        } catch (SQLException e) {
            System.out.println("Unable to receive connection :(");
            throw new RuntimeException(e);
        }
    }
}
