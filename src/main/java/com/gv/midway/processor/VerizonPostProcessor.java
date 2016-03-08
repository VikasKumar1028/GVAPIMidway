package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class VerizonPostProcessor implements Processor {
	


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	public void process(Exchange exchange) throws Exception {
	
		Message inMessage = exchange.getIn();
		inMessage.setBody(exchange.getUnitOfWork().getOriginalInMessage().toString());
		
		System.out.println("in");
		
		inMessage.setHeader(Exchange.HTTP_PATH, "/devices/actions/list");
		//inMessage.setBody(body);
		
		
		System.out.println("------------ERROR------------"+ exchange.getUnitOfWork().getOriginalInMessage().toString());
	//	inMessage.setBody("TO CHECK THE VALUE");

		/*
		 * String json = "{\"vinay1\":\"sumani1\"}"; Message
		 * in=exchange.getIn(); in.setHeader(exchange.ACCEPT_CONTENT_TYPE,
		 * "application/json"); in.setBody(json);
		 */

		// List params = new ArrayList();
		// params.add("Vinay");
		// exchange.getOut().setBody("<?xml version=\"1.0\"?><user>  <userName>Vinay</userName>  <password>sumani</password></user>");

	}
}
