
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LocationServiceType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LocationServiceType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AYCE"/>
 *     &lt;enumeration value="PAYG"/>
 *     &lt;enumeration value="NONE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LocationServiceType")
@XmlEnum
public enum LocationServiceType {

    AYCE,
    PAYG,
    NONE;

    public String value() {
        return name();
    }

    public static LocationServiceType fromValue(String v) {
        return valueOf(v);
    }

}
