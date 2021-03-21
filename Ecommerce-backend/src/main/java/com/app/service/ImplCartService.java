package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.CartRepository;
import com.app.pojos.Cart;
import com.app.pojos.User;

@Service
@Transactional
public class ImplCartService implements ICartService {
	@Autowired
	private CartRepository cartRepo;

	@Override
	public Cart addProductToCart(Cart cart) {
		return cartRepo.save(cart);
	}

	@Override
	public Cart showCart(User user) {
		return cartRepo.findByCustCart(user);
	}

}