
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
 *         &lt;element name="alertIds">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="alertId" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded"/>
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
    "alertIds"
})
@XmlRootElement(name = "GetAlertDetailsRequest")
public class GetAlertDetailsRequest
    extends RequestType
{

    @XmlElement(required = true)
    protected GetAlertDetailsRequest.AlertIds alertIds;

    /**
     * Gets the value of the alertIds property.
     * 
     * @return
     *     possible object is
     *     {@link GetAlertDetailsRequest.AlertIds }
     *     
     */
    public GetAlertDetailsRequest.AlertIds getAlertIds() {
        return alertIds;
    }

    /**
     * Sets the value of the alertIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetAlertDetailsRequest.AlertIds }
     *     
     */
    public void setAlertIds(GetAlertDetailsRequest.AlertIds value) {
        this.alertIds = value;
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
     *         &lt;element name="alertId" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded"/>
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
        "alertId"
    })
    public static class AlertIds {

        @XmlElement(type = Long.class)
        protected List<Long> alertId;

        /**
         * Gets the value of the alertId property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the alertId property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAlertId().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Long }
         * 
         * 
         */
        public List<Long> getAlertId() {
            if (alertId == null) {
                alertId = new ArrayList<Long>();
            }
            return this.alertId;
        }

    }

}
