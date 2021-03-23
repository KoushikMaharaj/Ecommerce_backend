package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.Product;
import com.app.pojos.SubCategory;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Modifying
	@Query(value = "update Product p set p.numberInStock=p.numberInStock-:qty where p.id=:pid")
	void updateProduct(int qty, int pid);

	List<Product> findBySubCtg(SubCategory subCtg);
}
