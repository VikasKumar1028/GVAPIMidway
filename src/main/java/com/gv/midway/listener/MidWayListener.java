package com.gv.midway.listener;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

import com.gv.midway.controller.AdaptationLayerServiceImpl;
import com.gv.midway.utility.BatchExecutor;

public class MidWayListener extends ContextLoaderListener {

	private static final Logger logger = LoggerFactory
			.getLogger(AdaptationLayerServiceImpl.class);

	private BatchExecutor batchExecutor;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("context initizaliized...");
		super.contextInitialized(event);
		batchExecutor = BatchExecutor.getBatchExecutor();
	}

	/**
	 * Close the root web application context.
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("context destroyed...");
		super.contextDestroyed(event);
		batchExecutor.shutDown();
	}
}
