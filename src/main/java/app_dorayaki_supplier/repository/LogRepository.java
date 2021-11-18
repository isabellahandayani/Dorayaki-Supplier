package app_dorayaki_supplier.repository;

import java.sql.*;

import app_dorayaki_supplier.services.DatabaseService;

public class LogRepository {
    public static final Connection conn = DatabaseService.getService().getConn();

    public static void insertLog(String ip, String endpoint) throws SQLException {
        try {
            String query = "INSERT INTO log_request VALUES(null, '" + ip + "', '" + endpoint + "', now())";
            Connection conn = DatabaseService.getService().getConn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Fail To Log");
            e.printStackTrace();
        }
    }

    public static Boolean checkLimit(String ip, String endpoint) throws SQLException {
        /*
        Returns True if limit is reached
        */
        try {
            String query = "SELECT COUNT(*) FROM log_request WHERE ip='" + ip + "' AND endpoint='" + endpoint + "' AND timestamp > (NOW() - INTERVAL 1 MINUTE)";
            Connection conn = DatabaseService.getService().getConn();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(query);
            res.next();
            return res.getInt(1) > 10;
        } catch (Exception e) {
            System.out.println("Fail To Check");
            e.printStackTrace();
            return true;
        }
    }

    public static void deleteLog(String ip, String endpoint) throws SQLException {
        try {
            String query = "DELETE FROM log_request WHERE ip='" + ip + "' AND endpoint='" + endpoint + "'";
            System.out.println(query);
            Connection conn = DatabaseService.getService().getConn();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Fail to Delete Log");
            e.printStackTrace();
        }
    }
}
