
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
 *         &lt;element name="strCustPrefix" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strDeactivationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "strCustPrefix",
    "strDeactivationDate"
})
@XmlRootElement(name = "DeactivateSimRequest")
public class DeactivateSimRequest
    extends RequestType
{

    @XmlElement(required = true)
    protected String strSIM;
    @XmlElement(required = true)
    protected String strCustPrefix;
    @XmlElement(required = true, nillable = true)
    protected String strDeactivationDate;

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
     * Gets the value of the strDeactivationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrDeactivationDate() {
        return strDeactivationDate;
    }

    /**
     * Sets the value of the strDeactivationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrDeactivationDate(String value) {
        this.strDeactivationDate = value;
    }

}
