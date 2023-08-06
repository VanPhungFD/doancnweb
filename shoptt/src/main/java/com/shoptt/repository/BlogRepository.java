package com.shoptt.repository;

import com.shoptt.entity.AddressUser;
import com.shoptt.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query(value = "select * from blog order by id desc", nativeQuery = true)
    public List<Blog> findAllDesc();
}
