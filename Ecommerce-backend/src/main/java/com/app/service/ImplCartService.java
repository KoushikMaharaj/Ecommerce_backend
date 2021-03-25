package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.CartRepository;
import com.app.pojos.Cart;
import com.app.pojos.Product;
import com.app.pojos.User;

@Service
@Transactional
public class ImplCartService implements ICartService {
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private IProductService prodService;
	@Autowired
	private IUserService userService;

	@Override
	public Cart addProductToCart(int cid,int pid) {
		User user = userService.getUserById(cid);
		System.out.println(user);
		Product prod = prodService.getProductDetail(pid);
		System.out.println(prod);
		Cart crt = user.getCart();
		if (crt == null) {
			Cart cart = new Cart();
			user.addCart(cart);
			cart.addProduct(prod);
			return cartRepo.save(cart);
		}
		crt.addProduct(prod);
		return cartRepo.save(crt);
	}

	@Override
	public Cart showCart(User user) {
		return cartRepo.findByCustCart(user);
	}

	@Override
	public Cart getById(int id) {
		return cartRepo.findById(id).orElseThrow(() -> new RuntimeException("Cart Not Found"));
	}

	@Override
	public void deleteCart(Cart c) {
		System.out.println("in delete " + c);
		cartRepo.delete(c);
	}

}
