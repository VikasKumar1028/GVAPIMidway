
package com.gv.midway.attjasper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


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
 *         &lt;element name="live" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="comingSoon" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="matchType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="retiring" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "live",
    "comingSoon",
    "matchType",
    "retiring",
    "any"
})
@XmlRootElement(name = "GetCoverageResponse")
public class GetCoverageResponse
    extends ResponseType
{

    protected boolean live;
    protected boolean comingSoon;
    @XmlElement(required = true)
    protected String matchType;
    protected boolean retiring;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the live property.
     * 
     */
    public boolean isLive() {
        return live;
    }

    /**
     * Sets the value of the live property.
     * 
     */
    public void setLive(boolean value) {
        this.live = value;
    }

    /**
     * Gets the value of the comingSoon property.
     * 
     */
    public boolean isComingSoon() {
        return comingSoon;
    }

    /**
     * Sets the value of the comingSoon property.
     * 
     */
    public void setComingSoon(boolean value) {
        this.comingSoon = value;
    }

    /**
     * Gets the value of the matchType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatchType() {
        return matchType;
    }

    /**
     * Sets the value of the matchType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatchType(String value) {
        this.matchType = value;
    }

    /**
     * Gets the value of the retiring property.
     * 
     */
    public boolean isRetiring() {
        return retiring;
    }

    /**
     * Sets the value of the retiring property.
     * 
     */
    public void setRetiring(boolean value) {
        this.retiring = value;
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

}
