import org.example.LoginService;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
public class LoginServiceTest {
    // 创建服务URL
    URL url = new URL("http://localhost:8080/LoginService?wsdl");
    QName qname = new QName("http://example.org/", "LoginServiceImplService");
    // 创建服务
    Service service = Service.create(url, qname);
    // 获取服务实现类
    LoginService loginService = service.getPort(LoginService.class);

    public LoginServiceTest() throws MalformedURLException {
    }

    @Test
    public void test1(){
        System.out.println(loginService.login("teacher@nju.teacher.edu.cn", "teacher123"));
    }
    @Test
    public void test2(){
        System.out.println(loginService.login("postgrad@nju.postgrad.edu.cn", "postgrad123"));
    }
    @Test
    public void test3(){
        System.out.println(loginService.login("undergrad@nju.undergrad.edu.cn", "undergrad123"));
    }
    @Test
    public void test4(){
        System.out.println(loginService.login("wrong@example.com", "error123"));
    }
}
