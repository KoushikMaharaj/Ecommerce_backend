package com.app.service;

import java.util.List;

import com.app.pojos.Cart;
import com.app.pojos.Product;
import com.app.pojos.User;

public interface ICartService {
	Cart addProductToCart(Cart cart);

	Cart showCart(User user);

	Cart getById(int id);
	
	void deleteCart(Cart c);
}
