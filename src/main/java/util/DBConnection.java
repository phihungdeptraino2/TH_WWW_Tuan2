package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Nếu dùng MariaDB
    private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/UploadFileServletDB";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver"); // load driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
}
