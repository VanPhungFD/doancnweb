package com.shoptt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DetailProductController {

    @RequestMapping(value = {"/detail"}, method = RequestMethod.GET)
    public String homePage(){
        return "user/detail";
    }
}
