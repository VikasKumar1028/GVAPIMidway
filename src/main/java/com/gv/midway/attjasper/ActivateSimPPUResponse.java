
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
 *         &lt;element name="Activate_SIM_PPUResult" type="{http://api.jasperwireless.com/ws/schema}ActivateSimResult" minOccurs="0"/>
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
    "activateSIMPPUResult"
})
@XmlRootElement(name = "ActivateSimPPUResponse")
public class ActivateSimPPUResponse
    extends ResponseType
{

    @XmlElement(name = "Activate_SIM_PPUResult")
    protected ActivateSimResult activateSIMPPUResult;

    /**
     * Gets the value of the activateSIMPPUResult property.
     * 
     * @return
     *     possible object is
     *     {@link ActivateSimResult }
     *     
     */
    public ActivateSimResult getActivateSIMPPUResult() {
        return activateSIMPPUResult;
    }

    /**
     * Sets the value of the activateSIMPPUResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivateSimResult }
     *     
     */
    public void setActivateSIMPPUResult(ActivateSimResult value) {
        this.activateSIMPPUResult = value;
    }

}
