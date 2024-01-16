package wsdl;

import com.example.authenticate.AuthenticationPortType;
import com.example.authenticate.Credentials;

import javax.jws.WebService;

@WebService(endpointInterface = "com.example.authenticate.AuthenticationPortType")
public class AuthenticationPortTypeImpl implements AuthenticationPortType {
    @Override
    public boolean authenticate(Credentials credentials) {
        return credentials.getUsername().endsWith(".teacher") && credentials.getPassword().endsWith(".teacher");
    }
}
