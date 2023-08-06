package com.shoptt.dto;

public class PaymentDto {
    private Long amount;
    private String content;
    private String returnUrl;
    private String notifyUrl;

    public PaymentDto() {

    }

    public PaymentDto(Long amount, String content, String returnUrl, String notifyUrl) {
        this.amount = amount;
        this.content = content;
        this.returnUrl = returnUrl;
        this.notifyUrl = notifyUrl;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}

