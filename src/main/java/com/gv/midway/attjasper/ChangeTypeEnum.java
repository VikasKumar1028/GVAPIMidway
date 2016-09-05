
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChangeTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ChangeTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="TIMEZONE"/>
 *     &lt;enumeration value="LANGUAGE"/>
 *     &lt;enumeration value="ALL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ChangeTypeEnum")
@XmlEnum
public enum ChangeTypeEnum {

    TIMEZONE,
    LANGUAGE,
    ALL;

    public String value() {
        return name();
    }

    public static ChangeTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
