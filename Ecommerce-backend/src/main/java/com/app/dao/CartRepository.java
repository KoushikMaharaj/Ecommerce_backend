package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Cart;
import com.app.pojos.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	Cart findByCustCart(User user);

}
