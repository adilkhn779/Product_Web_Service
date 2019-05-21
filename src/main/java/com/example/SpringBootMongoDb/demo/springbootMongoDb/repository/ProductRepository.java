package com.example.SpringBootMongoDb.demo.springbootMongoDb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringBootMongoDb.demo.springbootMongoDb.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
 public Product findByName(String name);
 public List<Product> findByPrice(int price);
}
