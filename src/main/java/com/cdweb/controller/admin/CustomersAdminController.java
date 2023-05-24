package com.cdweb.controller.admin;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cdweb.entity.Customer;
import com.cdweb.service.IAccountService;
import com.cdweb.service.IHttpService;

@Controller
@RequestMapping("/admin/customer/")
public class CustomersAdminController {
	@Autowired
	private IAccountService customerService;

	@Autowired
	private IHttpService http;
	
	@Autowired
	ServletContext context;

	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("customer", new Customer());
		model.addAttribute("users", customerService.findByRoles(false));
		model.addAttribute("admins", customerService.findByRoles(true));
		return "admin/customer/index";
	}

	@RequestMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") String id) {
		model.addAttribute("customer", customerService.findById(id));
		model.addAttribute("users", customerService.findByRoles(false));
		model.addAttribute("admins", customerService.findByRoles(true));
		return "admin/customer/index";
	}

	@RequestMapping("create")
	public String create(Model model, RedirectAttributes params, @Validated Customer form, BindingResult errors,
			@RequestParam("photo_file") MultipartFile file) {
		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng không bỏ trống các trường bên trên");
			model.addAttribute("users", customerService.findByRoles(false));
			model.addAttribute("admins", customerService.findByRoles(true));
			return "admin/customer/index";
		}
		File photo = http.saveCustomerPhoto(file);
		if (photo != null) {
			form.setPhoto(photo.getName());
		}
		customerService.add(form);
		params.addAttribute("message", "Thêm mới thành công");
		return "redirect:/admin/customer/index"; // redirect lại để mất dữ liệu trên form
	}

	@RequestMapping("update")
	public String update(Model model, RedirectAttributes params, @Validated Customer form, BindingResult errors,@RequestParam("photo_file") MultipartFile file) {
		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng không bỏ trống bên dưới");
			model.addAttribute("users", customerService.findByRoles(false));
			model.addAttribute("admins", customerService.findByRoles(true));
			return "admin/customer/index";
		}
		File photo = http.saveCustomerPhoto(file);
		if(photo != null) {
			form.setPhoto(photo.getName());
		}
		customerService.update(form);
		params.addAttribute("message", "Cập nhật thành công");
		return "redirect:/admin/customer/edit/" + form.getId(); // redirect giữ lại dữ liệu
	}

	@RequestMapping("delete/{id}")
	public String delete(Model model, RedirectAttributes params, @PathVariable("id") String id) {
		customerService.delete(id);
		params.addAttribute("message", "Xóa thành công");
		return "redirect:/admin/customer/index";
	}
}
