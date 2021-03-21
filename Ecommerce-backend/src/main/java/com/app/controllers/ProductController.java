package com.app.controllers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.pojos.Product;
import com.app.service.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

	@Autowired
	private IProductService prodService;

	

	public ProductController() {
		System.out.println("in ctor of " + getClass().getName());
	}

	@PostMapping
	public ResponseEntity<?> addProduct(@RequestParam MultipartFile prodImage, @RequestParam String product) {
		System.out.println("product " + product + " image " + prodImage.getOriginalFilename());

		try {
			Product prod = new ObjectMapper().readValue(product, Product.class);
			System.out.println("without image " + prod);
			byte[] image = prodImage.getBytes();
			System.out.println(image);
			prod.setProdImage(image);
			return new ResponseEntity<>(prodService.addProduct(prod), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping
	public List<Product> getAllProducts() {
		return prodService.getAllProducts();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProductDetail(@PathVariable Integer id) {
		System.out.println("in getProductDetail " + id);
		try {
			return new ResponseEntity<>(prodService.getProductDetail(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
