package com.cdweb.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cdweb.entity.Order;
import com.cdweb.service.IOrderSevice;


@Controller
@RequestMapping("/admin/order/")
public class OrdersAdminController {
	@Autowired
	private IOrderSevice orderService;

	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("order", new Order());
		model.addAttribute("list", orderService.findAll());
		return "admin/order/index";
	}

	@RequestMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("order", orderService.findById(id));
		model.addAttribute("list", orderService.findAll());
		return "admin/order/index";
	}

	@RequestMapping("create")
	public String create(Model model, RedirectAttributes params, @Validated Order form,
			BindingResult errors) {
		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng không bỏ trống các trường bên trên");
			model.addAttribute("list", orderService.findAll());
			return "admin/order/index";
		}
		orderService.add(form);
		params.addAttribute("message", "Thêm mới thành công");
		return "redirect:/admin/order/index"; // redirect lại để mất dữ liệu trên form
	}

	@RequestMapping("update")
	public String update(Model model, RedirectAttributes params, @Validated  Order form,
			BindingResult errors) {
		if (errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng không bỏ trống bên dưới");
			model.addAttribute("list", orderService.findAll());
			return "admin/order/index";
		}
		orderService.update(form);
		params.addAttribute("message", "Cập nhật thành công");
		return "redirect:/admin/order/edit/" + form.getId(); // redirect giữ lại dữ liệu
	}

	@RequestMapping("delete/{id}")
	public String delete(Model model, RedirectAttributes params, @PathVariable("id") Integer id) {
		orderService.delete(id);
		params.addAttribute("message", "Xóa thành công");
		return "redirect:/admin/order/index";
	}
}
