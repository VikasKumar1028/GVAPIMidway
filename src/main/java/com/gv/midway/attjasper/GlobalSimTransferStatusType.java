
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GlobalSimTransferStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GlobalSimTransferStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PENDING"/>
 *     &lt;enumeration value="COMPLETE"/>
 *     &lt;enumeration value="FAILED"/>
 *     &lt;enumeration value="CANCELLED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GlobalSimTransferStatusType")
@XmlEnum
public enum GlobalSimTransferStatusType {

    PENDING,
    COMPLETE,
    FAILED,
    CANCELLED;

    public String value() {
        return name();
    }

    public static GlobalSimTransferStatusType fromValue(String v) {
        return valueOf(v);
    }

}
