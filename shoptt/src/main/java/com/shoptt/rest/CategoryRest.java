package com.shoptt.rest;

import com.shoptt.entity.Category;
import com.shoptt.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CategoryRest {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/admin/addOrUpdateCategory")
    public void save(@RequestBody Category category){
        categoryRepository.save(category);
    }

    @GetMapping("/public/allcategory")
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    @GetMapping("/public/categoryById")
    public Category findById(@RequestParam("id") Long id){
        return categoryRepository.findById(id).get();
    }

    @DeleteMapping("/admin/deleteCategory")
    public void deleteCategory(@RequestParam("id") Long id){
        categoryRepository.deleteById(id);
    }


}
