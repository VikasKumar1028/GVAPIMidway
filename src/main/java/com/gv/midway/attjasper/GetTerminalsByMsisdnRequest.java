
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
 *     &lt;extension base="{http://api.jasperwireless.com/ws/schema}RequestType">
 *       &lt;sequence>
 *         &lt;element name="msisdns">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
    "msisdns"
})
@XmlRootElement(name = "GetTerminalsByMsisdnRequest")
public class GetTerminalsByMsisdnRequest
    extends RequestType
{

    @XmlElement(required = true)
    protected GetTerminalsByMsisdnRequest.Msisdns msisdns;

    /**
     * Gets the value of the msisdns property.
     * 
     * @return
     *     possible object is
     *     {@link GetTerminalsByMsisdnRequest.Msisdns }
     *     
     */
    public GetTerminalsByMsisdnRequest.Msisdns getMsisdns() {
        return msisdns;
    }

    /**
     * Sets the value of the msisdns property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetTerminalsByMsisdnRequest.Msisdns }
     *     
     */
    public void setMsisdns(GetTerminalsByMsisdnRequest.Msisdns value) {
        this.msisdns = value;
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
     *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
        "msisdn"
    })
    public static class Msisdns {

        @XmlElement(required = true)
        protected List<String> msisdn;

        /**
         * Gets the value of the msisdn property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the msisdn property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMsisdn().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getMsisdn() {
            if (msisdn == null) {
                msisdn = new ArrayList<String>();
            }
            return this.msisdn;
        }

    }

}
