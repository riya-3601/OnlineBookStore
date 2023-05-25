package Config;

import java.sql.*;

public class JdbcConnection {

    private static String  user  = "riya3601";
    private static String  password ="riya360127";
    private static String connStr =  "jdbc:oracle:thin:@localhost:1521:dbs3";
    //String static database = "dbs3.cs.umb.edu";

    private  static Connection conn;


    public static void init() throws SQLException {

        // Load the Oracle JDBC driver
//        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
//
//        conn = DriverManager.getConnection (connStr,
//                user, password);
//
//        conn.setAutoCommit(true);
//        System.out.println ("connected.");

    }

    public Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

        conn = DriverManager.getConnection (connStr,
                user, password);

        conn.setAutoCommit(true);
        //System.out.println ("connected.");
        return conn;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
