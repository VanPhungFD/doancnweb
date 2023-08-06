package com.shoptt.rest;


import com.shoptt.dto.SaleDto;
import com.shoptt.entity.Category;
import com.shoptt.entity.Product;
import com.shoptt.entity.Sale;
import com.shoptt.repository.CategoryRepository;
import com.shoptt.repository.ProductRepository;
import com.shoptt.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class SaleRest {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/admin/addSale")
    public Sale saveByProduct(@RequestBody Sale sale){
        Sale result = saleRepository.save(sale);
        return result;
    }

    @PostMapping("/admin/addSaleByCategory")
    public void saveByCategory(@RequestBody SaleDto saleDto){
        for(Category c : saleDto.getCategory()){
            Category category = categoryRepository.findById(c.getId()).get();
            List<Product> productList = productRepository.findByCategoryId(category.getId());

            for(Product p : productList){
                Sale sale = new Sale();
                sale.setFromDate(saleDto.getSale().getFromDate());
                sale.setName(saleDto.getSale().getName());
                sale.setPercent(saleDto.getSale().getPercent());
                sale.setProduct(p);
                sale.setToDate(saleDto.getSale().getToDate());
                saleRepository.save(sale);
            }
        }
    }

    @GetMapping("/admin/sales")
    public List<Sale> getAll(){
        return saleRepository.findAllDesc();
    }

}
