package app_dorayaki_supplier.repository;

import app_dorayaki_supplier.models.*;
import app_dorayaki_supplier.services.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.*;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class DorayakiRepo {
    public static final Connection conn = DatabaseService.getService().getConn();

    public ArrayList<Dorayaki> getDorayakis() throws IOException {
        try {
            String url = "http://localhost:5000/dorayaki";
            HttpGet request = new HttpGet(url);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(request);

            Charset encoding = Charset.forName("UTF-8");

            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, encoding);
            JSONObject json = new JSONObject(result);
            JSONArray arr = json.getJSONArray("data");

            ArrayList<Dorayaki> dorayakis = new ArrayList<Dorayaki>();

            for (int i = 0; i < arr.length(); i++) {
                dorayakis.add(new Dorayaki(arr.getJSONObject(i)));
            }

            httpClient.close();
            response.close();

            return dorayakis;
        } catch (Exception e) {
            System.out.println("Error Geting Dorayaki");
            e.printStackTrace();
            return null;
        }
    }
}