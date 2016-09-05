
package com.gv.midway.attjasper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.w3c.dom.Element;


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
 *         &lt;element name="AddTerminal">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="externalSimProfileId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="sourceOperatorName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="targetOperatorName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="terminalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="modemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="suspended" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="dateAdded" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="dateShipped" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                   &lt;element name="imsi" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="primaryICCID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="imei" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="simNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="pin1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="puk1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="pin2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="puk2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="kiEnc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="adm3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="adm4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="acc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="dlKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="dualImsiCapable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="dualImsiEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="simBound" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="adm1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="adm2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="secureSimId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="custom1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="custom2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="custom3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="customerCustom1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="customerCustom2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="customerCustom3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="customerCustom4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="customerCustom5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="operatorCustom1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="operatorCustom2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="operatorCustom3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="operatorCustom4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="operatorCustom5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="globalSimType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="copyCustomFields" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="targetAccountName" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "addTerminal",
    "copyCustomFields",
    "targetAccountName",
    "any"
})
@XmlRootElement(name = "AddTerminalRequest")
public class AddTerminalRequest
    extends RequestType
{

    @XmlElement(name = "AddTerminal", required = true)
    protected AddTerminalRequest.AddTerminal addTerminal;
    protected Boolean copyCustomFields;
    @XmlElement(required = true)
    protected String targetAccountName;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the addTerminal property.
     * 
     * @return
     *     possible object is
     *     {@link AddTerminalRequest.AddTerminal }
     *     
     */
    public AddTerminalRequest.AddTerminal getAddTerminal() {
        return addTerminal;
    }

    /**
     * Sets the value of the addTerminal property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddTerminalRequest.AddTerminal }
     *     
     */
    public void setAddTerminal(AddTerminalRequest.AddTerminal value) {
        this.addTerminal = value;
    }

    /**
     * Gets the value of the copyCustomFields property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCopyCustomFields() {
        return copyCustomFields;
    }

    /**
     * Sets the value of the copyCustomFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCopyCustomFields(Boolean value) {
        this.copyCustomFields = value;
    }

    /**
     * Gets the value of the targetAccountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetAccountName() {
        return targetAccountName;
    }

    /**
     * Sets the value of the targetAccountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetAccountName(String value) {
        this.targetAccountName = value;
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
     * 
     *                                     The detail information about SIM being added.
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
     *         &lt;element name="iccid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="externalSimProfileId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="sourceOperatorName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="targetOperatorName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="terminalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="modemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="suspended" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="dateAdded" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="dateShipped" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *         &lt;element name="imsi" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="primaryICCID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="imei" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="simNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="pin1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="puk1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="pin2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="puk2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="kiEnc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="adm3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="adm4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="acc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="dlKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="dualImsiCapable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="dualImsiEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="simBound" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="adm1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="adm2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="secureSimId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="custom1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="custom2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="custom3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="customerCustom1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="customerCustom2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="customerCustom3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="customerCustom4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="customerCustom5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="operatorCustom1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="operatorCustom2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="operatorCustom3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="operatorCustom4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="operatorCustom5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="globalSimType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string" />
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
        "externalSimProfileId",
        "sourceOperatorName",
        "targetOperatorName",
        "terminalId",
        "modemId",
        "suspended",
        "status",
        "dateAdded",
        "dateShipped",
        "imsi",
        "primaryICCID",
        "imei",
        "simNotes",
        "version",
        "pin1",
        "puk1",
        "pin2",
        "puk2",
        "kiEnc",
        "adm3",
        "adm4",
        "acc",
        "dlKey",
        "dualImsiCapable",
        "dualImsiEnabled",
        "simBound",
        "adm1",
        "adm2",
        "secureSimId",
        "custom1",
        "custom2",
        "custom3",
        "customerCustom1",
        "customerCustom2",
        "customerCustom3",
        "customerCustom4",
        "customerCustom5",
        "operatorCustom1",
        "operatorCustom2",
        "operatorCustom3",
        "operatorCustom4",
        "operatorCustom5",
        "globalSimType"
    })
    public static class AddTerminal {

        @XmlElement(required = true)
        protected String iccid;
        @XmlElement(required = true)
        protected String externalSimProfileId;
        @XmlElement(required = true)
        protected String sourceOperatorName;
        @XmlElement(required = true)
        protected String targetOperatorName;
        protected String terminalId;
        protected String modemId;
        @XmlElement(required = true)
        protected String suspended;
        @XmlElement(required = true)
        protected String status;
        @XmlElement(required = true)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar dateAdded;
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar dateShipped;
        @XmlElement(required = true)
        protected String imsi;
        protected String primaryICCID;
        protected String imei;
        protected String simNotes;
        protected int version;
        protected String pin1;
        protected String puk1;
        protected String pin2;
        protected String puk2;
        protected String kiEnc;
        protected String adm3;
        protected String adm4;
        protected String acc;
        protected String dlKey;
        protected Boolean dualImsiCapable;
        protected Boolean dualImsiEnabled;
        protected Boolean simBound;
        protected String adm1;
        protected String adm2;
        protected String secureSimId;
        protected String custom1;
        protected String custom2;
        protected String custom3;
        protected String customerCustom1;
        protected String customerCustom2;
        protected String customerCustom3;
        protected String customerCustom4;
        protected String customerCustom5;
        protected String operatorCustom1;
        protected String operatorCustom2;
        protected String operatorCustom3;
        protected String operatorCustom4;
        protected String operatorCustom5;
        protected String globalSimType;
        @XmlAttribute(name = "msisdn")
        protected String msisdn;

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
         * Gets the value of the externalSimProfileId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getExternalSimProfileId() {
            return externalSimProfileId;
        }

        /**
         * Sets the value of the externalSimProfileId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setExternalSimProfileId(String value) {
            this.externalSimProfileId = value;
        }

        /**
         * Gets the value of the sourceOperatorName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSourceOperatorName() {
            return sourceOperatorName;
        }

        /**
         * Sets the value of the sourceOperatorName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSourceOperatorName(String value) {
            this.sourceOperatorName = value;
        }

        /**
         * Gets the value of the targetOperatorName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTargetOperatorName() {
            return targetOperatorName;
        }

        /**
         * Sets the value of the targetOperatorName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTargetOperatorName(String value) {
            this.targetOperatorName = value;
        }

        /**
         * Gets the value of the terminalId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTerminalId() {
            return terminalId;
        }

        /**
         * Sets the value of the terminalId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTerminalId(String value) {
            this.terminalId = value;
        }

        /**
         * Gets the value of the modemId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getModemId() {
            return modemId;
        }

        /**
         * Sets the value of the modemId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setModemId(String value) {
            this.modemId = value;
        }

        /**
         * Gets the value of the suspended property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSuspended() {
            return suspended;
        }

        /**
         * Sets the value of the suspended property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSuspended(String value) {
            this.suspended = value;
        }

        /**
         * Gets the value of the status property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStatus() {
            return status;
        }

        /**
         * Sets the value of the status property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStatus(String value) {
            this.status = value;
        }

        /**
         * Gets the value of the dateAdded property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDateAdded() {
            return dateAdded;
        }

        /**
         * Sets the value of the dateAdded property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDateAdded(XMLGregorianCalendar value) {
            this.dateAdded = value;
        }

        /**
         * Gets the value of the dateShipped property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDateShipped() {
            return dateShipped;
        }

        /**
         * Sets the value of the dateShipped property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDateShipped(XMLGregorianCalendar value) {
            this.dateShipped = value;
        }

        /**
         * Gets the value of the imsi property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getImsi() {
            return imsi;
        }

        /**
         * Sets the value of the imsi property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setImsi(String value) {
            this.imsi = value;
        }

        /**
         * Gets the value of the primaryICCID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPrimaryICCID() {
            return primaryICCID;
        }

        /**
         * Sets the value of the primaryICCID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPrimaryICCID(String value) {
            this.primaryICCID = value;
        }

        /**
         * Gets the value of the imei property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getImei() {
            return imei;
        }

        /**
         * Sets the value of the imei property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setImei(String value) {
            this.imei = value;
        }

        /**
         * Gets the value of the simNotes property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSimNotes() {
            return simNotes;
        }

        /**
         * Sets the value of the simNotes property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSimNotes(String value) {
            this.simNotes = value;
        }

        /**
         * Gets the value of the version property.
         * 
         */
        public int getVersion() {
            return version;
        }

        /**
         * Sets the value of the version property.
         * 
         */
        public void setVersion(int value) {
            this.version = value;
        }

        /**
         * Gets the value of the pin1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPin1() {
            return pin1;
        }

        /**
         * Sets the value of the pin1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPin1(String value) {
            this.pin1 = value;
        }

        /**
         * Gets the value of the puk1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPuk1() {
            return puk1;
        }

        /**
         * Sets the value of the puk1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPuk1(String value) {
            this.puk1 = value;
        }

        /**
         * Gets the value of the pin2 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPin2() {
            return pin2;
        }

        /**
         * Sets the value of the pin2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPin2(String value) {
            this.pin2 = value;
        }

        /**
         * Gets the value of the puk2 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPuk2() {
            return puk2;
        }

        /**
         * Sets the value of the puk2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPuk2(String value) {
            this.puk2 = value;
        }

        /**
         * Gets the value of the kiEnc property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getKiEnc() {
            return kiEnc;
        }

        /**
         * Sets the value of the kiEnc property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setKiEnc(String value) {
            this.kiEnc = value;
        }

        /**
         * Gets the value of the adm3 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAdm3() {
            return adm3;
        }

        /**
         * Sets the value of the adm3 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAdm3(String value) {
            this.adm3 = value;
        }

        /**
         * Gets the value of the adm4 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAdm4() {
            return adm4;
        }

        /**
         * Sets the value of the adm4 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAdm4(String value) {
            this.adm4 = value;
        }

        /**
         * Gets the value of the acc property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAcc() {
            return acc;
        }

        /**
         * Sets the value of the acc property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAcc(String value) {
            this.acc = value;
        }

        /**
         * Gets the value of the dlKey property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDlKey() {
            return dlKey;
        }

        /**
         * Sets the value of the dlKey property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDlKey(String value) {
            this.dlKey = value;
        }

        /**
         * Gets the value of the dualImsiCapable property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isDualImsiCapable() {
            return dualImsiCapable;
        }

        /**
         * Sets the value of the dualImsiCapable property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setDualImsiCapable(Boolean value) {
            this.dualImsiCapable = value;
        }

        /**
         * Gets the value of the dualImsiEnabled property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isDualImsiEnabled() {
            return dualImsiEnabled;
        }

        /**
         * Sets the value of the dualImsiEnabled property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setDualImsiEnabled(Boolean value) {
            this.dualImsiEnabled = value;
        }

        /**
         * Gets the value of the simBound property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isSimBound() {
            return simBound;
        }

        /**
         * Sets the value of the simBound property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setSimBound(Boolean value) {
            this.simBound = value;
        }

        /**
         * Gets the value of the adm1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAdm1() {
            return adm1;
        }

        /**
         * Sets the value of the adm1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAdm1(String value) {
            this.adm1 = value;
        }

        /**
         * Gets the value of the adm2 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAdm2() {
            return adm2;
        }

        /**
         * Sets the value of the adm2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAdm2(String value) {
            this.adm2 = value;
        }

        /**
         * Gets the value of the secureSimId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSecureSimId() {
            return secureSimId;
        }

        /**
         * Sets the value of the secureSimId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSecureSimId(String value) {
            this.secureSimId = value;
        }

        /**
         * Gets the value of the custom1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCustom1() {
            return custom1;
        }

        /**
         * Sets the value of the custom1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCustom1(String value) {
            this.custom1 = value;
        }

        /**
         * Gets the value of the custom2 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCustom2() {
            return custom2;
        }

        /**
         * Sets the value of the custom2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCustom2(String value) {
            this.custom2 = value;
        }

        /**
         * Gets the value of the custom3 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCustom3() {
            return custom3;
        }

        /**
         * Sets the value of the custom3 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCustom3(String value) {
            this.custom3 = value;
        }

        /**
         * Gets the value of the customerCustom1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCustomerCustom1() {
            return customerCustom1;
        }

        /**
         * Sets the value of the customerCustom1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCustomerCustom1(String value) {
            this.customerCustom1 = value;
        }

        /**
         * Gets the value of the customerCustom2 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCustomerCustom2() {
            return customerCustom2;
        }

        /**
         * Sets the value of the customerCustom2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCustomerCustom2(String value) {
            this.customerCustom2 = value;
        }

        /**
         * Gets the value of the customerCustom3 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCustomerCustom3() {
            return customerCustom3;
        }

        /**
         * Sets the value of the customerCustom3 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCustomerCustom3(String value) {
            this.customerCustom3 = value;
        }

        /**
         * Gets the value of the customerCustom4 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCustomerCustom4() {
            return customerCustom4;
        }

        /**
         * Sets the value of the customerCustom4 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCustomerCustom4(String value) {
            this.customerCustom4 = value;
        }

        /**
         * Gets the value of the customerCustom5 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCustomerCustom5() {
            return customerCustom5;
        }

        /**
         * Sets the value of the customerCustom5 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCustomerCustom5(String value) {
            this.customerCustom5 = value;
        }

        /**
         * Gets the value of the operatorCustom1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOperatorCustom1() {
            return operatorCustom1;
        }

        /**
         * Sets the value of the operatorCustom1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOperatorCustom1(String value) {
            this.operatorCustom1 = value;
        }

        /**
         * Gets the value of the operatorCustom2 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOperatorCustom2() {
            return operatorCustom2;
        }

        /**
         * Sets the value of the operatorCustom2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOperatorCustom2(String value) {
            this.operatorCustom2 = value;
        }

        /**
         * Gets the value of the operatorCustom3 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOperatorCustom3() {
            return operatorCustom3;
        }

        /**
         * Sets the value of the operatorCustom3 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOperatorCustom3(String value) {
            this.operatorCustom3 = value;
        }

        /**
         * Gets the value of the operatorCustom4 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOperatorCustom4() {
            return operatorCustom4;
        }

        /**
         * Sets the value of the operatorCustom4 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOperatorCustom4(String value) {
            this.operatorCustom4 = value;
        }

        /**
         * Gets the value of the operatorCustom5 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOperatorCustom5() {
            return operatorCustom5;
        }

        /**
         * Sets the value of the operatorCustom5 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOperatorCustom5(String value) {
            this.operatorCustom5 = value;
        }

        /**
         * Gets the value of the globalSimType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGlobalSimType() {
            return globalSimType;
        }

        /**
         * Sets the value of the globalSimType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGlobalSimType(String value) {
            this.globalSimType = value;
        }

        /**
         * Gets the value of the msisdn property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMsisdn() {
            return msisdn;
        }

        /**
         * Sets the value of the msisdn property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMsisdn(String value) {
            this.msisdn = value;
        }

    }

}
