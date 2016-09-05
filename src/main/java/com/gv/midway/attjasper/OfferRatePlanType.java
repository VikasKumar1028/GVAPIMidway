
package com.gv.midway.attjasper;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * <p>Java class for OfferRatePlanType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OfferRatePlanType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ratePlanId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="offerRatePlanDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="dataLimitMB" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="termLength" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="termUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="combineSms" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="smsMoLimitMessages" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="smsMtLimitMessages" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="voiceLimitSeconds" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="notes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dateModified" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OfferRatePlanType", propOrder = {
    "ratePlanId",
    "offerRatePlanDesc",
    "price",
    "dataLimitMB",
    "termLength",
    "termUnit",
    "combineSms",
    "smsMoLimitMessages",
    "smsMtLimitMessages",
    "voiceLimitSeconds",
    "message",
    "notes",
    "dateModified"
})
public class OfferRatePlanType {

    protected long ratePlanId;
    protected String offerRatePlanDesc;
    @XmlElement(required = true)
    protected BigDecimal price;
    protected BigDecimal dataLimitMB;
    protected Long termLength;
    protected String termUnit;
    protected boolean combineSms;
    protected Long smsMoLimitMessages;
    protected Long smsMtLimitMessages;
    protected Long voiceLimitSeconds;
    protected String message;
    protected String notes;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateModified;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the ratePlanId property.
     * 
     */
    public long getRatePlanId() {
        return ratePlanId;
    }

    /**
     * Sets the value of the ratePlanId property.
     * 
     */
    public void setRatePlanId(long value) {
        this.ratePlanId = value;
    }

    /**
     * Gets the value of the offerRatePlanDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferRatePlanDesc() {
        return offerRatePlanDesc;
    }

    /**
     * Sets the value of the offerRatePlanDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferRatePlanDesc(String value) {
        this.offerRatePlanDesc = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrice(BigDecimal value) {
        this.price = value;
    }

    /**
     * Gets the value of the dataLimitMB property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDataLimitMB() {
        return dataLimitMB;
    }

    /**
     * Sets the value of the dataLimitMB property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDataLimitMB(BigDecimal value) {
        this.dataLimitMB = value;
    }

    /**
     * Gets the value of the termLength property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTermLength() {
        return termLength;
    }

    /**
     * Sets the value of the termLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTermLength(Long value) {
        this.termLength = value;
    }

    /**
     * Gets the value of the termUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermUnit() {
        return termUnit;
    }

    /**
     * Sets the value of the termUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermUnit(String value) {
        this.termUnit = value;
    }

    /**
     * Gets the value of the combineSms property.
     * 
     */
    public boolean isCombineSms() {
        return combineSms;
    }

    /**
     * Sets the value of the combineSms property.
     * 
     */
    public void setCombineSms(boolean value) {
        this.combineSms = value;
    }

    /**
     * Gets the value of the smsMoLimitMessages property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSmsMoLimitMessages() {
        return smsMoLimitMessages;
    }

    /**
     * Sets the value of the smsMoLimitMessages property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSmsMoLimitMessages(Long value) {
        this.smsMoLimitMessages = value;
    }

    /**
     * Gets the value of the smsMtLimitMessages property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSmsMtLimitMessages() {
        return smsMtLimitMessages;
    }

    /**
     * Sets the value of the smsMtLimitMessages property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSmsMtLimitMessages(Long value) {
        this.smsMtLimitMessages = value;
    }

    /**
     * Gets the value of the voiceLimitSeconds property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getVoiceLimitSeconds() {
        return voiceLimitSeconds;
    }

    /**
     * Sets the value of the voiceLimitSeconds property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setVoiceLimitSeconds(Long value) {
        this.voiceLimitSeconds = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the notes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotes(String value) {
        this.notes = value;
    }

    /**
     * Gets the value of the dateModified property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateModified() {
        return dateModified;
    }

    /**
     * Sets the value of the dateModified property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateModified(XMLGregorianCalendar value) {
        this.dateModified = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
