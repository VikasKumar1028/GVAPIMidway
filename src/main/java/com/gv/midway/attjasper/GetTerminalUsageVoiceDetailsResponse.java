
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
 *         &lt;element name="voiceUsageDetails">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="voiceUsageDetail" type="{http://api.jasperwireless.com/ws/schema}VoiceUsageDetailType" maxOccurs="unbounded" minOccurs="0"/>
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
    "voiceUsageDetails",
    "any"
})
@XmlRootElement(name = "GetTerminalUsageVoiceDetailsResponse")
public class GetTerminalUsageVoiceDetailsResponse
    extends ResponseType
{

    @XmlElement(required = true)
    protected GetTerminalUsageVoiceDetailsResponse.VoiceUsageDetails voiceUsageDetails;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the voiceUsageDetails property.
     * 
     * @return
     *     possible object is
     *     {@link GetTerminalUsageVoiceDetailsResponse.VoiceUsageDetails }
     *     
     */
    public GetTerminalUsageVoiceDetailsResponse.VoiceUsageDetails getVoiceUsageDetails() {
        return voiceUsageDetails;
    }

    /**
     * Sets the value of the voiceUsageDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetTerminalUsageVoiceDetailsResponse.VoiceUsageDetails }
     *     
     */
    public void setVoiceUsageDetails(GetTerminalUsageVoiceDetailsResponse.VoiceUsageDetails value) {
        this.voiceUsageDetails = value;
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
     *         &lt;element name="voiceUsageDetail" type="{http://api.jasperwireless.com/ws/schema}VoiceUsageDetailType" maxOccurs="unbounded" minOccurs="0"/>
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
        "voiceUsageDetail"
    })
    public static class VoiceUsageDetails {

        protected List<VoiceUsageDetailType> voiceUsageDetail;

        /**
         * Gets the value of the voiceUsageDetail property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the voiceUsageDetail property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getVoiceUsageDetail().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link VoiceUsageDetailType }
         * 
         * 
         */
        public List<VoiceUsageDetailType> getVoiceUsageDetail() {
            if (voiceUsageDetail == null) {
                voiceUsageDetail = new ArrayList<VoiceUsageDetailType>();
            }
            return this.voiceUsageDetail;
        }

    }

}
