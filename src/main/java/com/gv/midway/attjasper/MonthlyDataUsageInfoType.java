
package com.gv.midway.attjasper;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         The information is about monthly billable data usage for pooled rate plans
 *     
 * 
 * <p>Java class for MonthlyDataUsageInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MonthlyDataUsageInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accountName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="zoneName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ratePlanName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="totalIncludedZoneUsage" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="totalActualZoneUsage" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MonthlyDataUsageInfoType", propOrder = {
    "accountName",
    "zoneName",
    "ratePlanName",
    "totalIncludedZoneUsage",
    "totalActualZoneUsage"
})
@XmlSeeAlso({
    MonthlyDataUsage.class
})
public class MonthlyDataUsageInfoType {

    @XmlElement(required = true)
    protected String accountName;
    @XmlElement(required = true)
    protected String zoneName;
    @XmlElement(required = true)
    protected String ratePlanName;
    @XmlElement(required = true)
    protected BigDecimal totalIncludedZoneUsage;
    @XmlElement(required = true)
    protected BigDecimal totalActualZoneUsage;

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

    /**
     * Gets the value of the zoneName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZoneName() {
        return zoneName;
    }

    /**
     * Sets the value of the zoneName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZoneName(String value) {
        this.zoneName = value;
    }

    /**
     * Gets the value of the ratePlanName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatePlanName() {
        return ratePlanName;
    }

    /**
     * Sets the value of the ratePlanName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatePlanName(String value) {
        this.ratePlanName = value;
    }

    /**
     * Gets the value of the totalIncludedZoneUsage property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalIncludedZoneUsage() {
        return totalIncludedZoneUsage;
    }

    /**
     * Sets the value of the totalIncludedZoneUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalIncludedZoneUsage(BigDecimal value) {
        this.totalIncludedZoneUsage = value;
    }

    /**
     * Gets the value of the totalActualZoneUsage property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalActualZoneUsage() {
        return totalActualZoneUsage;
    }

    /**
     * Sets the value of the totalActualZoneUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalActualZoneUsage(BigDecimal value) {
        this.totalActualZoneUsage = value;
    }

}
