
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
 *         &lt;element name="DeactivateTerminal">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="simId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "deactivateTerminal"
})
@XmlRootElement(name = "DeactivateTerminalRequest")
public class DeactivateTerminalRequest
    extends RequestType
{

    @XmlElement(name = "DeactivateTerminal", required = true)
    protected DeactivateTerminalRequest.DeactivateTerminal deactivateTerminal;

    /**
     * Gets the value of the deactivateTerminal property.
     * 
     * @return
     *     possible object is
     *     {@link DeactivateTerminalRequest.DeactivateTerminal }
     *     
     */
    public DeactivateTerminalRequest.DeactivateTerminal getDeactivateTerminal() {
        return deactivateTerminal;
    }

    /**
     * Sets the value of the deactivateTerminal property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeactivateTerminalRequest.DeactivateTerminal }
     *     
     */
    public void setDeactivateTerminal(DeactivateTerminalRequest.DeactivateTerminal value) {
        this.deactivateTerminal = value;
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
     *         &lt;element name="simId" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "simId",
        "iccid"
    })
    public static class DeactivateTerminal {

        protected long simId;
        @XmlElement(required = true)
        protected String iccid;

        /**
         * Gets the value of the simId property.
         * 
         */
        public long getSimId() {
            return simId;
        }

        /**
         * Sets the value of the simId property.
         * 
         */
        public void setSimId(long value) {
            this.simId = value;
        }

        /**
         * Gets the value of the iccid property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIccid() {
            return iccid;
        }

        /**
         * Sets the value of the iccid property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIccid(String value) {
            this.iccid = value;
        }

    }

}
