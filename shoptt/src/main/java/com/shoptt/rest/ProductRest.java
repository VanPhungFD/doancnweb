package com.shoptt.rest;

import com.shoptt.dto.SearchDto;
import com.shoptt.entity.Category;
import com.shoptt.entity.Product;
import com.shoptt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductRest {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/allproduct")
    public List<Product> findAll(){
        return productRepository.findAllDesc();
    }

    @GetMapping("/productByID")
    public Product findById(@RequestParam("id") Long id){
        return productRepository.findById(id).get();
    }

    @DeleteMapping("/admin/deleteProduct")
    public void deleteProduct(@RequestParam("id") Long id){
        Product p = productRepository.findById(id).get();
        p.setDeleted(1);
        productRepository.save(p);
    }

    @PostMapping("/admin/addOrUpdateproduct")
    public Product save(@RequestBody Product product){
        if(product.getId() == null){
            product.setCreatedDate(new Date(System.currentTimeMillis()));
            product.setDeleted(0);
        }
        else{
            Product p = productRepository.findById(product.getId()).get();
            product.setDeleted(p.getDeleted());
            product.setCreatedDate(p.getCreatedDate());
            if(product.getImageBanner() == null){
                product.setImageBanner(p.getImageBanner());
            }
        }
        Product result = productRepository.save(product);
        return result;
    }

    @GetMapping("/public/productNewIndexPage")
    public Page<Product> getProductIndexPage(Pageable pageable){
        Page products = productRepository.findNewIndexPage(pageable);
        return products;
    }

    @GetMapping("/public/getSpBanChay")
    public Page<Product> getSpBanChay(Pageable pageable){
        Page products = productRepository.getSpBanChay(pageable);
        return products;
    }

    @GetMapping("/public/sanPhamCungDanhMuc")
    public List<Product> getSanPhamCungDanhMuc(@RequestParam("id") Long productId){
        Product product = productRepository.findById(productId).get();
        return productRepository.getSanPhamCungDanhMuc(product.getCategory().getId(), productId);
    }

    @PostMapping("/public/searchFull")
    public Page<Product> searchFull(@RequestBody SearchDto searchDto, Pageable pageable){
        Page products = productRepository.searchByPrice(searchDto.getPriceSmall(), searchDto.getPriceLarge(), searchDto.getCategories(), pageable);
        return products;
    }

    @GetMapping("/public/search")
    public Page<Product> search(@RequestParam("s") String param, Pageable pageable){
        Page products = productRepository.search("%"+param+"%", pageable);
        return products;
    }
}
