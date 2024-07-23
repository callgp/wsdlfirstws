package com.bt.ws.soap;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.feature.Features;

import com.bharath.ws.trainings.CreateOrdersRequest;
import com.bharath.ws.trainings.CreateOrdersResponse;
import com.bharath.ws.trainings.CustomerOrdersPortType;
import com.bharath.ws.trainings.GetOrdersRequest;
import com.bharath.ws.trainings.GetOrdersResponse;
import com.bharath.ws.trainings.Order;
import com.bharath.ws.trainings.Product;

//@Features(features="org.apache.cxf.feature.LoggingFeatures")
public class CustomerOrdersWsImpl implements CustomerOrdersPortType {

	Map<BigInteger,List<Order>> customerOrders=new HashMap<>();
	int currentId;
	
	public CustomerOrdersWsImpl() {
		init();
	}
	public void init() {
		List<Order> orders=new ArrayList<>();
		Order order=new Order();
		order.setId(BigInteger.valueOf(1));
		
		
		Product product = new Product();
		product.setId("1");
		product.setDescription("Iphone");
		product.setQuantity(BigInteger.valueOf(3));
		order.getProduct().add(product);
		orders.add(order);
		
		customerOrders.put(BigInteger.valueOf(++currentId), orders);
		
	}
	@Override
	public GetOrdersResponse getOrders(GetOrdersRequest request) {
		BigInteger customerId = request.getCustomerId();
		List<Order> orders = customerOrders.get(customerId);
		
		GetOrdersResponse response=new GetOrdersResponse();
		response.getOrder().addAll(orders);
		return response;
		
		
		
		
	}

	@Override
	public CreateOrdersResponse createOrders(CreateOrdersRequest request) {
		System.out.println("request"+request);
		// TODO Auto-generated method stub
		BigInteger customerId = request.getCustomerId();
		System.out.println("customerId"+customerId);
		Order order = request.getOrder();
		System.out.println("order"+order);
		List<Order> orders = customerOrders.get(customerId);
		System.out.println("orders"+orders);
		orders.add(order);
		CreateOrdersResponse resp=new CreateOrdersResponse();
		resp.setResult(true);
		return resp;
		
	}

}
