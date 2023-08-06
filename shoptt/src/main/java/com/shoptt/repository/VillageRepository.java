package com.shoptt.repository;

import com.shoptt.entity.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VillageRepository extends JpaRepository<Village,Long> {

    @Query(value = "select * from Village where town_id = ?1",nativeQuery = true)
    public List<Village> findByTownId(Long id);
}
