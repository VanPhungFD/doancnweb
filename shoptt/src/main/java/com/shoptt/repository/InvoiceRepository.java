package com.shoptt.repository;

import com.shoptt.entity.AddressUser;
import com.shoptt.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;

import java.sql.Date;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {


    @Query("select i from Invoice i where i.addressUser.user.id = ?1")
    public List<Invoice> findByUserId(Long userId);

    @Query(value = "select * from invoice", nativeQuery = true)
    public List<Invoice> findAlls();

    @Query(value = "select i from Invoice i where i.trangThai.id = ?1 ")
    public List<Invoice> findByTT(Long trangThaiId);

    @Query(value = "select i from Invoice i where i.trangThai.id = ?1 and i.createdDate >= ?2 and i.createdDate <= ?3")
    public List<Invoice> findByTTAndNgay(Long trangThaiId, Date d1, Date d2);

    @Query(value = "select i from Invoice i where i.createdDate >= ?1 and i.createdDate <= ?2")
    public List<Invoice> findByNgay(Date d1, Date d2);

    @Query(value = "select sum(i.total_amount) from Invoice i where Month(i.created_date) = ?1 and Year(i.created_date) = ?2 and (i.pay_type = 1 or i.trangthai_id = 4)", nativeQuery = true)
    Double calStatiticsOnMonth(Integer month, Integer year);

    @Query("select count(i.id) from Invoice i")
    Double tongDonHang();

    @Query(value = "select * from invoice i where MONTH(i.created_date) = ?1 and YEAR(i.created_date) = ?2 and i.trangthai_id = 1", nativeQuery = true)
    public List<Invoice> findByThisMonth(int month, int year);
}
