package com.cdweb.dao.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cdweb.dao.IOrderDAO;
import com.cdweb.dao.IOrderDetailDAO;
import com.cdweb.entity.Customer;
import com.cdweb.entity.Order;
import com.cdweb.entity.OrderDetail;
import com.cdweb.entity.Product;
import com.cdweb.service.impl.CartService;
@Repository
public class OrderDAO extends GeneraDAO<Order, Integer> implements IOrderDAO {

	@Autowired
	private IOrderDetailDAO dao;

	@Override
	public void create(Order o, CartService cart) {
		this.create(o);
		Collection<Product> items = cart.getItemsCart();
		items.forEach(p -> {
			OrderDetail detail = new OrderDetail();
			detail.setOrder(o);
			detail.setProduct(p);
			detail.setUnitPrice(p.getUnitPrice());
			detail.setDiscount(p.getDiscount());
			detail.setQuantity(p.getQuantity());
			dao.create(detail);
		});

	}

	@Override
	public List<Order> findByUser(Customer user) {
		String sql = "FROM Order o WHERE o.customer.id=?0 ORDER BY o.orderDate DESC";
		return this.getResultList(sql, user.getId());
	}

}
