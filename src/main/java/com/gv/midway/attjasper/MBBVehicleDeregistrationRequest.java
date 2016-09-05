
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.jasperwireless.com/ws/schema}RequestType">
 *       &lt;sequence>
 *         &lt;element name="VehicleDeregistration" type="{http://api.jasperwireless.com/ws/schema}MBBVehicleDeregistrationRequestType"/>
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
    "vehicleDeregistration"
})
@XmlRootElement(name = "MBBVehicleDeregistrationRequest")
public class MBBVehicleDeregistrationRequest
    extends RequestType
{

    @XmlElement(name = "VehicleDeregistration", required = true)
    protected MBBVehicleDeregistrationRequestType vehicleDeregistration;

    /**
     * Gets the value of the vehicleDeregistration property.
     * 
     * @return
     *     possible object is
     *     {@link MBBVehicleDeregistrationRequestType }
     *     
     */
    public MBBVehicleDeregistrationRequestType getVehicleDeregistration() {
        return vehicleDeregistration;
    }

    /**
     * Sets the value of the vehicleDeregistration property.
     * 
     * @param value
     *     allowed object is
     *     {@link MBBVehicleDeregistrationRequestType }
     *     
     */
    public void setVehicleDeregistration(MBBVehicleDeregistrationRequestType value) {
        this.vehicleDeregistration = value;
    }

}
