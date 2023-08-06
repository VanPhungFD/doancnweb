package com.shoptt.repository;

import com.shoptt.entity.AddressUser;
import com.shoptt.entity.DetailInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetailInvoiceRepository extends JpaRepository<DetailInvoice, Long> {

    @Query("select d from DetailInvoice d where d.invoice.id = ?1")
    public List<DetailInvoice> findByInvoiceId(Long id);
}
