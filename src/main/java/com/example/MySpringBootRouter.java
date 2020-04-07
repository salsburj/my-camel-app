package com.example;

import javax.sql.DataSource;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class MySpringBootRouter extends RouteBuilder {
	
	@Bean
	private static DataSource myDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUsername("bioshare");
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setPassword("h&wa11Bio");
        ds.setUrl("jdbc:postgresql://web-btp-dev-03:5488/lefcourn_dataload_030920");
        return ds;
    }

    @Override
    public void configure() {
    	from("file:C:\\test_data?fileName=test.csv&noop=true")
    	.split(body().tokenize("/n")).streaming()
    	.unmarshal().csv()
        .process(new MySpringProcessor())
    	.to("jdbc:myDataSource");
    }
}
