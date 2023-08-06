package com.shoptt.repository;

import com.shoptt.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {

    @Query(value = "select p.* from Province p",nativeQuery = true)
    public List<Province> findAlls();
}
