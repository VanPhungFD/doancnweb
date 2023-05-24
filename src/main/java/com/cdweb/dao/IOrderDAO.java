package com.cdweb.dao;

import java.util.List;

import com.cdweb.entity.Customer;
import com.cdweb.entity.Order;
import com.cdweb.service.impl.CartService;

public interface IOrderDAO extends IGeneralDAO<Order, Integer> {

	void create(Order o, CartService cart);
	List<Order> findByUser(Customer user);

}
