package com.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

	public ProductController() {
		System.out.println("in ctor of " + getClass().getName());
	}

}
