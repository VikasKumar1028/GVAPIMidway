package com.gv.midway.controller;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ScheduledJobServiceImpl implements IScheduledJobService {

	private static final Logger logger = LoggerFactory
			.getLogger(ScheduledJobServiceImpl.class);

	@EndpointInject(uri = "")
	ProducerTemplate producer;

	
}