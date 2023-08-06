package com.shoptt.rest;

import com.shoptt.entity.Blog;
import com.shoptt.entity.Category;
import com.shoptt.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class BlogRest {

    @Autowired
    private BlogRepository blogRepository;

    @PostMapping("/admin/addOrUpdateBlog")
    public void save(@RequestBody Blog blog){
        if(blog.getId() == null){
            blog.setCreatedDate(new Date(System.currentTimeMillis()));
        }
        else{
            Blog b = blogRepository.findById(blog.getId()).get();
            blog.setCreatedDate(b.getCreatedDate());
            if(blog.getImageBanner() == null){
                blog.setImageBanner(b.getImageBanner());
            }
        }
        blogRepository.save(blog);
    }

    @GetMapping("/public/allBlog")
    public List<Blog> findAll(){
        return blogRepository.findAllDesc();
    }

    @GetMapping("/public/blogById")
    public Blog findById(@RequestParam("id") Long id){
        return blogRepository.findById(id).get();
    }

    @DeleteMapping("/admin/deleteBlog")
    public void deleteCategory(@RequestParam("id") Long id){
        blogRepository.deleteById(id);
    }

}
