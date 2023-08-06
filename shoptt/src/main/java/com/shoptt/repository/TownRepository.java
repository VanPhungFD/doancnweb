package com.shoptt.repository;

import com.shoptt.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {

    @Query(value = "select * from Town where province_id = ?1",nativeQuery = true)
    public List<Town> findByProvinceId(Long id);
}