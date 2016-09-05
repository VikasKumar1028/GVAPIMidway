
package com.gv.midway.attjasper;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LoginTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LoginTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="USERNAMEPASSWORDANDSAML"/>
 *     &lt;enumeration value="USERNAMEPASSWORD"/>
 *     &lt;enumeration value="SAML"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LoginTypeEnum")
@XmlEnum
public enum LoginTypeEnum {

    USERNAMEPASSWORDANDSAML,
    USERNAMEPASSWORD,
    SAML;

    public String value() {
        return name();
    }

    public static LoginTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
