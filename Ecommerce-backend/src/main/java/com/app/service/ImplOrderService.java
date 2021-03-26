package com.app.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.OrderRepository;
import com.app.pojos.Cart;
import com.app.pojos.Order;
import com.app.pojos.OrderDetail;
import com.app.pojos.Product;
import com.app.pojos.User;

@Service
@Transactional
public class ImplOrderService implements IOrderService {
	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private IUserService userService;

	@Autowired
	private IProductService prodService;

	@Autowired
	private ICartService cartService;

	@Override
	public Order placeOrder(int cid, int pid, int qty, Order order) {
		System.out.println("in placeOrder cid: " + cid + " pid: " + pid + " qty: " + qty);
		User customer = userService.getUserById(cid);
		Product prod = prodService.getProductDetail(pid);
		System.out.println("product: " + prod.getProdName());
		OrderDetail detail = new OrderDetail(qty, prod.getPrice());
		detail.setProduct(prod);
		order.getDetails().add(detail);
		order.setOrderAddress(customer.getUserAddr());
		customer.linkOrders(order);
		prodService.updateProduct(qty, pid);
		System.out.println(order);
		return orderRepo.save(order);
	}

	@Override
	public List<Order> getOrdersByCustomer(User customer) {
		return orderRepo.findByCustomerOrderByIdDesc(customer);
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> allOrders = orderRepo.findAll();
		allOrders.sort((o1, o2) -> o2.getOrderDate().compareTo(o1.getOrderDate()));
		allOrders.sort((o1, o2) -> o2.getId().compareTo(o1.getId()));
		return allOrders;
	}

	@Override
	public Order placeOrderFromCart(int cartId, Order order) {
		System.out.println("in placeCartOrder cart id " + cartId + " order : " + order);
		Cart cart = cartService.getById(cartId);
		User customer = cart.getCustCart();
		List<Product> products = cart.getProducts();
		products.forEach((product) -> {
			OrderDetail detail = new OrderDetail(1, product.getPrice());
			detail.setProduct(product);
			order.getDetails().add(detail);
			prodService.updateProduct(1, product.getId());
		});
		order.setOrderAddress(customer.getUserAddr());
		customer.linkOrders(order);

		Iterator<Product> itr = products.iterator();
		while (itr.hasNext()) {
			itr.next();
			itr.remove();
		}
		cartService.deleteCart(cart);
		return orderRepo.save(order);
	}

	@Override
	public Order getOrderByOrderId(int orderId) {
		return orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
	}

}
