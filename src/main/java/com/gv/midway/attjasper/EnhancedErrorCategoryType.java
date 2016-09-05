
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnhancedErrorCategoryType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnhancedErrorCategoryType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NETWORK_SERVICES"/>
 *     &lt;enumeration value="MESSAGING_SERVICES"/>
 *     &lt;enumeration value="POLICY_SERVICES "/>
 *     &lt;enumeration value="VOICEMAIL_SERVICES"/>
 *     &lt;enumeration value="SIM_LOCKED"/>
 *     &lt;enumeration value="PROVISIONING_TIMEOUT"/>
 *     &lt;enumeration value="DELAYED"/>
 *     &lt;enumeration value="EXCEPTION_CONTACT_NETWORK_SUPPORT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnhancedErrorCategoryType")
@XmlEnum
public enum EnhancedErrorCategoryType {

    NETWORK_SERVICES("NETWORK_SERVICES"),
    MESSAGING_SERVICES("MESSAGING_SERVICES"),
    @XmlEnumValue("POLICY_SERVICES ")
    POLICY_SERVICES("POLICY_SERVICES "),
    VOICEMAIL_SERVICES("VOICEMAIL_SERVICES"),
    SIM_LOCKED("SIM_LOCKED"),
    PROVISIONING_TIMEOUT("PROVISIONING_TIMEOUT"),
    DELAYED("DELAYED"),
    EXCEPTION_CONTACT_NETWORK_SUPPORT("EXCEPTION_CONTACT_NETWORK_SUPPORT");
    private final String value;

    EnhancedErrorCategoryType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnhancedErrorCategoryType fromValue(String v) {
        for (EnhancedErrorCategoryType c: EnhancedErrorCategoryType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
