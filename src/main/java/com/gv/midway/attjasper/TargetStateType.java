
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Target State is a Mandatory for all types of request. And it specifies what needs to be done on SIM state.
 *     
 * 
 * <p>Java class for TargetStateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TargetStateType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="simState" type="{http://api.jasperwireless.com/ws/schema}SimStateType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TargetStateType", propOrder = {
    "simState"
})
public class TargetStateType {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected SimStateType simState;

    /**
     * Gets the value of the simState property.
     * 
     * @return
     *     possible object is
     *     {@link SimStateType }
     *     
     */
    public SimStateType getSimState() {
        return simState;
    }

    /**
     * Sets the value of the simState property.
     * 
     * @param value
     *     allowed object is
     *     {@link SimStateType }
     *     
     */
    public void setSimState(SimStateType value) {
        this.simState = value;
    }

}
