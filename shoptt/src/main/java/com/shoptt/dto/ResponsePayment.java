package com.shoptt.dto;

public class ResponsePayment {
    private String url;
    private String orderId;
    private String requestId;

    public ResponsePayment(String url, String orderId, String requestId) {
        this.url = url;
        this.orderId = orderId;
        this.requestId = requestId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
