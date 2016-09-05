
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
 *         &lt;element name="AccountTransfer" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="authtoken" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="simid" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="sourceuser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="globaluser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="targetaccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="requestoraccountid" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="messageid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="sourceaccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="sourceoperator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="targetoperator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="globalaccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "accountTransfer"
})
@XmlRootElement(name = "AccountTransferRequest")
public class AccountTransferRequest
    extends RequestType
{

    @XmlElement(name = "AccountTransfer", required = true)
    protected List<AccountTransferRequest.AccountTransfer> accountTransfer;

    /**
     * Gets the value of the accountTransfer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accountTransfer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccountTransfer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccountTransferRequest.AccountTransfer }
     * 
     * 
     */
    public List<AccountTransferRequest.AccountTransfer> getAccountTransfer() {
        if (accountTransfer == null) {
            accountTransfer = new ArrayList<AccountTransferRequest.AccountTransfer>();
        }
        return this.accountTransfer;
    }


    /**
     * 
     *                                     The detail information about SIM being transferred across SPs.
     *                                 
     * 
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="authtoken" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="simid" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="sourceuser" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="globaluser" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="targetaccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="requestoraccountid" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="messageid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="sourceaccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="sourceoperator" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="targetoperator" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="globalaccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "authtoken",
        "iccid",
        "simid",
        "sourceuser",
        "globaluser",
        "targetaccount",
        "requestoraccountid",
        "messageid",
        "sourceaccount",
        "sourceoperator",
        "targetoperator",
        "globalaccount"
    })
    public static class AccountTransfer {

        @XmlElement(required = true)
        protected String authtoken;
        @XmlElement(required = true)
        protected String iccid;
        protected long simid;
        @XmlElement(required = true)
        protected String sourceuser;
        @XmlElement(required = true)
        protected String globaluser;
        @XmlElement(required = true)
        protected String targetaccount;
        protected long requestoraccountid;
        @XmlElement(required = true)
        protected String messageid;
        @XmlElement(required = true)
        protected String sourceaccount;
        @XmlElement(required = true)
        protected String sourceoperator;
        @XmlElement(required = true)
        protected String targetoperator;
        @XmlElement(required = true)
        protected String globalaccount;

        /**
         * Gets the value of the authtoken property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAuthtoken() {
            return authtoken;
        }

        /**
         * Sets the value of the authtoken property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAuthtoken(String value) {
            this.authtoken = value;
        }

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
         * Gets the value of the simid property.
         * 
         */
        public long getSimid() {
            return simid;
        }

        /**
         * Sets the value of the simid property.
         * 
         */
        public void setSimid(long value) {
            this.simid = value;
        }

        /**
         * Gets the value of the sourceuser property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSourceuser() {
            return sourceuser;
        }

        /**
         * Sets the value of the sourceuser property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSourceuser(String value) {
            this.sourceuser = value;
        }

        /**
         * Gets the value of the globaluser property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGlobaluser() {
            return globaluser;
        }

        /**
         * Sets the value of the globaluser property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGlobaluser(String value) {
            this.globaluser = value;
        }

        /**
         * Gets the value of the targetaccount property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTargetaccount() {
            return targetaccount;
        }

        /**
         * Sets the value of the targetaccount property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTargetaccount(String value) {
            this.targetaccount = value;
        }

        /**
         * Gets the value of the requestoraccountid property.
         * 
         */
        public long getRequestoraccountid() {
            return requestoraccountid;
        }

        /**
         * Sets the value of the requestoraccountid property.
         * 
         */
        public void setRequestoraccountid(long value) {
            this.requestoraccountid = value;
        }

        /**
         * Gets the value of the messageid property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMessageid() {
            return messageid;
        }

        /**
         * Sets the value of the messageid property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMessageid(String value) {
            this.messageid = value;
        }

        /**
         * Gets the value of the sourceaccount property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSourceaccount() {
            return sourceaccount;
        }

        /**
         * Sets the value of the sourceaccount property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSourceaccount(String value) {
            this.sourceaccount = value;
        }

        /**
         * Gets the value of the sourceoperator property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSourceoperator() {
            return sourceoperator;
        }

        /**
         * Sets the value of the sourceoperator property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSourceoperator(String value) {
            this.sourceoperator = value;
        }

        /**
         * Gets the value of the targetoperator property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTargetoperator() {
            return targetoperator;
        }

        /**
         * Sets the value of the targetoperator property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTargetoperator(String value) {
            this.targetoperator = value;
        }

        /**
         * Gets the value of the globalaccount property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGlobalaccount() {
            return globalaccount;
        }

        /**
         * Sets the value of the globalaccount property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGlobalaccount(String value) {
            this.globalaccount = value;
        }

    }

}
