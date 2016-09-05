
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SimStateType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SimStateType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ACTIVATIONREADY"/>
 *     &lt;enumeration value="ACTIVATED"/>
 *     &lt;enumeration value="DEACTIVATED"/>
 *     &lt;enumeration value="PURGED"/>
 *     &lt;enumeration value="UNKNOWN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SimStateType")
@XmlEnum
public enum SimStateType {

    ACTIVATIONREADY,
    ACTIVATED,
    DEACTIVATED,
    PURGED,
    UNKNOWN;

    public String value() {
        return name();
    }

    public static SimStateType fromValue(String v) {
        return valueOf(v);
    }

}
