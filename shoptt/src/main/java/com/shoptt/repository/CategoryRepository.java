package com.shoptt.repository;

import com.shoptt.entity.AddressUser;
import com.shoptt.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {


}
