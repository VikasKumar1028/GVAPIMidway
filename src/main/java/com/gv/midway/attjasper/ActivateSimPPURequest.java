
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.jasperwireless.com/ws/schema}RequestType">
 *       &lt;sequence>
 *         &lt;element name="strSIM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strAction" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strCustPrefix" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strCust_Field1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strCust_Field2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strCust_Field3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strCust_Field4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strCust_Field5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strProfileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iPriority" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="strPPU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "strSIM",
    "strAction",
    "strDate",
    "strCustPrefix",
    "strCustField1",
    "strCustField2",
    "strCustField3",
    "strCustField4",
    "strCustField5",
    "strProfileName",
    "iPriority",
    "strPPU"
})
@XmlRootElement(name = "ActivateSimPPURequest")
public class ActivateSimPPURequest
    extends RequestType
{

    @XmlElement(required = true)
    protected String strSIM;
    @XmlElement(required = true)
    protected String strAction;
    @XmlElement(required = true)
    protected String strDate;
    @XmlElement(required = true)
    protected String strCustPrefix;
    @XmlElement(name = "strCust_Field1")
    protected String strCustField1;
    @XmlElement(name = "strCust_Field2")
    protected String strCustField2;
    @XmlElement(name = "strCust_Field3")
    protected String strCustField3;
    @XmlElement(name = "strCust_Field4")
    protected String strCustField4;
    @XmlElement(name = "strCust_Field5")
    protected String strCustField5;
    protected String strProfileName;
    protected int iPriority;
    protected String strPPU;

    /**
     * Gets the value of the strSIM property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrSIM() {
        return strSIM;
    }

    /**
     * Sets the value of the strSIM property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrSIM(String value) {
        this.strSIM = value;
    }

    /**
     * Gets the value of the strAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrAction() {
        return strAction;
    }

    /**
     * Sets the value of the strAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrAction(String value) {
        this.strAction = value;
    }

    /**
     * Gets the value of the strDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrDate() {
        return strDate;
    }

    /**
     * Sets the value of the strDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrDate(String value) {
        this.strDate = value;
    }

    /**
     * Gets the value of the strCustPrefix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrCustPrefix() {
        return strCustPrefix;
    }

    /**
     * Sets the value of the strCustPrefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrCustPrefix(String value) {
        this.strCustPrefix = value;
    }

    /**
     * Gets the value of the strCustField1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrCustField1() {
        return strCustField1;
    }

    /**
     * Sets the value of the strCustField1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrCustField1(String value) {
        this.strCustField1 = value;
    }

    /**
     * Gets the value of the strCustField2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrCustField2() {
        return strCustField2;
    }

    /**
     * Sets the value of the strCustField2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrCustField2(String value) {
        this.strCustField2 = value;
    }

    /**
     * Gets the value of the strCustField3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrCustField3() {
        return strCustField3;
    }

    /**
     * Sets the value of the strCustField3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrCustField3(String value) {
        this.strCustField3 = value;
    }

    /**
     * Gets the value of the strCustField4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrCustField4() {
        return strCustField4;
    }

    /**
     * Sets the value of the strCustField4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrCustField4(String value) {
        this.strCustField4 = value;
    }

    /**
     * Gets the value of the strCustField5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrCustField5() {
        return strCustField5;
    }

    /**
     * Sets the value of the strCustField5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrCustField5(String value) {
        this.strCustField5 = value;
    }

    /**
     * Gets the value of the strProfileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrProfileName() {
        return strProfileName;
    }

    /**
     * Sets the value of the strProfileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrProfileName(String value) {
        this.strProfileName = value;
    }

    /**
     * Gets the value of the iPriority property.
     * 
     */
    public int getIPriority() {
        return iPriority;
    }

    /**
     * Sets the value of the iPriority property.
     * 
     */
    public void setIPriority(int value) {
        this.iPriority = value;
    }

    /**
     * Gets the value of the strPPU property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrPPU() {
        return strPPU;
    }

    /**
     * Sets the value of the strPPU property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrPPU(String value) {
        this.strPPU = value;
    }

}
