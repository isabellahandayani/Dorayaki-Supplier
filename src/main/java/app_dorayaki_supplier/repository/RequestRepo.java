package app_dorayaki_supplier.repository;

import static app_dorayaki_supplier.utils.Constant.ENDPOINT_URL;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import app_dorayaki_supplier.models.Request;
import app_dorayaki_supplier.services.DatabaseService;

public class RequestRepo {
  public static final Connection conn = DatabaseService.getService().getConn();

  public ArrayList<Request> getAllRequest() throws ClientProtocolException, IOException {
    HttpGet get = new HttpGet(ENDPOINT_URL + "/request");
    CloseableHttpClient client = HttpClients.createDefault();

    CloseableHttpResponse response = client.execute(get);

    // int responseStatus = response.getStatusLine().getStatusCode();

    Charset encoding = Charset.forName("UTF-8");

    String responseBody = EntityUtils.toString(response.getEntity(), encoding);
    JSONObject json = new JSONObject(responseBody);
    JSONArray dataArr = json.getJSONArray("data");
    ArrayList<Request> requests = new ArrayList<Request>();
    for (int i = 0; i < dataArr.length(); i++) {
      requests.add(new Request(dataArr.getJSONObject(i)));
    }

    return requests;
  }

  public Boolean makeNewRequest(Request r) throws ClientProtocolException, IOException {
    HttpPost post = new HttpPost(ENDPOINT_URL + "/request/create");
    CloseableHttpClient client = HttpClients.createDefault();

    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("id_dorayaki", r.getIdDorayaki().toString()));
    params.add(new BasicNameValuePair("stok_added", r.getStokAdded().toString()));
    post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

    CloseableHttpResponse response = client.execute(post);
    HttpEntity entity = response.getEntity();
    System.out.println(response);
    System.out.println(entity);
    if (response.getStatusLine().getStatusCode() == 200) {
      client.close();
      System.out.println("APA");
      return true;
    } else return false;
  }
}
