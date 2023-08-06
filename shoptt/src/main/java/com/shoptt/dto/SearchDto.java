package com.shoptt.dto;

import com.shoptt.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class SearchDto {

    private Long categories;

    private Double priceSmall;

    private Double priceLarge;

    public Long getCategories() {
        return categories;
    }

    public void setCategories(Long categories) {
        this.categories = categories;
    }

    public Double getPriceSmall() {
        return priceSmall;
    }

    public void setPriceSmall(Double priceSmall) {
        this.priceSmall = priceSmall;
    }

    public Double getPriceLarge() {
        return priceLarge;
    }

    public void setPriceLarge(Double priceLarge) {
        this.priceLarge = priceLarge;
    }
}
