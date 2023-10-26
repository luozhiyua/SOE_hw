import org.example.*;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class XMLtest {

    String xsdPath = "src/main/java/org/example/MergedSchema.xsd";
    @Test
    public void testXmlVlidate1(){
        String xmlPath = "src/main/java/org/example/XML1.xml";
        boolean res = XmlVlidate.validate(xsdPath,xmlPath);
        assertTrue(res);
    }

    @Test
    public void testXmlVlidate2(){
        String xmlPath = "src/main/java/org/example/XML2.xml";
        boolean res = XmlVlidate.validate(xsdPath,xmlPath);
        assertTrue(res);
    }

    @Test
    public void testXmlVlidate3() throws Exception {
        ConvertToXML3.convertToXML3();
        String xmlPath = "src/main/java/org/example/XML3.xml";
        boolean res = XmlVlidate.validate(xsdPath,xmlPath);
        assertTrue(res);
    }

    @Test
    public void testXmlVlidate4() throws Exception {
        ConvertToXML4.convertToXML4();
        String xmlPath = "src/main/java/org/example/XML4.xml";
        boolean res = XmlVlidate.validate(xsdPath,xmlPath);
        assertTrue(res);
    }
    @Test
    public void testXmlVlidate5() throws Exception {
        ConvertToXML5.convertToXML5();
        String xmlPath = "src/main/java/org/example/XML5.xml";
        boolean res = XmlVlidate.validate(xsdPath,xmlPath);
        assertTrue(res);
    }
}
