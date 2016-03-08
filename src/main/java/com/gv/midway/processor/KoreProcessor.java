package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class KoreProcessor implements Processor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	public void process(Exchange exchange) throws Exception {
		/*
		 * System.out.println("Received Order: " +
		 * exchange.getIn().getBody(String.class));
		 */

		System.out.println(" Inside EmpProcessor ");

		//System.out.println("----exchange_Body-----------" + exchange.getIn().getBody());

		/*String userpass = new String(Base64.decode(exchange.getIn().getHeader(
				"Authorization", String.class)));
		String[] tokens = userpass.split(":");

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				tokens[0], tokens[1]);
		Subject subject = new Subject();
		subject.getPrincipals().add(authToken);
*/
		// wrap it in a Subject
		String json = "{\"sadhana\":\"test1234\"}";
		// String deviceNumber="11111222223333344444";
		//Message in = exchange.getIn();
		//in.setHeader(exchange.HTTP_METHOD, "POST");
		//in.setHeader(exchange.ACCEPT_CONTENT_TYPE, "application/json");
		//in.setHeader(exchange.CONTENT_TYPE, "application/json");
		//in.setHeader(exchange.AUTHENTICATION,"BasicZ3JhbnR2aWN0b3JhcGk6akx1Y1dMQ0JxakhQ");
		
		
        Message message = exchange.getIn();
       // message .setHeader("VZ-M2M-Token", "1d1f8e7a-c8bb-4f3c-a924-cf612b562425");
     
        message.setHeader(Exchange.CONTENT_TYPE,"application/json");
        message.setHeader(Exchange.ACCEPT_CONTENT_TYPE,"application/json");
        message.setHeader(Exchange.HTTP_METHOD, "POST");
        message.setHeader("Authorization", "Basic Z3JhbnR2aWN0b3JhcGk6akx1Y1dMQ0JxakhQ");
        message.setHeader(Exchange.HTTP_PATH, "/json/queryServiceTypeCodes");


		//in.setBody(json);

		System.out.println("----exchange_Body-----------" + message.toString());

	}
}
