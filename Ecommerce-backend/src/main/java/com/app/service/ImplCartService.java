package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.customException.ResourceNotFoundException;
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
	public Cart addProductToCart(int cid, int pid) {
		User user = userService.getUserById(cid);

		Product prod = prodService.getProductDetail(pid);

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
		return cartRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart Not Found"));
	}

	@Override
	public void deleteCart(Cart c) {
		cartRepo.delete(c);
	}

	@Override
	public Cart removeProductFromCart(int cartId, int pid) {
		Product product = prodService.getProductDetail(pid);
		Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
		cart.getProducts().remove(product);
		return cartRepo.save(cart);
	}

}
