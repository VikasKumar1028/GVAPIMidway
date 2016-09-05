
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
 *         &lt;element name="VehicleRegistration" type="{http://api.jasperwireless.com/ws/schema}MBBVehicleRegistrationRequestType"/>
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
    "vehicleRegistration"
})
@XmlRootElement(name = "MBBVehicleRegistrationRequest")
public class MBBVehicleRegistrationRequest
    extends RequestType
{

    @XmlElement(name = "VehicleRegistration", required = true)
    protected MBBVehicleRegistrationRequestType vehicleRegistration;

    /**
     * Gets the value of the vehicleRegistration property.
     * 
     * @return
     *     possible object is
     *     {@link MBBVehicleRegistrationRequestType }
     *     
     */
    public MBBVehicleRegistrationRequestType getVehicleRegistration() {
        return vehicleRegistration;
    }

    /**
     * Sets the value of the vehicleRegistration property.
     * 
     * @param value
     *     allowed object is
     *     {@link MBBVehicleRegistrationRequestType }
     *     
     */
    public void setVehicleRegistration(MBBVehicleRegistrationRequestType value) {
        this.vehicleRegistration = value;
    }

}
