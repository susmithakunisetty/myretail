package com.target.casestudy.myretail.controller;

import com.target.casestudy.myretail.entity.Product;
import com.target.casestudy.myretail.exception.ProductMisMatchException;
import com.target.casestudy.myretail.exception.ProductNotFoundException;
import com.target.casestudy.myretail.repository.ProductRepository;
import com.target.casestudy.myretail.response.ProductPriceUpdateResponse;
import com.target.casestudy.myretail.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value="/products")
@RestController
public class ProductController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductDetails(@PathVariable("id") String productId) {
            logger.info("Inside getproductDetails  " + productId);

            Product product = null;

            try{

                product = productService.getProductById(productId);
            }catch (Exception e) {
                logger.debug("Product Not Found Exception  " + e);
                throw new ProductNotFoundException();
            }

            return new ResponseEntity<Product>(product, HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<ProductPriceUpdateResponse> updateProductPrice(@RequestBody Product product,
                                                                         @PathVariable("id") String productId){
        if (!product.getProductId().equalsIgnoreCase(productId)) {
            throw new ProductMisMatchException();
        }
        try {
            productService.updateProductPrice(product);
        } catch (Exception e) {
            throw new ProductMisMatchException();
        }

        return new ResponseEntity<ProductPriceUpdateResponse>(
                new ProductPriceUpdateResponse(HttpStatus.OK.value(), "Product Price Update for "+productId+ " : SUCCESS"), HttpStatus.OK);

    }



}