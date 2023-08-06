package com.shoptt.repository;

import com.shoptt.entity.AddressUser;
import com.shoptt.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select * from product where deleted = 0 order by id desc ", nativeQuery = true)
    public   List<Product> findAllDesc();

    @Query(value = "select * from product where deleted = 0 and category_id = ?1 order by id desc ", nativeQuery = true)
    public List<Product> findByCategoryId(Long categoryId);

    @Query(value = "select * from product where product.deleted = 0 order by product.id desc", nativeQuery = true)
    public Page<Product> findNewIndexPage(Pageable pageable);

    @Query(value = "select * from product where category_id =?1 and id != ?2 and deleted = 0 order by id desc limit 4 offset 0", nativeQuery = true)
    public List<Product> getSanPhamCungDanhMuc(Long cateId, Long proId);

    @Query(value = "select * from product where product.deleted = 0 and price >= ?1 and price <= ?2 and category_id = ?3 order by product.id desc", nativeQuery = true)
    public Page<Product> searchByPrice(Double price1, Double price2,Long cateId, Pageable pageable);

    @Query(value = "select p from Product p inner join p.category c where p.deleted = 0 and (p.name like ?1 or c.name like ?1) order by p.id desc ", nativeQuery = false)
    public Page<Product> search(String param, Pageable pageable);

    @Query("select sum(p.quantity) from Product p")
    Double tongSp();

    @Query(value = "select * from product where product.deleted = 0 order by (select sum(d.quantity) from detail_invoice d inner join product pr on pr.id = d.product_id where pr.id = product.id) desc", nativeQuery = true)
    public Page<Product> getSpBanChay(Pageable pageable);
}
