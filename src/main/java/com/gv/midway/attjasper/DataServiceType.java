
package com.gv.midway.attjasper;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Data Service details:
 *         RoamBandwidthRuleId is valued with 501 - Canada, 601 - Mexico and 701 North America (Canada And Mexico) for
 *         International Roaming data policy
 * 
 *         Data Quota is to measure the data package for the subscriber.
 *         If date is UNLIMITED, data Quota is valued with "-1" and data quota unit must be "Bytes".
 *         Data Quota Unit always in Bytes or KB or MB or GB
 *         Data QOS is quality of Service, example Speed of the data service.
 *         Band Width Rule id is mandatory field to identify the ZIG pre-defined rule names for data service.
 *     
 * 
 * <p>Java class for DataServiceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataServiceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bandWidthRuleId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataQuota" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="dataQuotaUnit" type="{http://api.jasperwireless.com/ws/schema}DataQuotaUnitType"/>
 *         &lt;element name="dataQOS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataServiceType", propOrder = {
    "bandWidthRuleId",
    "dataQuota",
    "dataQuotaUnit",
    "dataQOS"
})
public class DataServiceType {

    @XmlElement(required = true)
    protected String bandWidthRuleId;
    @XmlElement(required = true)
    protected BigInteger dataQuota;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected DataQuotaUnitType dataQuotaUnit;
    protected String dataQOS;

    /**
     * Gets the value of the bandWidthRuleId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBandWidthRuleId() {
        return bandWidthRuleId;
    }

    /**
     * Sets the value of the bandWidthRuleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBandWidthRuleId(String value) {
        this.bandWidthRuleId = value;
    }

    /**
     * Gets the value of the dataQuota property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDataQuota() {
        return dataQuota;
    }

    /**
     * Sets the value of the dataQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDataQuota(BigInteger value) {
        this.dataQuota = value;
    }

    /**
     * Gets the value of the dataQuotaUnit property.
     * 
     * @return
     *     possible object is
     *     {@link DataQuotaUnitType }
     *     
     */
    public DataQuotaUnitType getDataQuotaUnit() {
        return dataQuotaUnit;
    }

    /**
     * Sets the value of the dataQuotaUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataQuotaUnitType }
     *     
     */
    public void setDataQuotaUnit(DataQuotaUnitType value) {
        this.dataQuotaUnit = value;
    }

    /**
     * Gets the value of the dataQOS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataQOS() {
        return dataQOS;
    }

    /**
     * Sets the value of the dataQOS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataQOS(String value) {
        this.dataQOS = value;
    }

}
