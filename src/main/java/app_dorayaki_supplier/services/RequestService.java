package app_dorayaki_supplier.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;

import app_dorayaki_supplier.models.Request;
import app_dorayaki_supplier.repository.RequestRepo;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class RequestService {
  private static final RequestRepo repo = new RequestRepo();
  @Resource WebServiceContext context;

  @WebMethod
  public ArrayList<Request> getRequests() throws SQLException, ClientProtocolException, IOException {
    return repo.getAllRequest();
  }
}
