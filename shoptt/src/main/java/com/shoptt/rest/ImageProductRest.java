package com.shoptt.rest;

import com.shoptt.entity.ImageProduct;
import com.shoptt.entity.Product;
import com.shoptt.repository.ImageProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ImageProductRest {

    @Autowired
    private ImageProductRepository imageProductRepository;

    @PostMapping("/admin/addImageproduct")
    public ImageProduct save(@RequestBody ImageProduct imageProduct){
        ImageProduct result = imageProductRepository.save(imageProduct);
        return result;
    }

    @DeleteMapping("/admin/deleteImageProduct")
    public void deleteProduct(@RequestParam("id") Long id){
        imageProductRepository.deleteById(id);
    }

    @GetMapping("/public/imageProByPro")
    public List<ImageProduct> findByProductId(@RequestParam("id") Long id){
        return imageProductRepository.findByProductId(id);
    }
}
