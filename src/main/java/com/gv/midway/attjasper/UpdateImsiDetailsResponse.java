
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
 *         &lt;element name="UpdateImsiDetailsStatus">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="UpdateImsiDetails" type="{http://api.jasperwireless.com/ws/schema}SimTransferResponseType"/>
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
    "updateImsiDetailsStatus"
})
@XmlRootElement(name = "UpdateImsiDetailsResponse")
public class UpdateImsiDetailsResponse
    extends ResponseType
{

    @XmlElement(name = "UpdateImsiDetailsStatus", required = true)
    protected UpdateImsiDetailsResponse.UpdateImsiDetailsStatus updateImsiDetailsStatus;

    /**
     * Gets the value of the updateImsiDetailsStatus property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateImsiDetailsResponse.UpdateImsiDetailsStatus }
     *     
     */
    public UpdateImsiDetailsResponse.UpdateImsiDetailsStatus getUpdateImsiDetailsStatus() {
        return updateImsiDetailsStatus;
    }

    /**
     * Sets the value of the updateImsiDetailsStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateImsiDetailsResponse.UpdateImsiDetailsStatus }
     *     
     */
    public void setUpdateImsiDetailsStatus(UpdateImsiDetailsResponse.UpdateImsiDetailsStatus value) {
        this.updateImsiDetailsStatus = value;
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
     *         &lt;element name="UpdateImsiDetails" type="{http://api.jasperwireless.com/ws/schema}SimTransferResponseType"/>
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
        "updateImsiDetails",
        "any"
    })
    public static class UpdateImsiDetailsStatus {

        @XmlElement(name = "UpdateImsiDetails", required = true)
        protected SimTransferResponseType updateImsiDetails;
        @XmlAnyElement(lax = true)
        protected List<Object> any;

        /**
         * Gets the value of the updateImsiDetails property.
         * 
         * @return
         *     possible object is
         *     {@link SimTransferResponseType }
         *     
         */
        public SimTransferResponseType getUpdateImsiDetails() {
            return updateImsiDetails;
        }

        /**
         * Sets the value of the updateImsiDetails property.
         * 
         * @param value
         *     allowed object is
         *     {@link SimTransferResponseType }
         *     
         */
        public void setUpdateImsiDetails(SimTransferResponseType value) {
            this.updateImsiDetails = value;
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
