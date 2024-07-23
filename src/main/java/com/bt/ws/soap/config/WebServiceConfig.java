package com.bt.ws.soap.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bt.ws.soap.CustomerOrdersWsImpl;
//import com.bt.ws.soap.HelloWs;

@Configuration

public class WebServiceConfig {

	@Bean(name = Bus.DEFAULT_BUS_ID)

	public SpringBus cxf() {

		return new SpringBus();

	}

	@Bean

	public Endpoint helloWorld() {

		CustomerOrdersWsImpl implementor = new CustomerOrdersWsImpl();

		EndpointImpl endpoint = new EndpointImpl(cxf(), implementor);

		endpoint.publish("/customerordersservice");

		return endpoint;

	}

}