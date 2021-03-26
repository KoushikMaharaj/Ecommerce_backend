package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Order;
import com.app.pojos.OrderDetail;
import com.app.pojos.User;
import com.app.service.IOrderService;
import com.app.service.IUserService;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private IUserService userService;

	@Autowired
	private IOrderService orderService;

	public OrderController() {
		System.out.println("in ctor " + getClass().getName());
	}

	@GetMapping
	public ResponseEntity<?> getAllOrders() {
		try {
			return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/{cid}/{pid}/{qty}")
	public ResponseEntity<?> placeOrder(@PathVariable int cid, @PathVariable int pid, @PathVariable int qty,
			Order order) {
		Order placeOrder = orderService.placeOrder(cid, pid, qty, order);
		StringBuilder text = new StringBuilder();
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(placeOrder.getCustomer().getUserEmail());
		mail.setSubject("Orders");
		placeOrder.getDetails().forEach((detail) -> {
			text.append("Product name: " + detail.getProduct().getProdName());
			text.append(", Quantity: " + detail.getQty());
			text.append(", Total price : " + detail.getTotalPrice() + "\n");
		});
		String message = text.toString();
		mail.setText(message);
		mailSender.send(mail);
		try {
			return new ResponseEntity<>(placeOrder, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/cartorder/{cartId}")
	public ResponseEntity<?> placeCartOrder(@PathVariable int cartId, Order order) {
		Order placeOrder=orderService.placeOrderFromCart(cartId, order);
		StringBuilder text = new StringBuilder();
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(placeOrder.getCustomer().getUserEmail());
		mail.setSubject("Orders");
		placeOrder.getDetails().forEach((detail) -> {
			text.append("Product name: " + detail.getProduct().getProdName());
			text.append(", Quantity: " + detail.getQty());
			text.append(", Total price : " + detail.getTotalPrice() + "\n");
		});
		String message = text.toString();
		mail.setText(message);
		mailSender.send(mail);
		try {
			return new ResponseEntity<>(placeOrder, HttpStatus.OK);
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

	@GetMapping("/orderbyid/{orderId}")
	public ResponseEntity<?> getOrderByOrderId(@PathVariable int orderId) {
		System.out.println("in getOrderByOrderId " + orderId);
		try {
			return new ResponseEntity<>(orderService.getOrderByOrderId(orderId), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
