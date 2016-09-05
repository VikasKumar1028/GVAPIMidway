
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
 *         &lt;element name="transferId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="primaryICCID" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "transferId",
    "primaryICCID"
})
@XmlRootElement(name = "GlobalTransferSimResponse")
public class GlobalTransferSimResponse
    extends ResponseType
{

    @XmlElement(required = true)
    protected String transferId;
    @XmlElement(required = true)
    protected String primaryICCID;

    /**
     * Gets the value of the transferId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransferId() {
        return transferId;
    }

    /**
     * Sets the value of the transferId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransferId(String value) {
        this.transferId = value;
    }

    /**
     * Gets the value of the primaryICCID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryICCID() {
        return primaryICCID;
    }

    /**
     * Sets the value of the primaryICCID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryICCID(String value) {
        this.primaryICCID = value;
    }

}
