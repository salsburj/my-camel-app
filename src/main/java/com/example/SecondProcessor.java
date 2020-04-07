package com.example;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class SecondProcessor implements Processor {
	
	@Override
	public void process(Exchange exchange) throws Exception {
	    System.out.println(exchange.getProperty("simple.timer"));
	}
}