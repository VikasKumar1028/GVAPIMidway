
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RoamingSteeringProfileInfoResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RoamingSteeringProfileInfoResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RSP_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RESULT" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DESCRIPTION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NETWORKS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoamingSteeringProfileInfoResponseType", propOrder = {
    "rspid",
    "result",
    "description",
    "networks"
})
public class RoamingSteeringProfileInfoResponseType {

    @XmlElement(name = "RSP_ID")
    protected int rspid;
    @XmlElement(name = "RESULT")
    protected int result;
    @XmlElement(name = "DESCRIPTION", required = true)
    protected String description;
    @XmlElement(name = "NETWORKS")
    protected String networks;

    /**
     * Gets the value of the rspid property.
     * 
     */
    public int getRSPID() {
        return rspid;
    }

    /**
     * Sets the value of the rspid property.
     * 
     */
    public void setRSPID(int value) {
        this.rspid = value;
    }

    /**
     * Gets the value of the result property.
     * 
     */
    public int getRESULT() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     */
    public void setRESULT(int value) {
        this.result = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDESCRIPTION() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDESCRIPTION(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the networks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNETWORKS() {
        return networks;
    }

    /**
     * Sets the value of the networks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNETWORKS(String value) {
        this.networks = value;
    }

}
