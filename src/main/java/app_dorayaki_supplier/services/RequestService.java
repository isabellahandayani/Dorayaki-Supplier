package app_dorayaki_supplier.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.http.client.ClientProtocolException;

import app_dorayaki_supplier.models.Request;
import app_dorayaki_supplier.repository.LogRepository;
import app_dorayaki_supplier.repository.RequestRepo;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class RequestService {
  private static final RequestRepo repo = new RequestRepo();
  @Resource WebServiceContext context;

  @WebMethod
  public ArrayList<Request> getRequests() throws SQLException, ClientProtocolException, IOException {
    HttpServletRequest request = (HttpServletRequest)context.getMessageContext().get(MessageContext.SERVLET_REQUEST);
    String ip = request.getRemoteAddr();
    String endpoint = "/request";
    LogRepository.insertLog(ip, endpoint);
    if (LogRepository.checkLimit(ip, endpoint)) {
        return new ArrayList<Request>();
    } else {
        try {
            return repo.getAllRequest();
        } catch (Exception e) {
            System.out.println("FAIL TO GET REQUESTS");
            e.printStackTrace();
            return new ArrayList<Request>();
        }
    }
  }
}
