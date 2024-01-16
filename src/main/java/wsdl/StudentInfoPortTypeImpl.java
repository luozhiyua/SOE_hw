package wsdl;
import com.example.archive.StudentInfoPortType;
import com.example.archive.StudentInfoSearch;
import com.example.authenticate.AuthenticationPortType;
import com.example.authenticate.Credentials;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

@WebService(endpointInterface = "com.example.archive.StudentInfoPortType")
public class StudentInfoPortTypeImpl implements StudentInfoPortType {
    URL authenticateWsdlURL = new URL("http://localhost:8080/authenticate?wsdl");
    Service authenticateService = Service.create(authenticateWsdlURL, new QName("http://wsdl/", "AuthenticationPortTypeImplService"));
    AuthenticationPortType authenticatePort = authenticateService.getPort(AuthenticationPortType.class);

    public StudentInfoPortTypeImpl() throws MalformedURLException {
    }

    @Override
    public String getStudentInfo(StudentInfoSearch studentInfoSearch) {
        Credentials credentials = new Credentials();
        credentials.setUsername(studentInfoSearch.getAuthName());
        credentials.setPassword(studentInfoSearch.getAuthPassword());
        if(authenticatePort.authenticate(credentials)){
            return "成功获取到学生 " + studentInfoSearch.getStudentID() + " 档案信息";
        }else {
            return "未通过统一认证！";
        }
    }
}