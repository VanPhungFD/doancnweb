package com.cdweb.dao.impl;

import org.springframework.stereotype.Repository;

import com.cdweb.dao.ICategoryDAO;
import com.cdweb.entity.Category;


@Repository
public class CategoryDAO extends GeneraDAO<Category, Integer> implements ICategoryDAO {

}
