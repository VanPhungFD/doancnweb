package com.cdweb.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cdweb.dao.IReportDAO;

@Transactional
@Repository
public class ReportDAO implements IReportDAO {
	@Autowired
	private SessionFactory sessionFactory;

	/* tổng hợp số liệu trong Product theo từng nhóm theo từng loại */
	@Override
	public List<Object[]> inventory() {
		String hql = "SELECT " + " p.category.nameVN AS category," + " sum(p.quantity) AS totalQuantity, "
				+ " sum(p.quantity*p.unitPrice) AS totalAmount, " + " min(p.unitPrice) AS minPrice, "
				+ " max(p.unitPrice) AS maxPrice, " + " avg(p.unitPrice) AS avgPrice" + " FROM Product p "
				+ " GROUP BY p.category.nameVN";
		return this.getReportData(hql);
	}

	/*
	 * - tổng hợp từ đơn hàng , - nhóm theo loại - tổng hợp só lượng bán
	 */
	@Override
	public List<Object[]> revenueByCategory() {
		String hql = "SELECT " + " d.product.category.nameVN AS category," + " sum(d.quantity) AS totalQuantity, "
				+ " sum(d.quantity*d.unitPrice*(1-d.discount)) AS revenue, " + " min(d.unitPrice) AS minPrice, "
				+ " max(d.unitPrice) AS maxPrice, " + " avg(d.unitPrice) AS avgPrice" + " FROM OrderDetail d "
				+ " GROUP BY d.product.category.nameVN" + " ORDER BY revenue DESC";
		return this.getReportData(hql);
	}

	@Override
	public List<Object[]> revenueByCustomer() {
		String hql = "SELECT " + " d.order.customer.id AS customer," + " sum(d.quantity) AS totalQuantity, "
				+ " sum(d.quantity*d.unitPrice*(1-d.discount)) AS revenue, " + " min(d.unitPrice) AS minPrice, "
				+ " max(d.unitPrice) AS maxPrice, " + " avg(d.unitPrice) AS avgPrice"
				+ " FROM OrderDetail d WHERE d.order.status = 0" + " GROUP BY d.order.customer.id";
		return this.getReportData(hql);
	}

	@Override
	public List<Object[]> revenueByYear() {
		String hql = "SELECT " + " year(d.order.orderDate) AS year," + " sum(d.quantity) AS totalQuantity, "
				+ " sum(d.quantity*d.unitPrice*(1-d.discount)) AS revenue, " + " min(d.unitPrice) AS minPrice, "
				+ " max(d.unitPrice) AS maxPrice, " + " avg(d.unitPrice) AS avgPrice" + " FROM OrderDetail d "
				+ " GROUP BY year(d.order.orderDate)" + " ORDER BY revenue DESC";
		return this.getReportData(hql);
	}

	@Override
	public List<Object[]> revenueByQuarter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> revenueByMonth() {
		String hql = "SELECT " + " month(d.order.orderDate) AS month," + " sum(d.quantity) AS totalQuantity, "
				+ " sum(d.quantity*d.unitPrice*(1-d.discount)) AS revenue, " + " min(d.unitPrice) AS minPrice, "
				+ " max(d.unitPrice) AS maxPrice, " + " avg(d.unitPrice) AS avgPrice" + " FROM OrderDetail d "
				+ " GROUP BY month(d.order.orderDate)" + " ORDER BY revenue DESC";
		return this.getReportData(hql);
	}

	@Override
	public List<Object[]> revenueByProduct(Integer categoryId) {
		String hql = "SELECT " + " d.product.name AS product," + " sum(d.quantity) AS totalQuantity, "
				+ " sum(d.quantity*d.unitPrice*(1-d.discount)) AS revenue, " + " min(d.unitPrice) AS minPrice, "
				+ " max(d.unitPrice) AS maxPrice, " + " avg(d.unitPrice) AS avgPrice,"
				+ " d.product.category.nameVN AS category" + " FROM OrderDetail d " + " WHERE d.product.category.id=?0"
				+ " GROUP BY d.product.category.nameVN, d.product.name" + " ORDER BY category ASC, revenue DESC";
		return this.getReportData(hql, categoryId);
	}

	private List<Object[]> getReportData(String hql, Object... args) {
		Session session = sessionFactory.getCurrentSession();
		TypedQuery<Object[]> query = session.createQuery(hql, Object[].class);
		for (int i = 0; i < args.length; i++) {
			query.setParameter(i, args[i]);
		}
		return query.getResultList();
	}

}
