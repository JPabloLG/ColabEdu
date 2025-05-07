package co.uniquindio.estructuras.colabedu.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.InputStream;
import java.util.Properties;

public class JDBC {

            private static Connection connection;

            public static Connection getConnection() {
                if (connection != null) return connection;

                try (InputStream input = JDBC.class.getClassLoader().getResourceAsStream("db.properties")) {
                    Properties prop = new Properties();
                    prop.load(input);

                    String url = prop.getProperty("db.url");
                    String user = prop.getProperty("db.user");
                    String password = prop.getProperty("db.password");

                    connection = DriverManager.getConnection(url, user, password);
                } catch (Exception e) {
                    System.err.println("DB connection failed: " + e.getMessage());
                }

                return connection;
            }

    public static class TestConnection {
        public static void main(String[] args) {
            try {
                Connection conn = JDBC.getConnection();
                System.out.println("Connected to DB!");
                conn.close();
            } catch (SQLException e) {
                System.out.println("Connection failed: " + e.getMessage());
            }
        }
    }
}



