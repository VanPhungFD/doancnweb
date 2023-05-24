package com.cdweb.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminHomeController {
	//@Autowired
	//private ElasticSearch elasticService;

	@RequestMapping("/admin/home/index")
	public String index() {
		return "admin/home/index";
	}

	@RequestMapping("/admin/home/document")
	public String createDocument() {
		//elasticService.documentElasticSearch();
		return "redirect:/admin/home/index";
	}

}
