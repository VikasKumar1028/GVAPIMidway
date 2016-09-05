
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for msisdnAssignmentStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="msisdnAssignmentStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MSISDN_COUNTER_LIMIT"/>
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="FAILURE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "msisdnAssignmentStatus")
@XmlEnum
public enum MsisdnAssignmentStatus {

    MSISDN_COUNTER_LIMIT,
    SUCCESS,
    FAILURE;

    public String value() {
        return name();
    }

    public static MsisdnAssignmentStatus fromValue(String v) {
        return valueOf(v);
    }

}
