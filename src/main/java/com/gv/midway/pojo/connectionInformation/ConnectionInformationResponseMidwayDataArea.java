package com.gv.midway.pojo.connectionInformation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionInformationResponseMidwayDataArea {

    private List<DeviceEvents> events;

    public List<DeviceEvents> getEvents() {
        return events;
    }

    public void setEvents(List<DeviceEvents> events) {
        this.events = events;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((events == null) ? 0 : events.hashCode());
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
        ConnectionInformationResponseMidwayDataArea other = (ConnectionInformationResponseMidwayDataArea) obj;
        if (events == null) {
            if (other.events != null)
                return false;
        } else if (!events.equals(other.events))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ConnectionInformationResponseMidwayDataArea [events=");
        builder.append(events);
        builder.append("]");
        return builder.toString();
    }

}
