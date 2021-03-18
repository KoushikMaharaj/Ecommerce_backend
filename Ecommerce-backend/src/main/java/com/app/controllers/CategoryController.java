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

import com.app.pojos.Category;
import com.app.service.ICategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

	@Autowired
	private ICategoryService ctgService;

	public CategoryController() {
		System.out.println("in ctor of " + getClass().getName());
	}

	@PostMapping
	public ResponseEntity<?> addCategory(@RequestBody Category category) {
		System.out.println("in addCategory " + category);
		try {
			return new ResponseEntity<>(ctgService.addCategory(category), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public List<Category> getAllCategories() {
		return ctgService.getAllCategories();
	}
}
