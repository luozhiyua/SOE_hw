
package com.example.archive;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.archive package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _StudentInfo_QNAME = new QName("http://example.com/archive", "studentInfo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.archive
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StudentInfoSearch }
     * 
     */
    public StudentInfoSearch createStudentInfoSearch() {
        return new StudentInfoSearch();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://example.com/archive", name = "studentInfo")
    public JAXBElement<String> createStudentInfo(String value) {
        return new JAXBElement<String>(_StudentInfo_QNAME, String.class, null, value);
    }

}
