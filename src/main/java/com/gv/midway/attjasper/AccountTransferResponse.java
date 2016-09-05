
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
 *     &lt;extension base="{http://api.jasperwireless.com/ws/schema}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="AccountTransferStatusResponse" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="accountTransferStatus" type="{http://api.jasperwireless.com/ws/schema}SimTransferResponseType"/>
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
    "accountTransferStatusResponse"
})
@XmlRootElement(name = "AccountTransferResponse")
public class AccountTransferResponse
    extends ResponseType
{

    @XmlElement(name = "AccountTransferStatusResponse", required = true)
    protected List<AccountTransferResponse.AccountTransferStatusResponse> accountTransferStatusResponse;

    /**
     * Gets the value of the accountTransferStatusResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accountTransferStatusResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccountTransferStatusResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccountTransferResponse.AccountTransferStatusResponse }
     * 
     * 
     */
    public List<AccountTransferResponse.AccountTransferStatusResponse> getAccountTransferStatusResponse() {
        if (accountTransferStatusResponse == null) {
            accountTransferStatusResponse = new ArrayList<AccountTransferResponse.AccountTransferStatusResponse>();
        }
        return this.accountTransferStatusResponse;
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
     *         &lt;element name="accountTransferStatus" type="{http://api.jasperwireless.com/ws/schema}SimTransferResponseType"/>
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
        "accountTransferStatus"
    })
    public static class AccountTransferStatusResponse {

        @XmlElement(required = true)
        protected SimTransferResponseType accountTransferStatus;

        /**
         * Gets the value of the accountTransferStatus property.
         * 
         * @return
         *     possible object is
         *     {@link SimTransferResponseType }
         *     
         */
        public SimTransferResponseType getAccountTransferStatus() {
            return accountTransferStatus;
        }

        /**
         * Sets the value of the accountTransferStatus property.
         * 
         * @param value
         *     allowed object is
         *     {@link SimTransferResponseType }
         *     
         */
        public void setAccountTransferStatus(SimTransferResponseType value) {
            this.accountTransferStatus = value;
        }

    }

}
