
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="since" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="simState" type="{http://api.jasperwireless.com/ws/schema}SimStateType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="accountId" type="{http://www.w3.org/2001/XMLSchema}long" />
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
    "since",
    "simState"
})
@XmlRootElement(name = "GetModifiedTerminalsRequest")
public class GetModifiedTerminalsRequest
    extends RequestType
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar since;
    @XmlSchemaType(name = "string")
    protected SimStateType simState;
    @XmlAttribute(name = "accountId")
    protected Long accountId;

    /**
     * Gets the value of the since property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSince() {
        return since;
    }

    /**
     * Sets the value of the since property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSince(XMLGregorianCalendar value) {
        this.since = value;
    }

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

    /**
     * Gets the value of the accountId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * Sets the value of the accountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAccountId(Long value) {
        this.accountId = value;
    }

}
