package com.shoptt.rest;


import com.shoptt.entity.DetailInvoice;
import com.shoptt.entity.Invoice;
import com.shoptt.entity.Product;
import com.shoptt.entity.User;
import com.shoptt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DetailInvoiceRest {
    @Autowired
    private TrangThaiRepository trangThaiRepository;

    @Autowired
    private TrangThaiDonHangRepository trangThaiDonHangRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private DetailInvoiceRepository detailInvoiceRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/user/addDetailInvoice")
    public DetailInvoice save(@RequestBody DetailInvoice detailInvoice){
        Product product = productRepository.findById(detailInvoice.getProduct().getId()).get();
        product.setQuantity(product.getQuantity() - detailInvoice.getQuantity());
        productRepository.save(product);
        DetailInvoice result = detailInvoiceRepository.save(detailInvoice);
        return result;
    }

    @GetMapping("/user/detailinvoiceByInvoice")
    public List<DetailInvoice> findByUser(@RequestParam("id") Long id){
        return detailInvoiceRepository.findByInvoiceId(id);
    }

    @GetMapping("/admin/detailinvoiceByInvoiceAdmin")
    public List<DetailInvoice> findInvoice(@RequestParam("id") Long id){
        return detailInvoiceRepository.findByInvoiceId(id);
    }
}
