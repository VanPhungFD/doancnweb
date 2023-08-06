package com.shoptt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlogUserController {

    @RequestMapping(value = {"/blog"}, method = RequestMethod.GET)
    public String homePage(){
        return "user/blog";
    }

    @RequestMapping(value = {"/blogdetail"}, method = RequestMethod.GET)
    public String blogdetail(){
        return "user/blogdetail";
    }
}
