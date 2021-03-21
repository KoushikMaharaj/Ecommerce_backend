package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Cart;
import com.app.pojos.Product;
import com.app.pojos.User;
import com.app.service.ICartService;
import com.app.service.IProductService;
import com.app.service.IUserService;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

	@Autowired
	private IUserService userService;

	@Autowired
	private ICartService cartService;

	@Autowired
	private IProductService prodService;

	public CartController() {
		System.out.println("in ctor of " + getClass().getName());
	}

	@PostMapping("/addtocart")
	public Cart addProductToCart(@RequestParam int id, @RequestParam int pid) {
		User user = userService.getUserById(id);
		System.out.println(user);
		Product prod = prodService.getProductDetail(pid);
		System.out.println(prod);
		Cart crt = user.getCart();
		if (crt == null) {
			Cart cart = new Cart();
			user.addCart(cart);
			cart.addProduct(prod);
			return cartService.addProductToCart(cart);
		}
		crt.addProduct(prod);
		return cartService.addProductToCart(crt);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> showCart(@PathVariable int id) {
		User user = userService.getUserById(id);
		System.out.println("showCart " + user.getCart().getId());
		try {
			return new ResponseEntity<>(cartService.showCart(user), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
