package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Cart;
import com.app.pojos.Order;
import com.app.pojos.OrderDetail;
import com.app.pojos.Product;
import com.app.pojos.User;
import com.app.service.ICartService;
import com.app.service.IOrderService;
import com.app.service.IProductService;
import com.app.service.IUserService;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IProductService prodService;

	@Autowired
	private IOrderService orderService;

	@Autowired
	private ICartService cartService;

	public OrderController() {
		System.out.println("in ctor " + getClass().getName());
	}

	@PostMapping("/{cid}/{pid}")
	public ResponseEntity<?> placeOrder(@PathVariable int cid, @PathVariable int pid, Order order) {
		System.out.println("in placeOrder cid: " + cid + " pid: " + pid);
		User customer = userService.getUserById(cid);
		Product prod = prodService.getProductDetail(pid);
		System.out.println("product: " + prod.getProdName());
		// Order order = new Order();
		OrderDetail detail = new OrderDetail(1, prod.getPrice());
		detail.setProduct(prod);
		order.getDetails().add(detail);
		order.setOrderAddress(customer.getUserAddr());
		customer.linkOrders(order);
		prodService.updateProduct(1, pid);
		// prodService.addProduct(prod);
		System.out.println(order);
		try {
			return new ResponseEntity<>(orderService.placeOrder(order), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/cartorder/{id}")
	public ResponseEntity<?> placeCartOrder(@PathVariable int id, Order order) {
		System.out.println("in placeCartOrder cart id " + id);
		Cart cart = cartService.getById(id);
		System.out.println(cart.getProducts());
		User customer = cart.getCustCart();
		List<Product> products = cart.getProducts();
		products.forEach((product) -> {
			OrderDetail detail = new OrderDetail(1, product.getPrice());
			detail.setProduct(product);
			order.getDetails().add(detail);
			prodService.updateProduct(1, product.getId());
		});
		order.setOrderAddress(customer.getUserAddr());
		customer.linkOrders(order);
		try {
			return new ResponseEntity<>(orderService.placeOrder(order), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
