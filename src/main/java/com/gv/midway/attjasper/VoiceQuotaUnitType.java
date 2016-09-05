
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VoiceQuotaUnitType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="VoiceQuotaUnitType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MINUTES"/>
 *     &lt;enumeration value="SECONDS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "VoiceQuotaUnitType")
@XmlEnum
public enum VoiceQuotaUnitType {

    MINUTES,
    SECONDS;

    public String value() {
        return name();
    }

    public static VoiceQuotaUnitType fromValue(String v) {
        return valueOf(v);
    }

}
