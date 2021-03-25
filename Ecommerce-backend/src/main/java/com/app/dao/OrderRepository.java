package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Order;
import com.app.pojos.User;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	List<Order> findByCustomerOrderByIdDesc(User customer);
}
