package org.example;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface LoginService {
    @WebMethod
    public String login(String email, String password);
}
