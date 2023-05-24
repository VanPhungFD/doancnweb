package com.cdweb.service;

import java.util.List;
import java.util.Map;

import com.cdweb.entity.Order;
import com.cdweb.entity.Product;
import com.cdweb.service.impl.CartService;

public interface IOrderSevice extends IGeneralService<Order, Integer> {
	Order createOrder();
	void addOrderAndOrderDetail(Order o, CartService cart); // thêm mới đơn hàng vào db
	List<Order> getAllOrderByUser();
	Order findById(Integer id);
	Map<Integer, Product> getPurchasedItems();

}
