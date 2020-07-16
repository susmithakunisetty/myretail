package com.target.casestudy.myretail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "ProductId in the url and productId in the body doesnt match.")
public class ProductMisMatchException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ProductMisMatchException() {

    }
}
