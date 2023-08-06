package com.shoptt.dto;

import com.shoptt.entity.Category;
import com.shoptt.entity.Sale;

import java.util.List;

public class SaleDto {

    private List<Category> category;

    private Sale sale;

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
