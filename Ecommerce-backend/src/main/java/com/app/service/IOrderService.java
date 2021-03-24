package com.app.service;

import java.util.List;

import com.app.pojos.Order;
import com.app.pojos.User;

public interface IOrderService {
	Order placeOrder (int cid,int pid,Order order);
	
	Order placeOrderFromCart(int cartId,Order order);
	
	List<Order> getOrdersByCustomer(User customer);
	
	List<Order> getAllOrders();
	
	
}
