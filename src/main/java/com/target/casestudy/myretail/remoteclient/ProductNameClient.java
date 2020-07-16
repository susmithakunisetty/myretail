package com.target.casestudy.myretail.remoteclient;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "product-service-client", url = "https://redsky.target.com/v1/pdp/tcin/")
public interface ProductNameClient {

    @RequestMapping(value = "{productId}?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics")
    public ResponseEntity<String> getProductDetailsById(@PathVariable("productId") String productId);
}
