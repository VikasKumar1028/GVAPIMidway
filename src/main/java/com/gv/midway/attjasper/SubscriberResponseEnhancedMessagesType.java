
package com.gv.midway.attjasper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         When the given request is Failed, list out error details from Network Service or Mail Services or Policy Service or Voicemail service .
 *     
 * 
 * <p>Java class for SubscriberResponseEnhancedMessagesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubscriberResponseEnhancedMessagesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="enhancedResponseMessage" type="{http://api.jasperwireless.com/ws/schema}SubscriberResponseEnhancedMessageType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubscriberResponseEnhancedMessagesType", propOrder = {
    "enhancedResponseMessage"
})
public class SubscriberResponseEnhancedMessagesType {

    @XmlElement(required = true)
    protected List<SubscriberResponseEnhancedMessageType> enhancedResponseMessage;

    /**
     * Gets the value of the enhancedResponseMessage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the enhancedResponseMessage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEnhancedResponseMessage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubscriberResponseEnhancedMessageType }
     * 
     * 
     */
    public List<SubscriberResponseEnhancedMessageType> getEnhancedResponseMessage() {
        if (enhancedResponseMessage == null) {
            enhancedResponseMessage = new ArrayList<SubscriberResponseEnhancedMessageType>();
        }
        return this.enhancedResponseMessage;
    }

}
