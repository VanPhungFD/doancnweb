package com.cdweb.dao.impl;

import org.springframework.stereotype.Service;

import com.cdweb.dao.IOrderDetailDAO;
import com.cdweb.entity.OrderDetail;
@Service
public class OrderDetailDAO extends GeneraDAO<OrderDetail, Integer> implements IOrderDetailDAO{

}
