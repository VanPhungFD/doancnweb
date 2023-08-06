package com.shoptt.controlleradmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DonHangAdminController {

    @RequestMapping(value = {"/admin/donhang"}, method = RequestMethod.GET)
    public String homePage(){
        return "admin/donhang";
    }
}
