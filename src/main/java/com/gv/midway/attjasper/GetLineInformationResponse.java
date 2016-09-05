
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
 *         &lt;element name="IMSI_RES" type="{http://api.jasperwireless.com/ws/schema}LineResultType" maxOccurs="50"/>
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
    "imsires"
})
@XmlRootElement(name = "GetLineInformationResponse")
public class GetLineInformationResponse
    extends ResponseType
{

    @XmlElement(name = "IMSI_RES", required = true)
    protected List<LineResultType> imsires;

    /**
     * Gets the value of the imsires property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the imsires property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIMSIRES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LineResultType }
     * 
     * 
     */
    public List<LineResultType> getIMSIRES() {
        if (imsires == null) {
            imsires = new ArrayList<LineResultType>();
        }
        return this.imsires;
    }

}
