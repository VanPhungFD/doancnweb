package com.cdweb.dao;

import java.util.List;

public interface IGeneralDAO<L, K> {
	L create(L entity); // thêm một thực thể

	void update(L entity);

	void delete(@SuppressWarnings("unchecked") K... ids);

	L findById(K id);

    List<L> findAll();
    
    /**
	 * Truy vấn danh sách giá trị từ CSDL
	 * @param <E> kiểu giá trị cần truy vấn
	 * @param hql là câu lệnh HQL có thể chứa tham số
	 * @param args là mảng giá trị cung cấp cho các tham số trong hql
	 */
	<E> List<E> getResultList(String hql,Object...mangParam);
	
	
	/**
	 * Truy vấn danh sách giá trị từ CSDL
	 * @param <E> kiểu giá trị cần truy vấn
	 * @param hql là câu lệnh HQL có thể chứa tham số
	 * @param page là trang số cần truy vấn
	 * @param size là số giá trị cần truy vấn
	 * @param magnParam là mảng giá trị cung cấp cho các tham số trong hql ( có thể 1,2,3...)
	 * Ví dụ
	 * Truy vấn tất cả các sản phẩm
	 * String hql = "FROM Product";
	 * List&lt;Product&gt; list = getResultListPage(hql, 0, 0);
	 * 
	 */
	<E> List<E> getResultPageOrPagram(String hql,int page,int size,Object...mangParam);
	
	<E> List<E> getResultListParam(String hql,Object...mangParam);
    

}
