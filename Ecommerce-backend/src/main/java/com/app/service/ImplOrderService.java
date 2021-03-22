package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.OrderRepository;
import com.app.pojos.Order;

@Service
@Transactional
public class ImplOrderService implements IOrderService {
	@Autowired
	private OrderRepository orderRepo;

	@Override
	public Order placeOrder(Order order) {

		return orderRepo.save(order);
	}

}
