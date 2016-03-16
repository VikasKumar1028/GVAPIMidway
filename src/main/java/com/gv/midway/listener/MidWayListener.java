package com.gv.midway.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import com.gv.midway.utility.BatchExecutor;

public class MidWayListener extends ContextLoaderListener {
	
	
	private BatchExecutor batchExecutor;

	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("context initizaliized...");
		super.contextInitialized(event);
		batchExecutor=BatchExecutor.getBatchExecutor();
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
