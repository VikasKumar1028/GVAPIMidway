
package com.gv.midway.attjasper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


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
 *         &lt;element name="DeactivateTerminalStatus">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="DeactivateTerminal" type="{http://api.jasperwireless.com/ws/schema}SimTransferResponseType"/>
 *                   &lt;element name="simId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;any processContents='lax' maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "deactivateTerminalStatus"
})
@XmlRootElement(name = "DeactivateTerminalResponse")
public class DeactivateTerminalResponse
    extends ResponseType
{

    @XmlElement(name = "DeactivateTerminalStatus", required = true)
    protected DeactivateTerminalResponse.DeactivateTerminalStatus deactivateTerminalStatus;

    /**
     * Gets the value of the deactivateTerminalStatus property.
     * 
     * @return
     *     possible object is
     *     {@link DeactivateTerminalResponse.DeactivateTerminalStatus }
     *     
     */
    public DeactivateTerminalResponse.DeactivateTerminalStatus getDeactivateTerminalStatus() {
        return deactivateTerminalStatus;
    }

    /**
     * Sets the value of the deactivateTerminalStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeactivateTerminalResponse.DeactivateTerminalStatus }
     *     
     */
    public void setDeactivateTerminalStatus(DeactivateTerminalResponse.DeactivateTerminalStatus value) {
        this.deactivateTerminalStatus = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="DeactivateTerminal" type="{http://api.jasperwireless.com/ws/schema}SimTransferResponseType"/>
     *         &lt;element name="simId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;any processContents='lax' maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "deactivateTerminal",
        "simId",
        "any"
    })
    public static class DeactivateTerminalStatus {

        @XmlElement(name = "DeactivateTerminal", required = true)
        protected SimTransferResponseType deactivateTerminal;
        @XmlElement(required = true)
        protected String simId;
        @XmlAnyElement(lax = true)
        protected List<Object> any;

        /**
         * Gets the value of the deactivateTerminal property.
         * 
         * @return
         *     possible object is
         *     {@link SimTransferResponseType }
         *     
         */
        public SimTransferResponseType getDeactivateTerminal() {
            return deactivateTerminal;
        }

        /**
         * Sets the value of the deactivateTerminal property.
         * 
         * @param value
         *     allowed object is
         *     {@link SimTransferResponseType }
         *     
         */
        public void setDeactivateTerminal(SimTransferResponseType value) {
            this.deactivateTerminal = value;
        }

        /**
         * Gets the value of the simId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSimId() {
            return simId;
        }

        /**
         * Sets the value of the simId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSimId(String value) {
            this.simId = value;
        }

        /**
         * Gets the value of the any property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the any property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAny().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Element }
         * {@link Object }
         * 
         * 
         */
        public List<Object> getAny() {
            if (any == null) {
                any = new ArrayList<Object>();
            }
            return this.any;
        }

    }

}
