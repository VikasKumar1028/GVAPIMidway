
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
 *         &lt;element name="usageDetails">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="usageDetail" type="{http://api.jasperwireless.com/ws/schema}DataUsageDetailType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="totalPages" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "usageDetails",
    "totalPages",
    "any"
})
@XmlRootElement(name = "GetTerminalUsageDataDetailsResponse")
public class GetTerminalUsageDataDetailsResponse
    extends ResponseType
{

    @XmlElement(required = true)
    protected GetTerminalUsageDataDetailsResponse.UsageDetails usageDetails;
    protected int totalPages;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the usageDetails property.
     * 
     * @return
     *     possible object is
     *     {@link GetTerminalUsageDataDetailsResponse.UsageDetails }
     *     
     */
    public GetTerminalUsageDataDetailsResponse.UsageDetails getUsageDetails() {
        return usageDetails;
    }

    /**
     * Sets the value of the usageDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetTerminalUsageDataDetailsResponse.UsageDetails }
     *     
     */
    public void setUsageDetails(GetTerminalUsageDataDetailsResponse.UsageDetails value) {
        this.usageDetails = value;
    }

    /**
     * Gets the value of the totalPages property.
     * 
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * Sets the value of the totalPages property.
     * 
     */
    public void setTotalPages(int value) {
        this.totalPages = value;
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
     *         &lt;element name="usageDetail" type="{http://api.jasperwireless.com/ws/schema}DataUsageDetailType" maxOccurs="unbounded" minOccurs="0"/>
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
        "usageDetail"
    })
    public static class UsageDetails {

        protected List<DataUsageDetailType> usageDetail;

        /**
         * Gets the value of the usageDetail property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the usageDetail property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getUsageDetail().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DataUsageDetailType }
         * 
         * 
         */
        public List<DataUsageDetailType> getUsageDetail() {
            if (usageDetail == null) {
                usageDetail = new ArrayList<DataUsageDetailType>();
            }
            return this.usageDetail;
        }

    }

}
