package com.springboot.shoppingapp.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.shoppingapp.productservice.models.Product;

public interface ProductRepository extends MongoRepository<Product, String> 
{
   
}
