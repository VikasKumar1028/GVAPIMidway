
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *             The account-specific response information received from the call to the individual account (on a particular POD)
 *             that should / would be included in the "global" response to the Global API Request
 *         
 * 
 * <p>Java class for AccountResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="account" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="operator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountResponseType")
@XmlSeeAlso({
    com.gv.midway.attjasper.GlobalSendSMSResponse.AccountResponse.class,
    com.gv.midway.attjasper.GlobalGetTerminalLatestRegistrationResponse.AccountResponse.class,
    com.gv.midway.attjasper.GlobalGetAvailableEventRatePlansResponse.AccountResponse.class,
    com.gv.midway.attjasper.GlobalActivateTerminalEventRatePlanResponse.AccountResponse.class,
    com.gv.midway.attjasper.GlobalGetTerminalEventRatePlansResponse.AccountResponse.class,
    com.gv.midway.attjasper.GlobalEditTerminalResponse.AccountResponse.class,
    com.gv.midway.attjasper.GlobalAPIFault.AccountResponse.class,
    com.gv.midway.attjasper.GlobalGetTerminalDetailsResponse.AccountResponse.class
})
public class AccountResponseType {

    @XmlAttribute(name = "account")
    protected String account;
    @XmlAttribute(name = "operator")
    protected String operator;

    /**
     * Gets the value of the account property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccount() {
        return account;
    }

    /**
     * Sets the value of the account property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccount(String value) {
        this.account = value;
    }

    /**
     * Gets the value of the operator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Sets the value of the operator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperator(String value) {
        this.operator = value;
    }

}
