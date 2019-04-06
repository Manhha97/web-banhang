package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static String driver = "com.mysql.jdbc.Driver";
    public static String hostName = "localhost";
    public static String port = "3306";
    public static String databaseName = "a_lt_web?useUnicode=true&characterEncoding=utf-8";
    public static String username = "root";
    public static String password = "";
    public static Connection open() throws SQLException{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Connection conn = DriverManager.getConnection("jdbc:mysql://"+hostName+":"+port+"/"+databaseName+"",username, password);
        return conn;
    }
}