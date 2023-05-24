package com.cdweb.model;

import java.util.ArrayList;
import java.util.List;

import com.cdweb.entity.Customer;
import com.cdweb.entity.Order;
import com.cdweb.entity.OrderDetail;

public class OrderModel {
	private String address;
	private Double amount;
	private String description;
	private Integer status;
	private Integer phone;
	
	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	private Customer customer;
	List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();

	public String getAddress() {
		return address;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	

}
