
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GlobalSimOtaAlgoType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GlobalSimOtaAlgoType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="GND"/>
 *     &lt;enumeration value="GSMA"/>
 *     &lt;enumeration value="INTERSPTRANSFER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GlobalSimOtaAlgoType")
@XmlEnum
public enum GlobalSimOtaAlgoType {

    GND,
    GSMA,
    INTERSPTRANSFER;

    public String value() {
        return name();
    }

    public static GlobalSimOtaAlgoType fromValue(String v) {
        return valueOf(v);
    }

}
