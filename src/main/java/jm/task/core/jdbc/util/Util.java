package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static final String URL = "jdbc:mysql://localhost:3306/1.1.4_db?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        Driver driver;

        {
            try {
                driver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(driver);
                System.out.println("Драйвер зарегистрировался");
            } catch (SQLException e) {
                System.err.println("Драйвер не зарегистрировался");
            }

            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                if (!connection.isClosed()) { System.out.println("Соединение установлено"); }
            } catch (SQLException ex) {
                System.err.println("Соединение не установлено");
            }
        }

        return connection;
    }
}
