
package com.gv.midway.attjasper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;


/**
 * 
 *         The detail information about CDR.
 *     
 * 
 * <p>Java class for CdrInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CdrInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="causeRecordClosing" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="apn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bytesUplink" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="bytesDownlink" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="usage" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="roundedUsage" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="servedPdpAddr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="recordOpenTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="recordCloseTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="carrier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;any processContents='lax' maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "CdrInfoType", propOrder = {
    "iccid",
    "causeRecordClosing",
    "apn",
    "bytesUplink",
    "bytesDownlink",
    "usage",
    "roundedUsage",
    "servedPdpAddr",
    "duration",
    "recordOpenTime",
    "recordCloseTime",
    "carrier",
    "any"
})
@XmlSeeAlso({
    Cdr.class
})
public class CdrInfoType {

    @XmlElement(required = true)
    protected String iccid;
    @XmlElement(required = true)
    protected String causeRecordClosing;
    @XmlElement(required = true)
    protected String apn;
    protected long bytesUplink;
    protected long bytesDownlink;
    @XmlElement(required = true)
    protected BigDecimal usage;
    @XmlElement(required = true)
    protected BigDecimal roundedUsage;
    @XmlElement(required = true)
    protected String servedPdpAddr;
    protected long duration;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar recordOpenTime;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar recordCloseTime;
    @XmlElement(required = true)
    protected String carrier;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

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
     * Gets the value of the causeRecordClosing property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCauseRecordClosing() {
        return causeRecordClosing;
    }

    /**
     * Sets the value of the causeRecordClosing property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCauseRecordClosing(String value) {
        this.causeRecordClosing = value;
    }

    /**
     * Gets the value of the apn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApn() {
        return apn;
    }

    /**
     * Sets the value of the apn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApn(String value) {
        this.apn = value;
    }

    /**
     * Gets the value of the bytesUplink property.
     * 
     */
    public long getBytesUplink() {
        return bytesUplink;
    }

    /**
     * Sets the value of the bytesUplink property.
     * 
     */
    public void setBytesUplink(long value) {
        this.bytesUplink = value;
    }

    /**
     * Gets the value of the bytesDownlink property.
     * 
     */
    public long getBytesDownlink() {
        return bytesDownlink;
    }

    /**
     * Sets the value of the bytesDownlink property.
     * 
     */
    public void setBytesDownlink(long value) {
        this.bytesDownlink = value;
    }

    /**
     * Gets the value of the usage property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUsage() {
        return usage;
    }

    /**
     * Sets the value of the usage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUsage(BigDecimal value) {
        this.usage = value;
    }

    /**
     * Gets the value of the roundedUsage property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRoundedUsage() {
        return roundedUsage;
    }

    /**
     * Sets the value of the roundedUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRoundedUsage(BigDecimal value) {
        this.roundedUsage = value;
    }

    /**
     * Gets the value of the servedPdpAddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServedPdpAddr() {
        return servedPdpAddr;
    }

    /**
     * Sets the value of the servedPdpAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServedPdpAddr(String value) {
        this.servedPdpAddr = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     */
    public long getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     */
    public void setDuration(long value) {
        this.duration = value;
    }

    /**
     * Gets the value of the recordOpenTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRecordOpenTime() {
        return recordOpenTime;
    }

    /**
     * Sets the value of the recordOpenTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRecordOpenTime(XMLGregorianCalendar value) {
        this.recordOpenTime = value;
    }

    /**
     * Gets the value of the recordCloseTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRecordCloseTime() {
        return recordCloseTime;
    }

    /**
     * Sets the value of the recordCloseTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRecordCloseTime(XMLGregorianCalendar value) {
        this.recordCloseTime = value;
    }

    /**
     * Gets the value of the carrier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrier() {
        return carrier;
    }

    /**
     * Sets the value of the carrier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrier(String value) {
        this.carrier = value;
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
