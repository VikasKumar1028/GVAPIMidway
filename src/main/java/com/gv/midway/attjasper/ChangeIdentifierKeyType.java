
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *             Change Identifier Key record details.
 *             Billing Account Number is mandatory field
 *             Since Change Identifier request should be called by using ICCID or MSISDN. So no other values are allowed in Key Fields
 *         
 * 
 * <p>Java class for ChangeIdentifierKeyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChangeIdentifierKeyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="billingAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="keyField" type="{http://api.jasperwireless.com/ws/schema}KeyFieldType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChangeIdentifierKeyType", propOrder = {
    "billingAccountNumber",
    "keyField"
})
public class ChangeIdentifierKeyType {

    @XmlElement(required = true)
    protected String billingAccountNumber;
    @XmlElement(required = true)
    protected KeyFieldType keyField;

    /**
     * Gets the value of the billingAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingAccountNumber() {
        return billingAccountNumber;
    }

    /**
     * Sets the value of the billingAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingAccountNumber(String value) {
        this.billingAccountNumber = value;
    }

    /**
     * Gets the value of the keyField property.
     * 
     * @return
     *     possible object is
     *     {@link KeyFieldType }
     *     
     */
    public KeyFieldType getKeyField() {
        return keyField;
    }

    /**
     * Sets the value of the keyField property.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyFieldType }
     *     
     */
    public void setKeyField(KeyFieldType value) {
        this.keyField = value;
    }

}
