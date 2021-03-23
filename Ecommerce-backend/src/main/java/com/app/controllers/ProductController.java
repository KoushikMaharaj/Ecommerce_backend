package com.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.message.ResponseFile;
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

	@GetMapping("/images")
	public ResponseEntity<List<ResponseFile>> getListFiles() {
		System.out.println("in list files");
		List<ResponseFile> files = prodService.getAllProducts().stream().map(p -> {

			String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath() // Prepares a URL from the
																							// host, port, scheme, and
					// context path of the given HttpServletRequest.eg : http://localhost:8080/
					.path("/product/images/")// apends the resource name eg : http://localhost:8080/files
					.path(p.getId().toString()) // appends file id(resource id) http://localhost:8080/files/1
					.toUriString();
			// System.out.println("url " + fileDownloadUrl);

			return new ResponseFile(p.getImageFileName(), fileDownloadUrl);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}

	@GetMapping("/images/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable Integer id) {
		System.out.println("in get file");
		Product p = prodService.getProductDetail(id);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + p.getImageFileName() + "\"")
				.body(p.getProdImage());
	}

	@GetMapping("/category")
	public ResponseEntity<?> getProductsByCategory(@RequestParam String ctgName) {
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

	@GetMapping("/subcategory")
	public ResponseEntity<?> getProductsBySubCategory(@RequestParam String subCtgName) {
		SubCategory subCtg = subCtgService.getSubCategoryByName(subCtgName);
		/*
		 * List<Product> products = new ArrayList<>(); products.addAll();
		 */
		try {
			return new ResponseEntity<>(prodService.getBySubCategory(subCtg), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
