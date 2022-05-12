package Context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

    /*USE BELOW METHOD FOR YOUR DATABASE CONNECTION FOR BOTH SINGLE AND MULTILPE SQL SERVER INSTANCE(s)*/
 /*DO NOT EDIT THE BELOW METHOD, YOU MUST USE ONLY THIS ONE FOR YOUR DATABASE CONNECTION*/
    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }

    /*Insert your other code right after this comment*/
 /*Change/update information of your database connection, DO NOT change name of instance variables in this class*/
    private final String serverName = "localhost";
    private final String dbName = "SE1613";
    private final String portNumber = "1433";
    private final String userID = "sa";
    private final String password = "123456";

    public static void main(String[] args) {
        try {
            DBContext dBContext = new DBContext();
            if (dBContext.getConnection() != null) {
                System.out.println("Kết nối thành công");
            } else {
                System.out.println("Kết nối thất bại");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
