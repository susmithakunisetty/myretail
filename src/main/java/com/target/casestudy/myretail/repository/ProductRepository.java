package com.target.casestudy.myretail.repository;

import com.target.casestudy.myretail.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
