package app_dorayaki_supplier.models;

import org.json.JSONObject;

public class Dorayaki {
    private Integer id;
    private String dorayaki_name;

    public Dorayaki (Integer id, String dorayaki_name) {
        this.id = id;
        this.dorayaki_name = dorayaki_name;
    }

    public Dorayaki(JSONObject json) {
        this.id = json.getInt("id");
        this.dorayaki_name = json.getString("dorayaki_name");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return dorayaki_name;
    }

    public void setName(String dorayaki_name) {
        this.dorayaki_name = dorayaki_name;
    }
}
