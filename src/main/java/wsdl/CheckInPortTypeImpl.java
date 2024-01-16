package wsdl;

import com.example.authenticate.AuthenticationPortType;
import com.example.authenticate.Credentials;
import com.example.checkin.CheckInPortType;
import com.example.checkin.CheckInSearch;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

@WebService(endpointInterface = "com.example.checkin.CheckInPortType")
public class CheckInPortTypeImpl implements CheckInPortType {
    URL authenticateWsdlURL = new URL("http://localhost:8080/authenticate?wsdl");
    Service authenticateService = Service.create(authenticateWsdlURL, new QName("http://wsdl/", "AuthenticationPortTypeImplService"));
    AuthenticationPortType authenticatePort = authenticateService.getPort(AuthenticationPortType.class);

    public CheckInPortTypeImpl() throws MalformedURLException {
    }

    @Override
    public String studentCheckIn(CheckInSearch checkInSearch) {
        Credentials credentials = new Credentials();
        credentials.setUsername(checkInSearch.getAuthName());
        credentials.setPassword(checkInSearch.getAuthPassword());
        if(authenticatePort.authenticate(credentials)){
            return "成功获取到学生 " + checkInSearch.getStudentID() + " 签到信息";
        }else {
            return "未通过统一认证！";
        }
    }
}
