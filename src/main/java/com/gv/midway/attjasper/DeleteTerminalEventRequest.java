
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="eventInstanceId" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "eventInstanceId"
})
@XmlRootElement(name = "DeleteTerminalEventRequest")
public class DeleteTerminalEventRequest
    extends RequestType
{

    protected long eventInstanceId;

    /**
     * Gets the value of the eventInstanceId property.
     * 
     */
    public long getEventInstanceId() {
        return eventInstanceId;
    }

    /**
     * Sets the value of the eventInstanceId property.
     * 
     */
    public void setEventInstanceId(long value) {
        this.eventInstanceId = value;
    }

}
