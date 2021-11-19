package app_dorayaki_supplier.services;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import app_dorayaki_supplier.models.Dorayaki;
import app_dorayaki_supplier.repository.DorayakiRepo;
import app_dorayaki_supplier.repository.LogRepository;

import java.sql.SQLException;
import java.util.ArrayList;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class DorayakiService {
    private static final DorayakiRepo repo = new DorayakiRepo();
    @Resource WebServiceContext context;

    @WebMethod
    public ArrayList<Dorayaki> getDorayakis() throws SQLException {
        HttpServletRequest request = (HttpServletRequest)context.getMessageContext().get(MessageContext.SERVLET_REQUEST);
        String ip = request.getRemoteAddr();
        String endpoint = "/dorayaki";
        LogRepository.insertLog(ip, endpoint);
        if (LogRepository.checkLimit(ip, endpoint)) {
            return new ArrayList<Dorayaki>();
        } else {
            try {
                return repo.getDorayakis();
            } catch (Exception e) {
                System.out.println("FAIL TO GET DORAYAKIS");
                e.printStackTrace();
                return new ArrayList<Dorayaki>();
            }
        }
    }

}