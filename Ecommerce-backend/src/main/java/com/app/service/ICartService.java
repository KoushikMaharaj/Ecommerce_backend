package com.app.service;

import com.app.pojos.Cart;
import com.app.pojos.User;

public interface ICartService {
	Cart addProductToCart(int cid,int pid);

	Cart showCart(User user);

	Cart getById(int id);
	
	void deleteCart(Cart c);
	
	
}
