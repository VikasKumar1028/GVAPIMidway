
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
 *         &lt;element name="MessageToVehicle" type="{http://api.jasperwireless.com/ws/schema}MBBMessageToVehicleRequestType"/>
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
    "messageToVehicle"
})
@XmlRootElement(name = "MBBMessageToVehicleRequest")
public class MBBMessageToVehicleRequest
    extends RequestType
{

    @XmlElement(name = "MessageToVehicle", required = true)
    protected MBBMessageToVehicleRequestType messageToVehicle;

    /**
     * Gets the value of the messageToVehicle property.
     * 
     * @return
     *     possible object is
     *     {@link MBBMessageToVehicleRequestType }
     *     
     */
    public MBBMessageToVehicleRequestType getMessageToVehicle() {
        return messageToVehicle;
    }

    /**
     * Sets the value of the messageToVehicle property.
     * 
     * @param value
     *     allowed object is
     *     {@link MBBMessageToVehicleRequestType }
     *     
     */
    public void setMessageToVehicle(MBBMessageToVehicleRequestType value) {
        this.messageToVehicle = value;
    }

}
