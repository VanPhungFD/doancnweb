package com.shoptt.controlleradmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserAdminController {

    @RequestMapping(value = {"/admin/user"}, method = RequestMethod.GET)
    public String homePage(){
        return "admin/user";
    }
}
