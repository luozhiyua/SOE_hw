
package com.example.checkin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡauthName���Ե�ֵ��
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
     * ����authName���Ե�ֵ��
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
     * ��ȡauthPassword���Ե�ֵ��
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
     * ����authPassword���Ե�ֵ��
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
     * ��ȡstudentID���Ե�ֵ��
     * 
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * ����studentID���Ե�ֵ��
     * 
     */
    public void setStudentID(int value) {
        this.studentID = value;
    }

}
