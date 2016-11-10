package com.gv.midway.pojo.activateDevice.request;

import com.gv.midway.pojo.MidWayDeviceId;

public class ActivateDeviceId extends MidWayDeviceId {

    public ActivateDeviceId() {
        //This needs to remain for Jackson to work properly
    }

    public ActivateDeviceId(String id, String kind) {
        super(id, kind);
    }

    @Override
    public String toString() {
        return "ActivateDeviceId [id=" + getId() + ", kind=" + getKind() + "]";
    }
}
