package com.shoptt.controlleradmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class  ProductController {

    @RequestMapping(value = {"/admin/sanpham"}, method = RequestMethod.GET)
    public String homePage(){
        return "admin/sanpham";
    }

    @RequestMapping(value = {"/admin/addproduct"}, method = RequestMethod.GET)
    public String addProduct(){
        return "admin/addproduct";
    }
}
