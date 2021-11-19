package app_dorayaki_supplier.models;

import java.sql.Date;

import org.json.JSONObject;

public class Request {
  private Integer id_dorayaki;
  private Integer stok_added;
  private String status;
  private Date created_at;

  public Request(Integer id_dorayaki, Integer stok_added, String status, Date created_at) {
    this.id_dorayaki = id_dorayaki;
    this.stok_added = stok_added;
    this.status = status;
    this.created_at = created_at;
  }

  public Request(JSONObject json) {
    this.id_dorayaki = json.getInt("id_dorayaki");
    this.stok_added = json.getInt("stok_added");
    this.status = json.getString("status");
    // this.created_at = Date.valueOf(json.getString("createdAt"));
  }

  public Integer getIdDorayaki() {
    return id_dorayaki;
  }

  public void setIdDorayaki(Integer id_dorayaki) {
    this.id_dorayaki = id_dorayaki;
  }

  public Integer getStokAdded() {
    return stok_added;
  }

  public void setStokAdded(Integer stok_added) {
    this.stok_added = stok_added;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getCreatedAt() {
    return created_at;
  }

  public void setCreatedAt(Date created_at) {
    this.created_at = created_at;
  }
}
