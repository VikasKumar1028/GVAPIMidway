
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
 *         &lt;element name="AccountResponse">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://api.jasperwireless.com/ws/schema}AccountResponseType">
 *                 &lt;sequence>
 *                   &lt;group ref="{http://api.jasperwireless.com/ws/schema}AvailableRatePlansResponseParamGroup"/>
 *                 &lt;/sequence>
 *               &lt;/extension>
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
    "accountResponse"
})
@XmlRootElement(name = "GlobalGetAvailableEventRatePlansResponse")
public class GlobalGetAvailableEventRatePlansResponse
    extends ResponseType
{

    @XmlElement(name = "AccountResponse", required = true)
    protected GlobalGetAvailableEventRatePlansResponse.AccountResponse accountResponse;

    /**
     * Gets the value of the accountResponse property.
     * 
     * @return
     *     possible object is
     *     {@link GlobalGetAvailableEventRatePlansResponse.AccountResponse }
     *     
     */
    public GlobalGetAvailableEventRatePlansResponse.AccountResponse getAccountResponse() {
        return accountResponse;
    }

    /**
     * Sets the value of the accountResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link GlobalGetAvailableEventRatePlansResponse.AccountResponse }
     *     
     */
    public void setAccountResponse(GlobalGetAvailableEventRatePlansResponse.AccountResponse value) {
        this.accountResponse = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://api.jasperwireless.com/ws/schema}AccountResponseType">
     *       &lt;sequence>
     *         &lt;group ref="{http://api.jasperwireless.com/ws/schema}AvailableRatePlansResponseParamGroup"/>
     *       &lt;/sequence>
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "availableEvents",
        "any"
    })
    public static class AccountResponse
        extends AccountResponseType
    {

        @XmlElement(name = "AvailableEvents", required = true)
        protected com.gv.midway.attjasper.GetAvailableEventsResponse.AvailableEvents availableEvents;
        @XmlAnyElement(lax = true)
        protected List<Object> any;

        /**
         * Gets the value of the availableEvents property.
         * 
         * @return
         *     possible object is
         *     {@link com.gv.midway.attjasper.GetAvailableEventsResponse.AvailableEvents }
         *     
         */
        public com.gv.midway.attjasper.GetAvailableEventsResponse.AvailableEvents getAvailableEvents() {
            return availableEvents;
        }

        /**
         * Sets the value of the availableEvents property.
         * 
         * @param value
         *     allowed object is
         *     {@link com.gv.midway.attjasper.GetAvailableEventsResponse.AvailableEvents }
         *     
         */
        public void setAvailableEvents(com.gv.midway.attjasper.GetAvailableEventsResponse.AvailableEvents value) {
            this.availableEvents = value;
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
