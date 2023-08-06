package com.shoptt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CartController {

    @RequestMapping(value = {"/cart"}, method = RequestMethod.GET)
    public String homePage(){
        return "user/cart";
    }

    @RequestMapping(value = {"/historyorder"}, method = RequestMethod.GET)
    public String historyorder(){
        return "user/historyorder";
    }

    @RequestMapping(value = {"/checkout"}, method = RequestMethod.GET)
    public String checkout(){
        return "user/checkout";
    }

    @RequestMapping(value = {"/listproduct"}, method = RequestMethod.GET)
    public String listproduct(){
        return "user/listproduct";
    }

    @RequestMapping(value = {"/taikhoan"}, method = RequestMethod.GET)
    public String taikhoan(){
        return "user/taikhoan";
    }

    @RequestMapping(value = {"/returnurl"}, method = RequestMethod.GET)
    public String returnurl(){
        return "user/returnurl";
    }
}
