package dev.asjordi.persistence.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDatabase {

    private static final String URL = "jdbc:mysql://localhost:3306/postal_codes_mx?useTimezone=true&serverTimezone=UTC";

    private ConnectionDatabase() {}

    public static Connection getInstance() throws SQLException {
        return DriverManager.getConnection(URL, databaseProperties());
    }

    private static Properties databaseProperties() {
        Properties p = new Properties();
        p.setProperty("user", "root");
        p.setProperty("password", System.getenv("password_mysql"));
        return p;
    }

}
