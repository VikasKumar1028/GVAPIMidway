package com.gv.midway.service;

import org.apache.camel.Exchange;

public interface ISchedulerService {

    public void saveDeviceConnectionHistory(Exchange exchange);

    public void saveDeviceUsageHistory(Exchange exchange);

}
