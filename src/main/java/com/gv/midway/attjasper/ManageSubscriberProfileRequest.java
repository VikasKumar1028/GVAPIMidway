
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
 *         &lt;element name="iccids">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="directives">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="directive" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
    "iccids",
    "directives"
})
@XmlRootElement(name = "ManageSubscriberProfileRequest")
public class ManageSubscriberProfileRequest
    extends RequestType
{

    @XmlElement(required = true)
    protected ManageSubscriberProfileRequest.Iccids iccids;
    @XmlElement(required = true)
    protected ManageSubscriberProfileRequest.Directives directives;

    /**
     * Gets the value of the iccids property.
     * 
     * @return
     *     possible object is
     *     {@link ManageSubscriberProfileRequest.Iccids }
     *     
     */
    public ManageSubscriberProfileRequest.Iccids getIccids() {
        return iccids;
    }

    /**
     * Sets the value of the iccids property.
     * 
     * @param value
     *     allowed object is
     *     {@link ManageSubscriberProfileRequest.Iccids }
     *     
     */
    public void setIccids(ManageSubscriberProfileRequest.Iccids value) {
        this.iccids = value;
    }

    /**
     * Gets the value of the directives property.
     * 
     * @return
     *     possible object is
     *     {@link ManageSubscriberProfileRequest.Directives }
     *     
     */
    public ManageSubscriberProfileRequest.Directives getDirectives() {
        return directives;
    }

    /**
     * Sets the value of the directives property.
     * 
     * @param value
     *     allowed object is
     *     {@link ManageSubscriberProfileRequest.Directives }
     *     
     */
    public void setDirectives(ManageSubscriberProfileRequest.Directives value) {
        this.directives = value;
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
     *         &lt;element name="directive" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
        "directive"
    })
    public static class Directives {

        @XmlElement(required = true)
        protected List<String> directive;

        /**
         * Gets the value of the directive property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the directive property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDirective().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getDirective() {
            if (directive == null) {
                directive = new ArrayList<String>();
            }
            return this.directive;
        }

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
     *         &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
        "iccid"
    })
    public static class Iccids {

        @XmlElement(required = true)
        protected List<String> iccid;

        /**
         * Gets the value of the iccid property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the iccid property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getIccid().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getIccid() {
            if (iccid == null) {
                iccid = new ArrayList<String>();
            }
            return this.iccid;
        }

    }

}
