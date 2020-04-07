package com.example;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MySpringProcessor implements Processor {
	
	@Override
	public void process(Exchange exchange) throws Exception {
		List<List<String>> data = (List<List<String>>) exchange.getIn().getBody();
		for (List<String> line : data) {
		    System.out.println(String.format("%s has an IQ of %s and is currently %s", line.get(0), line.get(1), line.get(2)));
		}
		String input = (String) exchange.getIn().getBody();
        System.out.println("Input to be persisted : " + input);
        String insertQuery = "INSERT INTO messages values ( '1','" + input+"')";
        System.out.println("Insert Query is : " + insertQuery);
        exchange.getIn().setBody(insertQuery);
	}
}