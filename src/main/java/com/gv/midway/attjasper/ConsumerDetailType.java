
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Subscriber Personal Details:
 *         Subscriber Number is unique number that is defined in AMDOCS system.
 *         Subscriber Full Name (First Name, Middle Initial and Last Name)
 *         Subscriber Address but Only Postal Code is mandatory
 *         Subscriber Primary contact details like Phone or Email or Fax.
 *     
 * 
 * <p>Java class for ConsumerDetailType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsumerDetailType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subscriberNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subscirberName" type="{http://api.jasperwireless.com/ws/schema}NameType"/>
 *         &lt;element name="address" type="{http://api.jasperwireless.com/ws/schema}SubscriberAddressType"/>
 *         &lt;element name="primaryContact" type="{http://api.jasperwireless.com/ws/schema}PrimaryContactType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsumerDetailType", propOrder = {
    "subscriberNumber",
    "subscirberName",
    "address",
    "primaryContact"
})
public class ConsumerDetailType {

    @XmlElement(required = true)
    protected String subscriberNumber;
    @XmlElement(required = true)
    protected NameType subscirberName;
    @XmlElement(required = true)
    protected SubscriberAddressType address;
    protected PrimaryContactType primaryContact;

    /**
     * Gets the value of the subscriberNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriberNumber() {
        return subscriberNumber;
    }

    /**
     * Sets the value of the subscriberNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriberNumber(String value) {
        this.subscriberNumber = value;
    }

    /**
     * Gets the value of the subscirberName property.
     * 
     * @return
     *     possible object is
     *     {@link NameType }
     *     
     */
    public NameType getSubscirberName() {
        return subscirberName;
    }

    /**
     * Sets the value of the subscirberName property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameType }
     *     
     */
    public void setSubscirberName(NameType value) {
        this.subscirberName = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriberAddressType }
     *     
     */
    public SubscriberAddressType getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriberAddressType }
     *     
     */
    public void setAddress(SubscriberAddressType value) {
        this.address = value;
    }

    /**
     * Gets the value of the primaryContact property.
     * 
     * @return
     *     possible object is
     *     {@link PrimaryContactType }
     *     
     */
    public PrimaryContactType getPrimaryContact() {
        return primaryContact;
    }

    /**
     * Sets the value of the primaryContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrimaryContactType }
     *     
     */
    public void setPrimaryContact(PrimaryContactType value) {
        this.primaryContact = value;
    }

}
