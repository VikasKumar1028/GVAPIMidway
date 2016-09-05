
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
 *     &lt;extension base="{http://api.jasperwireless.com/ws/schema}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="simLocationChargeResponseDetails" type="{http://api.jasperwireless.com/ws/schema}SimLocationChargeResponseType"/>
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
    "simLocationChargeResponseDetails"
})
@XmlRootElement(name = "SimLocationChargeResponse")
public class SimLocationChargeResponse
    extends ResponseType
{

    @XmlElement(required = true)
    protected SimLocationChargeResponseType simLocationChargeResponseDetails;

    /**
     * Gets the value of the simLocationChargeResponseDetails property.
     * 
     * @return
     *     possible object is
     *     {@link SimLocationChargeResponseType }
     *     
     */
    public SimLocationChargeResponseType getSimLocationChargeResponseDetails() {
        return simLocationChargeResponseDetails;
    }

    /**
     * Sets the value of the simLocationChargeResponseDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link SimLocationChargeResponseType }
     *     
     */
    public void setSimLocationChargeResponseDetails(SimLocationChargeResponseType value) {
        this.simLocationChargeResponseDetails = value;
    }

}
