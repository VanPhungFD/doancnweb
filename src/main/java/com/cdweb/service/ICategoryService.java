package com.cdweb.service;

import java.util.List;

import com.cdweb.entity.Category;

public interface ICategoryService extends IGeneralService<Category, Integer> {
	List<Category> getRamDomByCategory();
}
