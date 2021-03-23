package com.app.service;

import java.util.List;

import com.app.pojos.Product;
import com.app.pojos.SubCategory;

public interface IProductService {
	Product addProduct(Product prod);

	List<Product> getAllProducts();

	Product getProductDetail(Integer id);

	void updateProduct(int qty, int pid);
	
	List<Product> getBySubCategory(SubCategory subCtg);
}
