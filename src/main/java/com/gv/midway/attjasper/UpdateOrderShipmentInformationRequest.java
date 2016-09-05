
package com.gv.midway.attjasper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="JasperOrderID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ExtOrderID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShippedDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="OrderLine" type="{http://api.jasperwireless.com/ws/schema}OrderLine" maxOccurs="unbounded"/>
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
    "jasperOrderID",
    "extOrderID",
    "shippedDate",
    "orderLine"
})
@XmlRootElement(name = "UpdateOrderShipmentInformationRequest")
public class UpdateOrderShipmentInformationRequest
    extends RequestType
{

    @XmlElement(name = "JasperOrderID", required = true)
    protected String jasperOrderID;
    @XmlElement(name = "ExtOrderID", required = true)
    protected String extOrderID;
    @XmlElement(name = "ShippedDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar shippedDate;
    @XmlElement(name = "OrderLine", required = true)
    protected List<OrderLine> orderLine;

    /**
     * Gets the value of the jasperOrderID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJasperOrderID() {
        return jasperOrderID;
    }

    /**
     * Sets the value of the jasperOrderID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJasperOrderID(String value) {
        this.jasperOrderID = value;
    }

    /**
     * Gets the value of the extOrderID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtOrderID() {
        return extOrderID;
    }

    /**
     * Sets the value of the extOrderID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtOrderID(String value) {
        this.extOrderID = value;
    }

    /**
     * Gets the value of the shippedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getShippedDate() {
        return shippedDate;
    }

    /**
     * Sets the value of the shippedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setShippedDate(XMLGregorianCalendar value) {
        this.shippedDate = value;
    }

    /**
     * Gets the value of the orderLine property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderLine property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderLine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderLine }
     * 
     * 
     */
    public List<OrderLine> getOrderLine() {
        if (orderLine == null) {
            orderLine = new ArrayList<OrderLine>();
        }
        return this.orderLine;
    }

}
