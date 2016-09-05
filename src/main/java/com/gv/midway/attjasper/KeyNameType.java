
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KeyNameType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="KeyNameType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ICCID"/>
 *     &lt;enumeration value="MSISDN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "KeyNameType")
@XmlEnum
public enum KeyNameType {

    ICCID,
    MSISDN;

    public String value() {
        return name();
    }

    public static KeyNameType fromValue(String v) {
        return valueOf(v);
    }

}
