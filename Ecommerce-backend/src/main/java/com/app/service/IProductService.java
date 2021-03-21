package com.app.service;

import java.util.List;

import com.app.pojos.Product;

public interface IProductService {
	Product addProduct(Product prod);

	List<Product> getAllProducts();

	Product getProductDetail(Integer id);
}
