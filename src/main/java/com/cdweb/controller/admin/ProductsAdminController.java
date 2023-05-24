package com.cdweb.controller.admin;

import java.io.File;

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

import com.cdweb.entity.Product;
import com.cdweb.service.IHttpService;
import com.cdweb.service.IProductService;

@Controller
@RequestMapping("/admin/product/")
public class ProductsAdminController {
	@Autowired
	private IProductService productService;

//	@Autowired
//	private IElasticSearch elastic;

	@Autowired
	private IHttpService http;

	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("list", productService.findAll());
		return "admin/product/index";
	}

	@RequestMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("product", productService.findById(id));
		model.addAttribute("list", productService.findAll());
		return "admin/product/index";
	}

	@RequestMapping("create")
	public String create(Model model, RedirectAttributes params, @Validated Product form, BindingResult errors,
			@RequestParam("image_file") MultipartFile file) {
		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng không bỏ trống các trường bên trên");
			return "admin/product/index";
		}
		File image = http.saveProductPhoto(file);
		if (image != null) {
			form.setImage(image.getName());
		}
		// thêm xuống db
		productService.add(form);
		
		// thêm vào elastic server
		//elastic.documentElasticSearch(form);
		params.addAttribute("message", "Thêm mới thành công");
		return "redirect:/admin/product/index"; // redirect lại để mất dữ liệu trên form
	}

	@RequestMapping("update")
	public String update(Model model, RedirectAttributes params, @Validated Product form, BindingResult errors,
			@RequestParam("image_file") MultipartFile file) {
		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng không bỏ trống bên dưới");
			// model.addAttribute("users", productService.findByRoles(false));
			// model.addAttribute("admins", productService.findByRoles(true));
			return "admin/product/index";
		}
		
	
		File photo = http.saveProductPhoto(file);
		if (photo != null) {
			form.setImage(photo.getName());
		}
		productService.update(form);
		//update lên elasticSearch
	//	elastic.updateElastic(form);
		params.addAttribute("message", "Cập nhật thành công");
		return "redirect:/admin/product/edit/" + form.getId(); // redirect giữ lại dữ liệu
	}

	@RequestMapping("delete/{id}")
	public String delete(Model model, RedirectAttributes params, @PathVariable("id") Integer id) {
		productService.delete(id);
		//xóa trên server ElasticSearch
		//elastic.deleteElastic(id);
		params.addAttribute("message", "Xóa thành công");
		return "redirect:/admin/product/index";
	}
}
