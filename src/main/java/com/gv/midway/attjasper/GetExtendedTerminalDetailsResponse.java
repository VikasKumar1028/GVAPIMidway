
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
 *         &lt;element name="extendedTerminalDetails">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="extendedTerminal" type="{http://api.jasperwireless.com/ws/schema}ExtendedTerminalType" minOccurs="0"/>
 *                   &lt;element name="getExtendedTerminalStatus" type="{http://api.jasperwireless.com/ws/schema}SimTransferResponseType"/>
 *                   &lt;element name="simId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "extendedTerminalDetails"
})
@XmlRootElement(name = "GetExtendedTerminalDetailsResponse")
public class GetExtendedTerminalDetailsResponse
    extends ResponseType
{

    @XmlElement(required = true)
    protected GetExtendedTerminalDetailsResponse.ExtendedTerminalDetails extendedTerminalDetails;

    /**
     * Gets the value of the extendedTerminalDetails property.
     * 
     * @return
     *     possible object is
     *     {@link GetExtendedTerminalDetailsResponse.ExtendedTerminalDetails }
     *     
     */
    public GetExtendedTerminalDetailsResponse.ExtendedTerminalDetails getExtendedTerminalDetails() {
        return extendedTerminalDetails;
    }

    /**
     * Sets the value of the extendedTerminalDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetExtendedTerminalDetailsResponse.ExtendedTerminalDetails }
     *     
     */
    public void setExtendedTerminalDetails(GetExtendedTerminalDetailsResponse.ExtendedTerminalDetails value) {
        this.extendedTerminalDetails = value;
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
     *         &lt;element name="extendedTerminal" type="{http://api.jasperwireless.com/ws/schema}ExtendedTerminalType" minOccurs="0"/>
     *         &lt;element name="getExtendedTerminalStatus" type="{http://api.jasperwireless.com/ws/schema}SimTransferResponseType"/>
     *         &lt;element name="simId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "extendedTerminal",
        "getExtendedTerminalStatus",
        "simId"
    })
    public static class ExtendedTerminalDetails {

        protected ExtendedTerminalType extendedTerminal;
        @XmlElement(required = true)
        protected SimTransferResponseType getExtendedTerminalStatus;
        protected String simId;

        /**
         * Gets the value of the extendedTerminal property.
         * 
         * @return
         *     possible object is
         *     {@link ExtendedTerminalType }
         *     
         */
        public ExtendedTerminalType getExtendedTerminal() {
            return extendedTerminal;
        }

        /**
         * Sets the value of the extendedTerminal property.
         * 
         * @param value
         *     allowed object is
         *     {@link ExtendedTerminalType }
         *     
         */
        public void setExtendedTerminal(ExtendedTerminalType value) {
            this.extendedTerminal = value;
        }

        /**
         * Gets the value of the getExtendedTerminalStatus property.
         * 
         * @return
         *     possible object is
         *     {@link SimTransferResponseType }
         *     
         */
        public SimTransferResponseType getGetExtendedTerminalStatus() {
            return getExtendedTerminalStatus;
        }

        /**
         * Sets the value of the getExtendedTerminalStatus property.
         * 
         * @param value
         *     allowed object is
         *     {@link SimTransferResponseType }
         *     
         */
        public void setGetExtendedTerminalStatus(SimTransferResponseType value) {
            this.getExtendedTerminalStatus = value;
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

    }

}
