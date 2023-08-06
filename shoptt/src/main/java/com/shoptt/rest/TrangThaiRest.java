package com.shoptt.rest;

import com.shoptt.entity.Invoice;
import com.shoptt.entity.TrangThai;
import com.shoptt.entity.User;
import com.shoptt.repository.TrangThaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TrangThaiRest {

    @Autowired
    private TrangThaiRepository trangThaiRepository;

    @GetMapping("/public/trangthaiall")
    public List<TrangThai> findByUser(){
        return trangThaiRepository.findAll();
    }
}
