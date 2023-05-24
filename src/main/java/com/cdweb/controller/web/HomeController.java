package com.cdweb.controller.web;

import java.util.Collections;
import java.util.List;

import com.cdweb.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdweb.entity.Product;
import com.cdweb.service.ICategoryService;
import com.cdweb.service.IProductService;

@Controller
public class HomeController {
    @Autowired
    private ICategoryService serviceCategory;

    @Autowired
    private IProductService prodService;

    @RequestMapping("/home/index")
    public String index() {
        return "home/index";
    }

    @RequestMapping("/home/about")
    public String about() {
        return "home/about";
    }

    @RequestMapping("/home/contact")
    public String contact() {
        return "home/contact";
    }

    @RequestMapping("/home/feedback")
    public String feedback() {
        return "home/feedback";
    }

    @RequestMapping("/home/faq")
    public String faq() {
        return "home/faq";
    }

    @RequestMapping("/home/aside/category")
    public String category(Model model) {
        model.addAttribute("cates", serviceCategory.findAll());
        return "non-layout/layout/_aside-category";
    }

    @RequestMapping("/home/random")
    public String random(Model model) {
        List<Product> list = prodService.findAll();
        Collections.shuffle(list);
        model.addAttribute("list", list.subList(0, 6));
        return "non-layout/product/list";
    }

    @RequestMapping("/home/slideshow")
    public String slideshow(Model model) {
        List<Category> list = serviceCategory.getRamDomByCategory();
        list.forEach(x -> System.out.println(x.getNameVN()));
        model.addAttribute("slideshow_cates", list.subList(0,3));
        return "non-layout/home/_slideshow";
    }
}
