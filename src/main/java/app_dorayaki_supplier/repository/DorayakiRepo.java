package app_dorayaki_supplier.repository;

import app_dorayaki_supplier.models.Dorayaki;
import app_dorayaki_supplier.services.DatabaseService;

import java.sql.*;
import java.util.ArrayList;

public class DorayakiRepo {
    public static final Connection conn = DatabaseService.getService().getConn();

    public ArrayList<Dorayaki> getDorayakis() throws SQLException {
        ArrayList<Dorayaki> dorayakis = new ArrayList<Dorayaki>();
        String query = "SELECT * FROM dorayakis";
        Statement stmt = this.conn.createStatement();
        ResultSet res = stmt.executeQuery(query);
        while (res.next()) {
            Dorayaki dor = new Dorayaki();
            dor.setId(res.getLong("id"));
            dor.setName(res.getString("dorayaki_name"));
            dorayakis.add(dor);
        }
        return dorayakis;
    }
}
