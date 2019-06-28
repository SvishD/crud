package app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    public static Connection getConnection() {
        try {

            StringBuilder url = new StringBuilder();

            url.append(PropertyReader.get("url")).
                    append(PropertyReader.get("dbname")).
                    append("?user=").
                    append(PropertyReader.get("username")).
                    append("&password=").
                    append(PropertyReader.get("pass")).
                    append("&serverTimezone=").
                    append(PropertyReader.get("serverTimezone"));

            System.out.println("URL: " + url + "\n");

            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(url.toString());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
