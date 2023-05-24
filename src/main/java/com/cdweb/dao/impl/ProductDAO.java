package com.cdweb.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdweb.dao.IProductDAO;
import com.cdweb.entity.Product;

@Repository
public class ProductDAO extends GeneraDAO<Product, Integer> implements IProductDAO {

	@Override
	public List<Product> findByKeywords(String keywords) {
		String hql = "FROM Product p WHERE p.name LIKE ?0 OR p.category.name " + "LIKE ?1 OR p.category.nameVN LIKE ?2";
		String keyWords = "%" + keywords + "%";
		return getResultListParam(hql, keyWords, keyWords, keyWords);	
	}

	@Override
	public List<Product> findByCategoryId(Integer id) {
		String hql = "FROM Product p WHERE p.category.id = ?0";
		return getResultList(hql, id);
	}

	@Override
	public List<Product> findItemByHot(String key) {
		String hql = "FROM Product";
		switch (key) {
		case "hangmoi":
			hql = "From Product p where year(current_date()) - year(p.productDate) < 10 ";
			break;
		// sắp xếp chi tiết đơn hàng theo số lượng bán giảm dần
		case "banchay":
			hql = "From Product p order by size (p.orderDetails) DESC";
			break;
		case "xemnhieu":
			hql = "FROM Product p ORDER BY p.viewCount DESC";
			break;
		case "giamgia":
			hql = "From Product p Where p.discount > 0 ORDER BY p.discount DESC";
			break;

		default:
			break;
		}
		return getResultPageOrPagram(hql, 0, 12);
	}


	@Override
	public List<Product> findByIdsInCookie(String id) {
		String hql = "From Product p Where p.id IN ("+ id +")"; 		
		return getResultList(hql);
	}

}
