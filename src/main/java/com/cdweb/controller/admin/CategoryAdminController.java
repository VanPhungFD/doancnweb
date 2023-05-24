package com.cdweb.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cdweb.entity.Category;
import com.cdweb.service.ICategoryService;

@Controller
@RequestMapping("/admin/category/")
public class CategoryAdminController {
	@Autowired
	private ICategoryService categoryService;

	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("category", new Category());
		model.addAttribute("list", categoryService.findAll());
		return "admin/category/index";
	}

	@RequestMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("category", categoryService.findById(id));
		model.addAttribute("list", categoryService.findAll());
		return "admin/category/index";
	}

	@RequestMapping("create")
	public String create(Model model, RedirectAttributes params, @Validated Category form,
			BindingResult errors) {
		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng không bỏ trống các trường bên trên");
			model.addAttribute("list", categoryService.findAll());
			return "admin/category/index";
		}
		categoryService.add(form);
		params.addAttribute("message", "Thêm mới thành công");
		return "redirect:/admin/category/index"; // redirect lại để mất dữ liệu trên form
	}

	@RequestMapping("update")
	public String update(Model model, RedirectAttributes params, @Validated  Category form,
			BindingResult errors) {
		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng không bỏ trống bên dưới");
			model.addAttribute("list", categoryService.findAll());
			return "admin/category/index";
		}
		categoryService.update(form);
		params.addAttribute("message", "Cập nhật thành công");
		return "redirect:/admin/category/edit/" + form.getId(); // redirect giữ lại dữ liệu
	}

	@RequestMapping("delete/{id}")
	public String delete(Model model, RedirectAttributes params, @PathVariable("id") Integer id) {
		categoryService.delete(id);
		params.addAttribute("message", "Xóa thành công");
		return "redirect:/admin/category/index";
	}
}
