package com.app.service;

import com.app.pojos.Cart;
import com.app.pojos.User;

public interface ICartService {
	Cart addProductToCart(Cart cart);
	
	Cart showCart(User user);
}
