
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
 *         &lt;element name="AddTerminalStatusResponse">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="addTerminalStatus" type="{http://api.jasperwireless.com/ws/schema}SimTransferResponseType"/>
 *                   &lt;element name="newSimId" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "addTerminalStatusResponse"
})
@XmlRootElement(name = "AddTerminalResponse")
public class AddTerminalResponse
    extends ResponseType
{

    @XmlElement(name = "AddTerminalStatusResponse", required = true)
    protected AddTerminalResponse.AddTerminalStatusResponse addTerminalStatusResponse;

    /**
     * Gets the value of the addTerminalStatusResponse property.
     * 
     * @return
     *     possible object is
     *     {@link AddTerminalResponse.AddTerminalStatusResponse }
     *     
     */
    public AddTerminalResponse.AddTerminalStatusResponse getAddTerminalStatusResponse() {
        return addTerminalStatusResponse;
    }

    /**
     * Sets the value of the addTerminalStatusResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddTerminalResponse.AddTerminalStatusResponse }
     *     
     */
    public void setAddTerminalStatusResponse(AddTerminalResponse.AddTerminalStatusResponse value) {
        this.addTerminalStatusResponse = value;
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
     *         &lt;element name="addTerminalStatus" type="{http://api.jasperwireless.com/ws/schema}SimTransferResponseType"/>
     *         &lt;element name="newSimId" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "addTerminalStatus",
        "newSimId",
        "any"
    })
    public static class AddTerminalStatusResponse {

        @XmlElement(required = true)
        protected SimTransferResponseType addTerminalStatus;
        @XmlElement(required = true)
        protected String newSimId;
        @XmlAnyElement(lax = true)
        protected List<Object> any;

        /**
         * Gets the value of the addTerminalStatus property.
         * 
         * @return
         *     possible object is
         *     {@link SimTransferResponseType }
         *     
         */
        public SimTransferResponseType getAddTerminalStatus() {
            return addTerminalStatus;
        }

        /**
         * Sets the value of the addTerminalStatus property.
         * 
         * @param value
         *     allowed object is
         *     {@link SimTransferResponseType }
         *     
         */
        public void setAddTerminalStatus(SimTransferResponseType value) {
            this.addTerminalStatus = value;
        }

        /**
         * Gets the value of the newSimId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNewSimId() {
            return newSimId;
        }

        /**
         * Sets the value of the newSimId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNewSimId(String value) {
            this.newSimId = value;
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
