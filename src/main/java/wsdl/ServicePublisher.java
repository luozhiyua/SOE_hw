package wsdl;

import javax.xml.ws.Endpoint;
import java.net.MalformedURLException;

public class ServicePublisher {
    public static void main(String[] args) throws MalformedURLException {
        String address = "http://localhost:8080/authenticate"; // 服务的地址
        Endpoint.publish(address, new AuthenticationPortTypeImpl());
        String address1 = "http://localhost:8080/studentinfo"; // 服务的地址
        Endpoint.publish(address1, new StudentInfoPortTypeImpl());
        String address2 = "http://localhost:8080/checkin"; // 服务的地址
        Endpoint.publish(address2, new CheckInPortTypeImpl());
    }
}
