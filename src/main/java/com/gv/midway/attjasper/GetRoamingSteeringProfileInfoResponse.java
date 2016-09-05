
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
 *     &lt;extension base="{http://api.jasperwireless.com/ws/schema}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="RSPID_RES" type="{http://api.jasperwireless.com/ws/schema}RoamingSteeringProfileInfoResponseType" maxOccurs="50"/>
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
    "rspidres"
})
@XmlRootElement(name = "GetRoamingSteeringProfileInfoResponse")
public class GetRoamingSteeringProfileInfoResponse
    extends ResponseType
{

    @XmlElement(name = "RSPID_RES", required = true)
    protected List<RoamingSteeringProfileInfoResponseType> rspidres;

    /**
     * Gets the value of the rspidres property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rspidres property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRSPIDRES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RoamingSteeringProfileInfoResponseType }
     * 
     * 
     */
    public List<RoamingSteeringProfileInfoResponseType> getRSPIDRES() {
        if (rspidres == null) {
            rspidres = new ArrayList<RoamingSteeringProfileInfoResponseType>();
        }
        return this.rspidres;
    }

}
