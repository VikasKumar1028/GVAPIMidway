
package com.gv.midway.attjasper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.w3c.dom.Element;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.jasperwireless.com/ws/schema}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="accountId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="invoiceId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="invoiceDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="dueDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="cycleStartDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="cycleEndDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="totalTerminals" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="dataVolume" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="subscriptionCharge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="overageCharge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="totalCharge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="smsVolume" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="smsCharge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="voiceVolume" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="voiceCharge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="otherCharge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="totalEvents" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="eventsCharge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="activationCharge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="discountApplied" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;any processContents='lax' maxOccurs="unbounded" minOccurs="0"/>
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
    "accountId",
    "invoiceId",
    "currency",
    "invoiceDate",
    "dueDate",
    "cycleStartDate",
    "cycleEndDate",
    "totalTerminals",
    "dataVolume",
    "subscriptionCharge",
    "overageCharge",
    "totalCharge",
    "smsVolume",
    "smsCharge",
    "voiceVolume",
    "voiceCharge",
    "otherCharge",
    "totalEvents",
    "eventsCharge",
    "activationCharge",
    "discountApplied",
    "any"
})
@XmlRootElement(name = "GetInvoiceResponse")
public class GetInvoiceResponse
    extends ResponseType
{

    protected long accountId;
    protected long invoiceId;
    @XmlElement(required = true)
    protected String currency;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar invoiceDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dueDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar cycleStartDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar cycleEndDate;
    protected long totalTerminals;
    @XmlElement(required = true)
    protected BigDecimal dataVolume;
    @XmlElement(required = true)
    protected BigDecimal subscriptionCharge;
    @XmlElement(required = true)
    protected BigDecimal overageCharge;
    @XmlElement(required = true)
    protected BigDecimal totalCharge;
    @XmlElement(required = true)
    protected BigDecimal smsVolume;
    @XmlElement(required = true)
    protected BigDecimal smsCharge;
    @XmlElement(required = true)
    protected BigDecimal voiceVolume;
    @XmlElement(required = true)
    protected BigDecimal voiceCharge;
    @XmlElement(required = true)
    protected BigDecimal otherCharge;
    protected long totalEvents;
    @XmlElement(required = true)
    protected BigDecimal eventsCharge;
    @XmlElement(required = true)
    protected BigDecimal activationCharge;
    @XmlElement(required = true)
    protected BigDecimal discountApplied;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the accountId property.
     * 
     */
    public long getAccountId() {
        return accountId;
    }

    /**
     * Sets the value of the accountId property.
     * 
     */
    public void setAccountId(long value) {
        this.accountId = value;
    }

    /**
     * Gets the value of the invoiceId property.
     * 
     */
    public long getInvoiceId() {
        return invoiceId;
    }

    /**
     * Sets the value of the invoiceId property.
     * 
     */
    public void setInvoiceId(long value) {
        this.invoiceId = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the invoiceDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * Sets the value of the invoiceDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInvoiceDate(XMLGregorianCalendar value) {
        this.invoiceDate = value;
    }

    /**
     * Gets the value of the dueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDueDate() {
        return dueDate;
    }

    /**
     * Sets the value of the dueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDueDate(XMLGregorianCalendar value) {
        this.dueDate = value;
    }

    /**
     * Gets the value of the cycleStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCycleStartDate() {
        return cycleStartDate;
    }

    /**
     * Sets the value of the cycleStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCycleStartDate(XMLGregorianCalendar value) {
        this.cycleStartDate = value;
    }

    /**
     * Gets the value of the cycleEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCycleEndDate() {
        return cycleEndDate;
    }

    /**
     * Sets the value of the cycleEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCycleEndDate(XMLGregorianCalendar value) {
        this.cycleEndDate = value;
    }

    /**
     * Gets the value of the totalTerminals property.
     * 
     */
    public long getTotalTerminals() {
        return totalTerminals;
    }

    /**
     * Sets the value of the totalTerminals property.
     * 
     */
    public void setTotalTerminals(long value) {
        this.totalTerminals = value;
    }

    /**
     * Gets the value of the dataVolume property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDataVolume() {
        return dataVolume;
    }

    /**
     * Sets the value of the dataVolume property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDataVolume(BigDecimal value) {
        this.dataVolume = value;
    }

    /**
     * Gets the value of the subscriptionCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSubscriptionCharge() {
        return subscriptionCharge;
    }

    /**
     * Sets the value of the subscriptionCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSubscriptionCharge(BigDecimal value) {
        this.subscriptionCharge = value;
    }

    /**
     * Gets the value of the overageCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOverageCharge() {
        return overageCharge;
    }

    /**
     * Sets the value of the overageCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOverageCharge(BigDecimal value) {
        this.overageCharge = value;
    }

    /**
     * Gets the value of the totalCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalCharge() {
        return totalCharge;
    }

    /**
     * Sets the value of the totalCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalCharge(BigDecimal value) {
        this.totalCharge = value;
    }

    /**
     * Gets the value of the smsVolume property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSmsVolume() {
        return smsVolume;
    }

    /**
     * Sets the value of the smsVolume property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSmsVolume(BigDecimal value) {
        this.smsVolume = value;
    }

    /**
     * Gets the value of the smsCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSmsCharge() {
        return smsCharge;
    }

    /**
     * Sets the value of the smsCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSmsCharge(BigDecimal value) {
        this.smsCharge = value;
    }

    /**
     * Gets the value of the voiceVolume property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVoiceVolume() {
        return voiceVolume;
    }

    /**
     * Sets the value of the voiceVolume property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVoiceVolume(BigDecimal value) {
        this.voiceVolume = value;
    }

    /**
     * Gets the value of the voiceCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVoiceCharge() {
        return voiceCharge;
    }

    /**
     * Sets the value of the voiceCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVoiceCharge(BigDecimal value) {
        this.voiceCharge = value;
    }

    /**
     * Gets the value of the otherCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOtherCharge() {
        return otherCharge;
    }

    /**
     * Sets the value of the otherCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOtherCharge(BigDecimal value) {
        this.otherCharge = value;
    }

    /**
     * Gets the value of the totalEvents property.
     * 
     */
    public long getTotalEvents() {
        return totalEvents;
    }

    /**
     * Sets the value of the totalEvents property.
     * 
     */
    public void setTotalEvents(long value) {
        this.totalEvents = value;
    }

    /**
     * Gets the value of the eventsCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEventsCharge() {
        return eventsCharge;
    }

    /**
     * Sets the value of the eventsCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEventsCharge(BigDecimal value) {
        this.eventsCharge = value;
    }

    /**
     * Gets the value of the activationCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getActivationCharge() {
        return activationCharge;
    }

    /**
     * Sets the value of the activationCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setActivationCharge(BigDecimal value) {
        this.activationCharge = value;
    }

    /**
     * Gets the value of the discountApplied property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDiscountApplied() {
        return discountApplied;
    }

    /**
     * Sets the value of the discountApplied property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDiscountApplied(BigDecimal value) {
        this.discountApplied = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Element }
     * {@link Object }
     * 
     * 
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }

}
