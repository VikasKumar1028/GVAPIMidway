
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SelectVirtualSubAndAssignMSISDNStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SelectVirtualSubAndAssignMSISDNStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PENDING"/>
 *     &lt;enumeration value="COMPLETE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SelectVirtualSubAndAssignMSISDNStatus")
@XmlEnum
public enum SelectVirtualSubAndAssignMSISDNStatus {

    PENDING,
    COMPLETE;

    public String value() {
        return name();
    }

    public static SelectVirtualSubAndAssignMSISDNStatus fromValue(String v) {
        return valueOf(v);
    }

}
