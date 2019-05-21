package com.example.SpringBootMongoDb.demo.springbootMongoDb.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBootMongoDb.demo.springbootMongoDb.model.Product;
import com.example.SpringBootMongoDb.demo.springbootMongoDb.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
	private ProductRepository productRepository;
    
    //create Operation
    public Product create(String name, String summary, int price) {
    	return productRepository.save(new Product(name,summary,price));
    }
    
    //retrieve operation
    public List<Product> getAll(){
    	return productRepository.findAll();
    }
    public Product getByName( String name) {
    	return productRepository.findByName(name);
    }
    //update operation
    public Product update(String name, String summary, int price) {
    	Product p = productRepository.findByName(name);
    	p.setSummary(summary);
    	p.setPrice(price);
    	return productRepository.save(p);
    }
    //delete operation
    public void deleteAll() {
    	productRepository.deleteAll();
    }
    public void delete(String name) {
    	Product p =productRepository.findByName(name);
    	productRepository.delete(p);
    }
}
