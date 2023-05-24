package com.cdweb.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdweb.entity.Category;
import com.cdweb.service.ICategoryService;
import com.cdweb.service.IReportService;

@Controller
public class InventoryAndReportControlller {

	@Autowired
	private IReportService reportService;
	
	@Autowired ICategoryService categoryService;

	@RequestMapping("/admin/inventory/category")
	public String inventoryByCategory(Model model) {
		model.addAttribute("data", reportService.inventory());
		return "admin/report/inventory-by-category";
	}

	@RequestMapping("/admin/revenue/category")
	public String reportByCategory(Model model) {
		model.addAttribute("rpcates", reportService.revenueByCategory());
		return "admin/report/revenue-by-category";
	}

	@RequestMapping("/admin/revenue/customer")
	public String reportByCustomer(Model model) {
		model.addAttribute("rpcustomer", reportService.revenueByCustomer());
		return "admin/report/revenue-by-customer";
	}

	@RequestMapping("/admin/revenue/product")
	public String reportByProduct(Model model,
			@RequestParam(name = "category_id",required = false) Integer category_id) {
		List<Category> list = categoryService.findAll();
		model.addAttribute("categories", list);
		if (category_id == null) {
			category_id = list.get(0).getId();
		}
		model.addAttribute("rpProduct", reportService.revenueByProduct(category_id));
		return "admin/report/revenue-by-product";
	}
	
	@RequestMapping("/admin/revenue/month")
	public String revenueByMonth(Model model) {
		model.addAttribute("rpMonth", reportService.revenueByMonth());
		return "admin/report/revenue-by-month";
	}
	@RequestMapping("/admin/revenue/year")
	public String revenueByYear(Model model) {
		model.addAttribute("rpYear", reportService.revenueByYear());
		return "admin/report/revenue-by-year";
	}

}
