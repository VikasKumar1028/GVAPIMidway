
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetOrCreateSimFromVirtualSubscriptionStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GetOrCreateSimFromVirtualSubscriptionStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PENDING"/>
 *     &lt;enumeration value="COMPLETE"/>
 *     &lt;enumeration value="FAILED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GetOrCreateSimFromVirtualSubscriptionStatus")
@XmlEnum
public enum GetOrCreateSimFromVirtualSubscriptionStatus {

    PENDING,
    COMPLETE,
    FAILED;

    public String value() {
        return name();
    }

    public static GetOrCreateSimFromVirtualSubscriptionStatus fromValue(String v) {
        return valueOf(v);
    }

}
