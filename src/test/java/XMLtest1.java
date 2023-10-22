import org.example.XmlVlidate;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class XMLtest1 {

    @Test
    public void testXmlVlidate1(){
        String xsdPath = "src/main/java/org/example/SchoolArchives.xsd";
        String xmlPath = "src/main/java/org/example/XML1.xml";
        boolean res = XmlVlidate.validate(xsdPath,xmlPath);
        assertTrue(res);
    }

    @Test
    public void testXmlVlidate2(){
        String xsdPath = "src/main/java/org/example/SignClass.xsd";
        String xmlPath = "src/main/java/org/example/XML2.xml";
        boolean res = XmlVlidate.validate(xsdPath,xmlPath);
        assertTrue(res);
    }
}
