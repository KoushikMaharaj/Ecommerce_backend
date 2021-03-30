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

import com.app.pojos.Cart;
import com.app.pojos.User;
import com.app.service.ICartService;
import com.app.service.IUserService;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

	@Autowired
	private IUserService userService;

	@Autowired
	private ICartService cartService;

	public CartController() {
		System.out.println("in ctor of " + getClass().getName());
	}

	@PostMapping("/addtocart/{cid}/{pid}")
	public Cart addProductToCart(@PathVariable int cid, @PathVariable int pid) {
		return cartService.addProductToCart(cid, pid);
	}

	@GetMapping("/{cid}")
	public ResponseEntity<?> showCart(@PathVariable int cid) {
		User user = userService.getUserById(cid);
		try {
			return new ResponseEntity<>(cartService.showCart(user), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>("Cart Not Found", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/removeproduct/{cartId}/{pid}")
	public ResponseEntity<?> removeProductFromCart(@PathVariable int cartId, @PathVariable int pid) {
		try {
			return new ResponseEntity<>(cartService.removeProductFromCart(cartId, pid), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>("Cart Not Found", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
