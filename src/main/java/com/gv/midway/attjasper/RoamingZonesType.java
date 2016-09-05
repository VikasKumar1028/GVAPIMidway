
package com.gv.midway.attjasper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Roaming Zone will define roaming zone details of the Subscriber. Describes what are the services (Data, Voice, MMS or SMS) are allowed in Roaming zones for given subscriber.
 *     
 * 
 * <p>Java class for RoamingZonesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RoamingZonesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="roamingZone" type="{http://api.jasperwireless.com/ws/schema}RoamingZoneType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoamingZonesType", propOrder = {
    "roamingZone"
})
public class RoamingZonesType {

    @XmlElement(required = true)
    protected List<RoamingZoneType> roamingZone;

    /**
     * Gets the value of the roamingZone property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roamingZone property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoamingZone().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RoamingZoneType }
     * 
     * 
     */
    public List<RoamingZoneType> getRoamingZone() {
        if (roamingZone == null) {
            roamingZone = new ArrayList<RoamingZoneType>();
        }
        return this.roamingZone;
    }

}
