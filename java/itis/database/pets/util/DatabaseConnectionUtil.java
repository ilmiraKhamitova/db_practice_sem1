package itis.database.pets.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionUtil {
    private static Connection connection;
    private static final String DRIVER = "org.postgresql.Driver";

    public static Connection getConnection(String url, String user, String password) {
        if (connection == null) {
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(
                        url,
                        user,
                        password
                );
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
