
package com.gv.midway.attjasper;

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
 *         &lt;element name="UpdateImsiDetails">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="simId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="accountName" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "updateImsiDetails"
})
@XmlRootElement(name = "UpdateImsiDetailsRequest")
public class UpdateImsiDetailsRequest
    extends RequestType
{

    @XmlElement(name = "UpdateImsiDetails", required = true)
    protected UpdateImsiDetailsRequest.UpdateImsiDetails updateImsiDetails;

    /**
     * Gets the value of the updateImsiDetails property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateImsiDetailsRequest.UpdateImsiDetails }
     *     
     */
    public UpdateImsiDetailsRequest.UpdateImsiDetails getUpdateImsiDetails() {
        return updateImsiDetails;
    }

    /**
     * Sets the value of the updateImsiDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateImsiDetailsRequest.UpdateImsiDetails }
     *     
     */
    public void setUpdateImsiDetails(UpdateImsiDetailsRequest.UpdateImsiDetails value) {
        this.updateImsiDetails = value;
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
     *         &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="simId" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="accountName" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "iccid",
        "simId",
        "accountName"
    })
    public static class UpdateImsiDetails {

        @XmlElement(required = true)
        protected String iccid;
        protected long simId;
        @XmlElement(required = true)
        protected String accountName;

        /**
         * Gets the value of the iccid property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIccid() {
            return iccid;
        }

        /**
         * Sets the value of the iccid property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIccid(String value) {
            this.iccid = value;
        }

        /**
         * Gets the value of the simId property.
         * 
         */
        public long getSimId() {
            return simId;
        }

        /**
         * Sets the value of the simId property.
         * 
         */
        public void setSimId(long value) {
            this.simId = value;
        }

        /**
         * Gets the value of the accountName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAccountName() {
            return accountName;
        }

        /**
         * Sets the value of the accountName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAccountName(String value) {
            this.accountName = value;
        }

    }

}
