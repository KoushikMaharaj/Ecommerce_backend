package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Order;
import com.app.pojos.User;
import com.app.service.IOrderService;
import com.app.service.IUserService;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IOrderService orderService;

	public OrderController() {
		System.out.println("in ctor " + getClass().getName());
	}

	@PostMapping("/{cid}/{pid}")
	public ResponseEntity<?> placeOrder(@PathVariable int cid, @PathVariable int pid, Order order) {

		try {
			return new ResponseEntity<>(orderService.placeOrder(cid, pid, order), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/cartorder/{cartId}")
	public ResponseEntity<?> placeCartOrder(@PathVariable int cartId, Order order) {

		try {
			return new ResponseEntity<>(orderService.placeOrderFromCart(cartId, order), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/{cid}")
	public ResponseEntity<?> getOrdersByCustomer(@PathVariable int cid) {
		User customer = userService.getUserById(cid);
		try {
			return new ResponseEntity<>(orderService.getOrdersByCustomer(customer), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping
	public ResponseEntity<?> getAllOrders() {
		try {
			return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
