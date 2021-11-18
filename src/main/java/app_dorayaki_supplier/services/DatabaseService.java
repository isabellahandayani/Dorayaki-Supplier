package app_dorayaki_supplier.services;

import java.sql.*;

public class DatabaseService {
    private Connection conn;
    private static DatabaseService db = new DatabaseService();

    public DatabaseService() {
        String URL = "jdbc:mysql://localhost/dorayaki_factory";
        String USER_NAME = "root";
        String PWD = "123456";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(URL, USER_NAME, PWD);
            System.out.println("CONNECTED TO DORAYAKI_FACTORY");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("COULDNT ACCESS DATABASE");
        }
    }

    public Connection getConn(){
        return this.conn;
    }

    public static DatabaseService getService(){
        return DatabaseService.db;
    }
}