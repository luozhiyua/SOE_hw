
package com.example.checkin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="authPassword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="studentID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "authName",
    "authPassword",
    "studentID"
})
@XmlRootElement(name = "checkInSearch")
public class CheckInSearch {

    @XmlElement(required = true)
    protected String authName;
    @XmlElement(required = true)
    protected String authPassword;
    protected int studentID;

    /**
     * 获取authName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthName() {
        return authName;
    }

    /**
     * 设置authName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthName(String value) {
        this.authName = value;
    }

    /**
     * 获取authPassword属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthPassword() {
        return authPassword;
    }

    /**
     * 设置authPassword属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthPassword(String value) {
        this.authPassword = value;
    }

    /**
     * 获取studentID属性的值。
     * 
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * 设置studentID属性的值。
     * 
     */
    public void setStudentID(int value) {
        this.studentID = value;
    }

}
