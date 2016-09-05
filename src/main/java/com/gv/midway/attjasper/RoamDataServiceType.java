
package com.gv.midway.attjasper;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RoamDataServiceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RoamDataServiceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="roamBandwidthRuleId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "RoamDataServiceType", propOrder = {
    "roamBandwidthRuleId",
    "dataQuota",
    "dataQuotaUnit",
    "dataQOS"
})
public class RoamDataServiceType {

    protected String roamBandwidthRuleId;
    @XmlElement(required = true)
    protected BigInteger dataQuota;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected DataQuotaUnitType dataQuotaUnit;
    protected String dataQOS;

    /**
     * Gets the value of the roamBandwidthRuleId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoamBandwidthRuleId() {
        return roamBandwidthRuleId;
    }

    /**
     * Sets the value of the roamBandwidthRuleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoamBandwidthRuleId(String value) {
        this.roamBandwidthRuleId = value;
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
