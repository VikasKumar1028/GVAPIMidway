
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="changetype" type="{http://api.jasperwireless.com/ws/schema}ChangeTypeEnum" minOccurs="0"/>
 *         &lt;element name="timezone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="language" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sharedSecretToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newSharedSecretToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "username",
    "changetype",
    "timezone",
    "language",
    "sharedSecretToken",
    "newSharedSecretToken"
})
@XmlRootElement(name = "EditUserForPartnersRequest")
public class EditUserForPartnersRequest
    extends RequestType
{

    protected String username;
    @XmlSchemaType(name = "string")
    protected ChangeTypeEnum changetype;
    protected String timezone;
    protected String language;
    protected String sharedSecretToken;
    protected String newSharedSecretToken;

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the changetype property.
     * 
     * @return
     *     possible object is
     *     {@link ChangeTypeEnum }
     *     
     */
    public ChangeTypeEnum getChangetype() {
        return changetype;
    }

    /**
     * Sets the value of the changetype property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChangeTypeEnum }
     *     
     */
    public void setChangetype(ChangeTypeEnum value) {
        this.changetype = value;
    }

    /**
     * Gets the value of the timezone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * Sets the value of the timezone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimezone(String value) {
        this.timezone = value;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the sharedSecretToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSharedSecretToken() {
        return sharedSecretToken;
    }

    /**
     * Sets the value of the sharedSecretToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSharedSecretToken(String value) {
        this.sharedSecretToken = value;
    }

    /**
     * Gets the value of the newSharedSecretToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewSharedSecretToken() {
        return newSharedSecretToken;
    }

    /**
     * Sets the value of the newSharedSecretToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewSharedSecretToken(String value) {
        this.newSharedSecretToken = value;
    }

}
