package com.target.casestudy.myretail.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.casestudy.myretail.entity.Product;
import com.target.casestudy.myretail.remoteclient.ProductNameClient;
import com.target.casestudy.myretail.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductNameClient productNameClient;

    @Autowired
    private ProductRepository productRepository;

    public ProductService() {

    }

    public Product getProductById(String productId) throws JsonParseException, JsonMappingException, IOException {


        Product product = productRepository.findOne(productId) ;
        String title = this.getTitle(productId);
        product.setTitle(title);
        productRepository.save(product);

        logger.info("New updated Title: "+ productRepository.findOne(productId).getTitle());
        return product;
    }


    public String getTitle(String productId)throws JsonParseException, JsonMappingException, IOException{
        logger.info("INSIDE GET TITLE: "+ productId);

        System.out.println(productNameClient);
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<String> response = productNameClient.getProductDetailsById(productId);
        System.out.println(response.getStatusCode().value());
        Map<String, Map> objectMap = objectMapper.readValue(response.getBody(), Map.class);
        Map<String,Map> productMap = objectMap.get("product");
        Map<String,Map> itemMap = productMap.get("item");
        Map<String,String> prodDescrMap = itemMap.get(("product_description"));
        String title =  prodDescrMap.get("title");

        return title ;
    }


    public void updateProductPrice(Product product){
        productRepository.save(product);
    }

}
