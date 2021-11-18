package app_dorayaki_supplier.services;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import app_dorayaki_supplier.models.Dorayaki;
import app_dorayaki_supplier.repository.DorayakiRepo;

import java.util.ArrayList;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class DorayakiService {
    private static final DorayakiRepo repo = new DorayakiRepo();

    @WebMethod
    public ArrayList<Dorayaki> getDorayakis(){
        try {
            return repo.getDorayakis();
        } catch (Exception e){
            System.out.println("FAIL TO GET DORAYAKIS");
            e.printStackTrace();
            return new ArrayList<Dorayaki>();
        }
    }

}