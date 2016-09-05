
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
 *         &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="terminalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endConsumerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalDataVolume" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="billableDataVolume" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="cycleStartDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="billable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="totalSMSVolume" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="totalVoiceVolume" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="billableSMSVolume" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="billableVoiceVolume" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ratePlan" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventsUsage" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="totalEvents" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "iccid",
    "terminalId",
    "customer",
    "endConsumerId",
    "totalDataVolume",
    "billableDataVolume",
    "cycleStartDate",
    "billable",
    "totalSMSVolume",
    "totalVoiceVolume",
    "billableSMSVolume",
    "billableVoiceVolume",
    "ratePlan",
    "eventsUsage",
    "totalEvents",
    "any"
})
@XmlRootElement(name = "GetTerminalUsageResponse")
public class GetTerminalUsageResponse
    extends ResponseType
{

    @XmlElement(required = true)
    protected String iccid;
    protected String terminalId;
    protected String customer;
    protected String endConsumerId;
    @XmlElement(required = true)
    protected BigDecimal totalDataVolume;
    @XmlElement(required = true)
    protected BigDecimal billableDataVolume;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar cycleStartDate;
    protected boolean billable;
    @XmlElement(required = true)
    protected BigDecimal totalSMSVolume;
    @XmlElement(required = true)
    protected BigDecimal totalVoiceVolume;
    @XmlElement(required = true)
    protected BigDecimal billableSMSVolume;
    @XmlElement(required = true)
    protected BigDecimal billableVoiceVolume;
    @XmlElement(required = true)
    protected String ratePlan;
    @XmlElement(required = true)
    protected BigDecimal eventsUsage;
    protected long totalEvents;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the iccid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIccid() {
        return iccid;
    }

    /**
     * Sets the value of the iccid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIccid(String value) {
        this.iccid = value;
    }

    /**
     * Gets the value of the terminalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminalId() {
        return terminalId;
    }

    /**
     * Sets the value of the terminalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminalId(String value) {
        this.terminalId = value;
    }

    /**
     * Gets the value of the customer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomer(String value) {
        this.customer = value;
    }

    /**
     * Gets the value of the endConsumerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndConsumerId() {
        return endConsumerId;
    }

    /**
     * Sets the value of the endConsumerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndConsumerId(String value) {
        this.endConsumerId = value;
    }

    /**
     * Gets the value of the totalDataVolume property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalDataVolume() {
        return totalDataVolume;
    }

    /**
     * Sets the value of the totalDataVolume property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalDataVolume(BigDecimal value) {
        this.totalDataVolume = value;
    }

    /**
     * Gets the value of the billableDataVolume property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBillableDataVolume() {
        return billableDataVolume;
    }

    /**
     * Sets the value of the billableDataVolume property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBillableDataVolume(BigDecimal value) {
        this.billableDataVolume = value;
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
     * Gets the value of the billable property.
     * 
     */
    public boolean isBillable() {
        return billable;
    }

    /**
     * Sets the value of the billable property.
     * 
     */
    public void setBillable(boolean value) {
        this.billable = value;
    }

    /**
     * Gets the value of the totalSMSVolume property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalSMSVolume() {
        return totalSMSVolume;
    }

    /**
     * Sets the value of the totalSMSVolume property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalSMSVolume(BigDecimal value) {
        this.totalSMSVolume = value;
    }

    /**
     * Gets the value of the totalVoiceVolume property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalVoiceVolume() {
        return totalVoiceVolume;
    }

    /**
     * Sets the value of the totalVoiceVolume property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalVoiceVolume(BigDecimal value) {
        this.totalVoiceVolume = value;
    }

    /**
     * Gets the value of the billableSMSVolume property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBillableSMSVolume() {
        return billableSMSVolume;
    }

    /**
     * Sets the value of the billableSMSVolume property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBillableSMSVolume(BigDecimal value) {
        this.billableSMSVolume = value;
    }

    /**
     * Gets the value of the billableVoiceVolume property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBillableVoiceVolume() {
        return billableVoiceVolume;
    }

    /**
     * Sets the value of the billableVoiceVolume property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBillableVoiceVolume(BigDecimal value) {
        this.billableVoiceVolume = value;
    }

    /**
     * Gets the value of the ratePlan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatePlan() {
        return ratePlan;
    }

    /**
     * Sets the value of the ratePlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatePlan(String value) {
        this.ratePlan = value;
    }

    /**
     * Gets the value of the eventsUsage property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEventsUsage() {
        return eventsUsage;
    }

    /**
     * Sets the value of the eventsUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEventsUsage(BigDecimal value) {
        this.eventsUsage = value;
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
