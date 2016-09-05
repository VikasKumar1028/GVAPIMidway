
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Aggregator for device. At least one element must be present
 *     
 * 
 * <p>Java class for MBBVehicleInfoResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MBBVehicleInfoResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Result" type="{http://api.jasperwireless.com/ws/schema}ResultType"/>
 *         &lt;element name="VIN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Devices" type="{http://api.jasperwireless.com/ws/schema}DevicesType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MBBVehicleInfoResponseType", propOrder = {
    "result",
    "vin",
    "devices"
})
public class MBBVehicleInfoResponseType {

    @XmlElement(name = "Result", required = true)
    protected ResultType result;
    @XmlElement(name = "VIN", required = true)
    protected String vin;
    @XmlElement(name = "Devices", required = true)
    protected DevicesType devices;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link ResultType }
     *     
     */
    public ResultType getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultType }
     *     
     */
    public void setResult(ResultType value) {
        this.result = value;
    }

    /**
     * Gets the value of the vin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVIN() {
        return vin;
    }

    /**
     * Sets the value of the vin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVIN(String value) {
        this.vin = value;
    }

    /**
     * Gets the value of the devices property.
     * 
     * @return
     *     possible object is
     *     {@link DevicesType }
     *     
     */
    public DevicesType getDevices() {
        return devices;
    }

    /**
     * Sets the value of the devices property.
     * 
     * @param value
     *     allowed object is
     *     {@link DevicesType }
     *     
     */
    public void setDevices(DevicesType value) {
        this.devices = value;
    }

}
