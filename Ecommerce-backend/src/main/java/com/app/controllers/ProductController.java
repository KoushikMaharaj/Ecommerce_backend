package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Product;
import com.app.service.IProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {
	@Autowired
	private IProductService prodService;

	public ProductController() {
		System.out.println("in ctor of " + getClass().getName());
	}
//my cmt
	@PostMapping
	public ResponseEntity<?> addProduct(@RequestBody Product product) {
		System.out.println("in addProduct " + product);
		try {
			return new ResponseEntity<>(prodService.addProduct(product), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping
	public List<Product> getAllProducts(){
		return prodService.getAllProducts();
	}
	
}
