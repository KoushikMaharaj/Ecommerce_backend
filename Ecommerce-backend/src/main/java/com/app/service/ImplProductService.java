package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ProductRepository;
import com.app.dao.SubCategoryRepository;
import com.app.pojos.Product;
import com.app.pojos.SubCategory;

@Service
@Transactional
public class ImplProductService implements IProductService {
	@Autowired
	private ProductRepository prodRepo;

	@Autowired
	private SubCategoryRepository subCtgRepo;

	@Override
	public Product addProduct(Product prod) {
		SubCategory subCtg = subCtgRepo.findBySubCtgName(prod.getSubCtg().getSubCtgName());
		subCtg.addProduct(prod);
		return prodRepo.save(prod);
	}

	@Override
	public List<Product> getAllProducts() {
		return prodRepo.findAll();
	}

}
