package com.gv.midway.pojo.deviceInformation.kore.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceInformationResponseKore
{
    private D d;

    public D getD ()
    {
        return d;
    }

    public void setD (D d)
    {
        this.d = d;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((d == null) ? 0 : d.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeviceInformationResponseKore other = (DeviceInformationResponseKore) obj;
		if (d == null) {
			if (other.d != null)
				return false;
		} else if (!d.equals(other.d))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeviceInformationResponseKore [d=");
		builder.append(d);
		builder.append("]");
		return builder.toString();
	}

   
}