
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
 *         &lt;element name="localUser" type="{http://api.jasperwireless.com/ws/schema}UserIdentifierType"/>
 *         &lt;element name="remoteUser" type="{http://api.jasperwireless.com/ws/schema}UserIdentifierType"/>
 *         &lt;element name="linkageType" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "localUser",
    "remoteUser",
    "linkageType"
})
@XmlRootElement(name = "GetAuthTokenRequest")
public class GetAuthTokenRequest
    extends RequestType
{

    @XmlElement(required = true)
    protected UserIdentifierType localUser;
    @XmlElement(required = true)
    protected UserIdentifierType remoteUser;
    protected long linkageType;

    /**
     * Gets the value of the localUser property.
     * 
     * @return
     *     possible object is
     *     {@link UserIdentifierType }
     *     
     */
    public UserIdentifierType getLocalUser() {
        return localUser;
    }

    /**
     * Sets the value of the localUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserIdentifierType }
     *     
     */
    public void setLocalUser(UserIdentifierType value) {
        this.localUser = value;
    }

    /**
     * Gets the value of the remoteUser property.
     * 
     * @return
     *     possible object is
     *     {@link UserIdentifierType }
     *     
     */
    public UserIdentifierType getRemoteUser() {
        return remoteUser;
    }

    /**
     * Sets the value of the remoteUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserIdentifierType }
     *     
     */
    public void setRemoteUser(UserIdentifierType value) {
        this.remoteUser = value;
    }

    /**
     * Gets the value of the linkageType property.
     * 
     */
    public long getLinkageType() {
        return linkageType;
    }

    /**
     * Sets the value of the linkageType property.
     * 
     */
    public void setLinkageType(long value) {
        this.linkageType = value;
    }

}
