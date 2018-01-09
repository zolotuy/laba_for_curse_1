package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by zolotuy on 02.01.2018.
 */
public abstract class Dao {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/Chair";
        String userName = "postgres";
        String password = "street777";
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(url, userName, password);
        return connection;
    }

}
