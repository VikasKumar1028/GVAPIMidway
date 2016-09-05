
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
 *         &lt;element name="smsUsageDetails">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="smsUsageDetail" type="{http://api.jasperwireless.com/ws/schema}SmsUsageDetailType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;any processContents='lax' maxOccurs="unbounded" minOccurs="0"/>
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
    "smsUsageDetails",
    "any"
})
@XmlRootElement(name = "GetTerminalUsageSmsDetailsResponse")
public class GetTerminalUsageSmsDetailsResponse
    extends ResponseType
{

    @XmlElement(required = true)
    protected GetTerminalUsageSmsDetailsResponse.SmsUsageDetails smsUsageDetails;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the smsUsageDetails property.
     * 
     * @return
     *     possible object is
     *     {@link GetTerminalUsageSmsDetailsResponse.SmsUsageDetails }
     *     
     */
    public GetTerminalUsageSmsDetailsResponse.SmsUsageDetails getSmsUsageDetails() {
        return smsUsageDetails;
    }

    /**
     * Sets the value of the smsUsageDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetTerminalUsageSmsDetailsResponse.SmsUsageDetails }
     *     
     */
    public void setSmsUsageDetails(GetTerminalUsageSmsDetailsResponse.SmsUsageDetails value) {
        this.smsUsageDetails = value;
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
     *         &lt;element name="smsUsageDetail" type="{http://api.jasperwireless.com/ws/schema}SmsUsageDetailType" maxOccurs="unbounded" minOccurs="0"/>
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
        "smsUsageDetail"
    })
    public static class SmsUsageDetails {

        protected List<SmsUsageDetailType> smsUsageDetail;

        /**
         * Gets the value of the smsUsageDetail property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the smsUsageDetail property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSmsUsageDetail().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SmsUsageDetailType }
         * 
         * 
         */
        public List<SmsUsageDetailType> getSmsUsageDetail() {
            if (smsUsageDetail == null) {
                smsUsageDetail = new ArrayList<SmsUsageDetailType>();
            }
            return this.smsUsageDetail;
        }

    }

}
