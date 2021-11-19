package app_dorayaki_supplier.models;

public class Dorayaki {
    private Long id;
    private String dorayaki_name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return dorayaki_name;
    }

    public void setName(String dorayaki_name) {
        this.dorayaki_name = dorayaki_name;
    }
}
