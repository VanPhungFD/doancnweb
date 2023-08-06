package com.shoptt.controlleradmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlogAdminController {

    @RequestMapping(value = {"/admin/blogadmin"}, method = RequestMethod.GET)
    public String homePage(){
        return "admin/blogadmin";
    }

    @RequestMapping(value = {"/admin/addblog"}, method = RequestMethod.GET)
    public String addCategory(){
        return "admin/addblog";
    }
}
