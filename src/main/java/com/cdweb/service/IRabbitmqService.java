package com.cdweb.service;

import com.cdweb.entity.Order;
import com.cdweb.service.impl.CartService;

public interface IRabbitmqService {
	public void converToSendRabbit(Order o, CartService cart);

}
