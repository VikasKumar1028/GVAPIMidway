
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
 *         &lt;element name="nacIds">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="nacId" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded"/>
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
    "nacIds"
})
@XmlRootElement(name = "GetNetworkAccessConfigDetailsRequest")
public class GetNetworkAccessConfigDetailsRequest
    extends RequestType
{

    @XmlElement(required = true)
    protected GetNetworkAccessConfigDetailsRequest.NacIds nacIds;

    /**
     * Gets the value of the nacIds property.
     * 
     * @return
     *     possible object is
     *     {@link GetNetworkAccessConfigDetailsRequest.NacIds }
     *     
     */
    public GetNetworkAccessConfigDetailsRequest.NacIds getNacIds() {
        return nacIds;
    }

    /**
     * Sets the value of the nacIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetNetworkAccessConfigDetailsRequest.NacIds }
     *     
     */
    public void setNacIds(GetNetworkAccessConfigDetailsRequest.NacIds value) {
        this.nacIds = value;
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
     *         &lt;element name="nacId" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded"/>
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
        "nacId"
    })
    public static class NacIds {

        @XmlElement(type = Long.class)
        protected List<Long> nacId;

        /**
         * Gets the value of the nacId property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the nacId property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getNacId().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Long }
         * 
         * 
         */
        public List<Long> getNacId() {
            if (nacId == null) {
                nacId = new ArrayList<Long>();
            }
            return this.nacId;
        }

    }

}
