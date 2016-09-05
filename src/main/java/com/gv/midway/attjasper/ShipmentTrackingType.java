
package com.gv.midway.attjasper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *         Shipment Tracking Type signifies a single shipment and MAY have one or more list of iccids.
 *         Each shipment MUST have a tracking number and MUST have a shipment date in UTC or with timezone offset.
 *         If present, ICCIDs will be validated and then transferred to the account that placed the SIM order.
 *         Each shipment MAY have a tracking URL. If present, the tracking number will be linked to the URL in the web UI.
 *         If RatePlan, CommPlan and SIM Status are specified in the API call they will be used, otherwise the values from the SIM Order will be used, with account level defaults.
 *     
 * 
 * <p>Java class for shipmentTrackingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="shipmentTrackingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="iccids" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="targetRatePlan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="targetCommunicationPlan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="targetSIMStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TrackingNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="shipDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="TrackingURL" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="shipmentStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "shipmentTrackingType", propOrder = {
    "iccids",
    "targetRatePlan",
    "targetCommunicationPlan",
    "targetSIMStatus",
    "trackingNumber",
    "shipDate",
    "trackingURL",
    "shipmentStatus"
})
public class ShipmentTrackingType {

    protected ShipmentTrackingType.Iccids iccids;
    protected String targetRatePlan;
    protected String targetCommunicationPlan;
    protected String targetSIMStatus;
    @XmlElement(name = "TrackingNumber", required = true)
    protected String trackingNumber;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar shipDate;
    @XmlElement(name = "TrackingURL")
    @XmlSchemaType(name = "anyURI")
    protected String trackingURL;
    protected String shipmentStatus;

    /**
     * Gets the value of the iccids property.
     * 
     * @return
     *     possible object is
     *     {@link ShipmentTrackingType.Iccids }
     *     
     */
    public ShipmentTrackingType.Iccids getIccids() {
        return iccids;
    }

    /**
     * Sets the value of the iccids property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShipmentTrackingType.Iccids }
     *     
     */
    public void setIccids(ShipmentTrackingType.Iccids value) {
        this.iccids = value;
    }

    /**
     * Gets the value of the targetRatePlan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetRatePlan() {
        return targetRatePlan;
    }

    /**
     * Sets the value of the targetRatePlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetRatePlan(String value) {
        this.targetRatePlan = value;
    }

    /**
     * Gets the value of the targetCommunicationPlan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetCommunicationPlan() {
        return targetCommunicationPlan;
    }

    /**
     * Sets the value of the targetCommunicationPlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetCommunicationPlan(String value) {
        this.targetCommunicationPlan = value;
    }

    /**
     * Gets the value of the targetSIMStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetSIMStatus() {
        return targetSIMStatus;
    }

    /**
     * Sets the value of the targetSIMStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetSIMStatus(String value) {
        this.targetSIMStatus = value;
    }

    /**
     * Gets the value of the trackingNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackingNumber() {
        return trackingNumber;
    }

    /**
     * Sets the value of the trackingNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackingNumber(String value) {
        this.trackingNumber = value;
    }

    /**
     * Gets the value of the shipDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getShipDate() {
        return shipDate;
    }

    /**
     * Sets the value of the shipDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setShipDate(XMLGregorianCalendar value) {
        this.shipDate = value;
    }

    /**
     * Gets the value of the trackingURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackingURL() {
        return trackingURL;
    }

    /**
     * Sets the value of the trackingURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackingURL(String value) {
        this.trackingURL = value;
    }

    /**
     * Gets the value of the shipmentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipmentStatus() {
        return shipmentStatus;
    }

    /**
     * Sets the value of the shipmentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipmentStatus(String value) {
        this.shipmentStatus = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "iccid"
    })
    public static class Iccids {

        @XmlElement(required = true)
        protected List<String> iccid;

        /**
         * Gets the value of the iccid property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the iccid property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getIccid().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getIccid() {
            if (iccid == null) {
                iccid = new ArrayList<String>();
            }
            return this.iccid;
        }

    }

}
