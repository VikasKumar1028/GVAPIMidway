
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
 *         &lt;element name="VehicleInfo" type="{http://api.jasperwireless.com/ws/schema}MBBVehicleInfoRequestType"/>
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
    "vehicleInfo"
})
@XmlRootElement(name = "MBBVehicleInfoRequest")
public class MBBVehicleInfoRequest
    extends RequestType
{

    @XmlElement(name = "VehicleInfo", required = true)
    protected MBBVehicleInfoRequestType vehicleInfo;

    /**
     * Gets the value of the vehicleInfo property.
     * 
     * @return
     *     possible object is
     *     {@link MBBVehicleInfoRequestType }
     *     
     */
    public MBBVehicleInfoRequestType getVehicleInfo() {
        return vehicleInfo;
    }

    /**
     * Sets the value of the vehicleInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link MBBVehicleInfoRequestType }
     *     
     */
    public void setVehicleInfo(MBBVehicleInfoRequestType value) {
        this.vehicleInfo = value;
    }

}
