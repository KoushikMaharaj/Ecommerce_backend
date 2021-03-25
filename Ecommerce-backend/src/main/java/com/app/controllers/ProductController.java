package com.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ProductUpdateRequestDTO;
import com.app.pojos.Product;
import com.app.pojos.SubCategory;
import com.app.service.IProductService;
import com.app.service.ISubCategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

	@Autowired
	private IProductService prodService;

	@Autowired
	private ISubCategoryService subCtgService;

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
			prod.setImageFileName(prodImage.getOriginalFilename());
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

	@GetMapping("/images/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable Integer id) {
		System.out.println("in get file " + id);
		Product p = prodService.getProductDetail(id);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + p.getImageFileName() + "\"")
				.body(p.getProdImage());
	}

	@GetMapping("/category/{ctgName}")
	public ResponseEntity<?> getProductsByCategory(@PathVariable String ctgName) {
		List<SubCategory> subcategories = subCtgService.getSubCategoryByCategory(ctgName);
		List<Product> products = new ArrayList<>();
		subcategories.forEach((subCtg) -> {
			products.addAll(prodService.getBySubCategory(subCtg));
		});
		try {
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/subcategory/{subCtgName}")
	public ResponseEntity<?> getProductsBySubCategory(@PathVariable String subCtgName) {
		SubCategory subCtg = subCtgService.getSubCategoryByName(subCtgName);
		try {
			return new ResponseEntity<>(prodService.getBySubCategory(subCtg), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateProduct(@RequestBody ProductUpdateRequestDTO prodDTO) {
		System.out.println("in updateProduct: " + prodDTO);

		try {
			return new ResponseEntity<>(prodService.updateProduct(prodDTO), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
