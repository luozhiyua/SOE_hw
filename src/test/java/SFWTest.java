import com.example.archive.StudentInfoSearch;
import com.example.checkin.CheckInSearch;
import org.junit.Test;
import com.example.archive.StudentInfoPortType;
import com.example.checkin.CheckInPortType;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class SFWTest {
    URL studentInfoWsdlURL = new URL("http://localhost:8080/studentinfo?wsdl");
    Service studentInfoService = Service.create(studentInfoWsdlURL, new QName("http://wsdl/", "StudentInfoPortTypeImplService"));
    StudentInfoPortType studentInfoPort = studentInfoService.getPort(StudentInfoPortType.class);

    URL checkInWsdlURL = new URL("http://localhost:8080/checkin?wsdl");
    Service checkInService = Service.create(checkInWsdlURL, new QName("http://wsdl/", "CheckInPortTypeImplService"));
    CheckInPortType checkInPort = checkInService.getPort(CheckInPortType.class);

    public SFWTest() throws MalformedURLException {
    }

    @Test
    public void test1(){
        StudentInfoSearch studentInfoSearch = new StudentInfoSearch();
        studentInfoSearch.setAuthName("1.teacher");
        studentInfoSearch.setAuthPassword("123.teacher");
        studentInfoSearch.setStudentID(1);
        System.out.println(studentInfoPort.getStudentInfo(studentInfoSearch));

    }
    @Test
    public void test2(){
        StudentInfoSearch studentInfoSearch = new StudentInfoSearch();
        studentInfoSearch.setAuthName("1.teacher");
        studentInfoSearch.setAuthPassword("123.teacher");
        studentInfoSearch.setStudentID(2);
        System.out.println(studentInfoPort.getStudentInfo(studentInfoSearch));
    }
    @Test
    public void test3(){
        CheckInSearch checkInSearch = new CheckInSearch();
        checkInSearch.setAuthName("1.teacher");
        checkInSearch.setAuthPassword("123.teacher");
        checkInSearch.setStudentID(1);
        System.out.println(checkInPort.studentCheckIn(checkInSearch));
    }
    @Test
    public void test4(){
        CheckInSearch checkInSearch = new CheckInSearch();
        checkInSearch.setAuthName("1.teacher");
        checkInSearch.setAuthPassword("123.teacher");
        checkInSearch.setStudentID(2);
        System.out.println(checkInPort.studentCheckIn(checkInSearch));
    }
    @Test
    public void test5(){
        CheckInSearch checkInSearch = new CheckInSearch();
        checkInSearch.setAuthName("1.student");
        checkInSearch.setAuthPassword("123.student");
        checkInSearch.setStudentID(2);
        System.out.println(checkInPort.studentCheckIn(checkInSearch));
    }

    @Test
    public void test6(){
        CheckInSearch checkInSearch = new CheckInSearch();
        checkInSearch.setAuthName("1.stu");
        checkInSearch.setAuthPassword("123.teacher");
        checkInSearch.setStudentID(2);
        System.out.println(checkInPort.studentCheckIn(checkInSearch));
    }

}
