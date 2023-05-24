package com.cdweb.dao;

import java.util.List;

import com.cdweb.entity.Customer;

public interface ICustomerDAO extends IGeneralDAO<Customer, String> {
	List<Customer> findByRoles(boolean roles);

}
