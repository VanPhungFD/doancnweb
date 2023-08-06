package com.shoptt.repository;

import com.shoptt.entity.AddressUser;
import com.shoptt.entity.Product;
import com.shoptt.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "select * from sale order by id desc ", nativeQuery = true)
    public List<Sale> findAllDesc();
}
