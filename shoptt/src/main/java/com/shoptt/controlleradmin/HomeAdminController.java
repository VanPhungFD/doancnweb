package com.shoptt.controlleradmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeAdminController {

    @RequestMapping(value = {"/admin","/admin/trang-chu"}, method = RequestMethod.GET)
    public String homePage(){
        return "admin/index";
    }
}
