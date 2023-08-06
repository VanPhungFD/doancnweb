package com.shoptt.controlleradmin;

import com.shoptt.entity.Category;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Validated
public class CategoryAdminController {

    @RequestMapping(value = {"/admin/category"}, method = RequestMethod.GET)
    public String homePage(){
        return "admin/category";
    }

    @RequestMapping(value = {"/admin/addcategory"}, method = RequestMethod.GET)
    public String addCategory(@Validated Category category){
        return "admin/addcategory";
    }
}
