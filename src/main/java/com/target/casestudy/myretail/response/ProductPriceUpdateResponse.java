package com.target.casestudy.myretail.response;

public class ProductPriceUpdateResponse {
    private int value;
    private String message;

    public ProductPriceUpdateResponse() {

    }

    public ProductPriceUpdateResponse(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
