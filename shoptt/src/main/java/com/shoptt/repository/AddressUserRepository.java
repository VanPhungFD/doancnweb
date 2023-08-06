package com.shoptt.repository;

import com.shoptt.entity.AddressUser;
import com.shoptt.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressUserRepository extends JpaRepository<AddressUser, Long> {

    @Query(value = "select * from address_user where user_id = ?1",nativeQuery = true)
    public List<AddressUser> findByUserId(Long userId);
}
