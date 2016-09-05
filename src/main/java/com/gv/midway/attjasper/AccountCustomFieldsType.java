
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccountCustomFieldsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountCustomFieldsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fieldValue1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fieldValue2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fieldValue3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fieldValue4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fieldValue5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountCustomFieldsType", propOrder = {
    "fieldValue1",
    "fieldValue2",
    "fieldValue3",
    "fieldValue4",
    "fieldValue5"
})
public class AccountCustomFieldsType {

    protected String fieldValue1;
    protected String fieldValue2;
    protected String fieldValue3;
    protected String fieldValue4;
    protected String fieldValue5;

    /**
     * Gets the value of the fieldValue1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldValue1() {
        return fieldValue1;
    }

    /**
     * Sets the value of the fieldValue1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldValue1(String value) {
        this.fieldValue1 = value;
    }

    /**
     * Gets the value of the fieldValue2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldValue2() {
        return fieldValue2;
    }

    /**
     * Sets the value of the fieldValue2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldValue2(String value) {
        this.fieldValue2 = value;
    }

    /**
     * Gets the value of the fieldValue3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldValue3() {
        return fieldValue3;
    }

    /**
     * Sets the value of the fieldValue3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldValue3(String value) {
        this.fieldValue3 = value;
    }

    /**
     * Gets the value of the fieldValue4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldValue4() {
        return fieldValue4;
    }

    /**
     * Sets the value of the fieldValue4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldValue4(String value) {
        this.fieldValue4 = value;
    }

    /**
     * Gets the value of the fieldValue5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldValue5() {
        return fieldValue5;
    }

    /**
     * Sets the value of the fieldValue5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldValue5(String value) {
        this.fieldValue5 = value;
    }

}
