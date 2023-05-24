package com.cdweb.service;

import java.util.List;

import com.cdweb.entity.Product;

public interface IElasticSearch {
	void documentElasticSearch();

	List<Object> searchElastiProduct(String keywords);

	void documentElasticSearch(Product product);

	List<Object> searchElasticProductByCate(Integer id);
	
	void updateElastic(Product p);
	
	void deleteElastic(int id);

}
