
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for messageTextEncodingType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="messageTextEncodingType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="LITERAL"/>
 *     &lt;enumeration value="BASE64"/>
 *     &lt;enumeration value="LITERAL_IRA"/>
 *     &lt;enumeration value="BASE64_IRA"/>
 *     &lt;enumeration value="LITERAL_BINARY"/>
 *     &lt;enumeration value="BASE64_BINARY"/>
 *     &lt;enumeration value="LITERAL_GSM7"/>
 *     &lt;enumeration value="BASE64_GSM7"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "messageTextEncodingType")
@XmlEnum
public enum MessageTextEncodingType {

    LITERAL("LITERAL"),
    @XmlEnumValue("BASE64")
    BASE_64("BASE64"),
    LITERAL_IRA("LITERAL_IRA"),
    @XmlEnumValue("BASE64_IRA")
    BASE_64_IRA("BASE64_IRA"),
    LITERAL_BINARY("LITERAL_BINARY"),
    @XmlEnumValue("BASE64_BINARY")
    BASE_64_BINARY("BASE64_BINARY"),
    @XmlEnumValue("LITERAL_GSM7")
    LITERAL_GSM_7("LITERAL_GSM7"),
    @XmlEnumValue("BASE64_GSM7")
    BASE_64_GSM_7("BASE64_GSM7");
    private final String value;

    MessageTextEncodingType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MessageTextEncodingType fromValue(String v) {
        for (MessageTextEncodingType c: MessageTextEncodingType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
