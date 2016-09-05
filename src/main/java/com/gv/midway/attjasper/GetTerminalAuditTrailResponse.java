
package com.gv.midway.attjasper;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="terminalAuditTrails">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="terminalAuditTrail" type="{http://api.jasperwireless.com/ws/schema}TerminalAuditTrailType" maxOccurs="unbounded" minOccurs="0"/>
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
    "terminalAuditTrails"
})
@XmlRootElement(name = "GetTerminalAuditTrailResponse")
public class GetTerminalAuditTrailResponse
    extends ResponseType
{

    @XmlElement(required = true)
    protected GetTerminalAuditTrailResponse.TerminalAuditTrails terminalAuditTrails;

    /**
     * Gets the value of the terminalAuditTrails property.
     * 
     * @return
     *     possible object is
     *     {@link GetTerminalAuditTrailResponse.TerminalAuditTrails }
     *     
     */
    public GetTerminalAuditTrailResponse.TerminalAuditTrails getTerminalAuditTrails() {
        return terminalAuditTrails;
    }

    /**
     * Sets the value of the terminalAuditTrails property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetTerminalAuditTrailResponse.TerminalAuditTrails }
     *     
     */
    public void setTerminalAuditTrails(GetTerminalAuditTrailResponse.TerminalAuditTrails value) {
        this.terminalAuditTrails = value;
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
     *         &lt;element name="terminalAuditTrail" type="{http://api.jasperwireless.com/ws/schema}TerminalAuditTrailType" maxOccurs="unbounded" minOccurs="0"/>
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
        "terminalAuditTrail"
    })
    public static class TerminalAuditTrails {

        protected List<TerminalAuditTrailType> terminalAuditTrail;

        /**
         * Gets the value of the terminalAuditTrail property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the terminalAuditTrail property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTerminalAuditTrail().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TerminalAuditTrailType }
         * 
         * 
         */
        public List<TerminalAuditTrailType> getTerminalAuditTrail() {
            if (terminalAuditTrail == null) {
                terminalAuditTrail = new ArrayList<TerminalAuditTrailType>();
            }
            return this.terminalAuditTrail;
        }

    }

}
