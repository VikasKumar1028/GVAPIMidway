
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         When the given request is "FAILURE" then it will carry the error details
 *     
 * 
 * <p>Java class for SubscriberResponseEnhancedMessageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubscriberResponseEnhancedMessageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="errorCategory" type="{http://api.jasperwireless.com/ws/schema}EnhancedErrorCategoryType"/>
 *         &lt;element name="errorCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="errorName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="errorDetails" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubscriberResponseEnhancedMessageType", propOrder = {
    "errorCategory",
    "errorCode",
    "errorName",
    "errorDetails"
})
public class SubscriberResponseEnhancedMessageType {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected EnhancedErrorCategoryType errorCategory;
    @XmlElement(required = true)
    protected String errorCode;
    @XmlElement(required = true)
    protected String errorName;
    @XmlElement(required = true)
    protected String errorDetails;

    /**
     * Gets the value of the errorCategory property.
     * 
     * @return
     *     possible object is
     *     {@link EnhancedErrorCategoryType }
     *     
     */
    public EnhancedErrorCategoryType getErrorCategory() {
        return errorCategory;
    }

    /**
     * Sets the value of the errorCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnhancedErrorCategoryType }
     *     
     */
    public void setErrorCategory(EnhancedErrorCategoryType value) {
        this.errorCategory = value;
    }

    /**
     * Gets the value of the errorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    /**
     * Gets the value of the errorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorName() {
        return errorName;
    }

    /**
     * Sets the value of the errorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorName(String value) {
        this.errorName = value;
    }

    /**
     * Gets the value of the errorDetails property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorDetails() {
        return errorDetails;
    }

    /**
     * Sets the value of the errorDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorDetails(String value) {
        this.errorDetails = value;
    }

}
