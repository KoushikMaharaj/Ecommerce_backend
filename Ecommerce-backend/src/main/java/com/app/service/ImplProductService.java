package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.customException.ResourceNotFoundException;
import com.app.dao.ProductRepository;
import com.app.dao.SubCategoryRepository;
import com.app.dto.ProductUpdateRequestDTO;
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

	@Override
	public Product getProductDetail(Integer id) {
		return prodRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}

	@Override
	public void updateProduct(int qty, int pid) {
		prodRepo.updateProduct(qty, pid);

	}

	@Override
	public List<Product> getBySubCategory(SubCategory subCtg) {
		return prodRepo.findBySubCtg(subCtg);
	}

	@Override
	public Product updateProduct(ProductUpdateRequestDTO prod) {
		Product product = prodRepo.findById(prod.getId()).orElseThrow(()->new ResourceNotFoundException("Product Not found"));
		product.setPrice(prod.getPrice());
		product.setNumberInStock(prod.getNumberInStock());
		return prodRepo.save(product);
	}

}
