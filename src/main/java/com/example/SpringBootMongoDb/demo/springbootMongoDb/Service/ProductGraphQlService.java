package com.example.SpringBootMongoDb.demo.springbootMongoDb.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBootMongoDb.demo.springbootMongoDb.model.Product;
import com.example.SpringBootMongoDb.demo.springbootMongoDb.repository.ProductRepository;

import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

//@GraphQLApi is to impose this service for GraphQL
@Service
@GraphQLApi
public class ProductGraphQlService {

	@Autowired
	private ProductRepository productRepository;

	@GraphQLQuery
	public List<Product> findAllProduct() {
		return productRepository.findAll();
	}

	@GraphQLMutation(name = "createProduct")
	public Product saveProduct(String id, String name, String summary, int price) {
		return productRepository.save(new Product(name, summary, price));
	}

	@GraphQLMutation(name = "updateProduct")
	public Product updateCustomer(String name, String summary, int price) {
		Product product = findProductByName(name);
		product.setSummary(summary);
		product.setPrice(price);
		return productRepository.save(product);
	}

	@GraphQLQuery(name = "product")
	public Product findProductByName(String name) {
		Product p = productRepository.findByName(name);
		return p;
	}

	@GraphQLQuery(name = "product")
	public Product findOrderById(String id) {
		Optional<Product> p = productRepository.findById(id);
		if (!p.isPresent())
			throw new RuntimeException("product doesn't exist with given id " + id);
		return p.get();
	}

	@GraphQLMutation(name = "deleteProduct")
	public String deleteProduct(String name) {
		Product p = productRepository.findByName(name);
		productRepository.delete(p);
		return "Customer deleted Successfully";

	}

	@GraphQLMutation(name = "deleteProduct")
	public String deleteAllProduct() {
		productRepository.deleteAll();
		return "Deleted Successfully";
	}

}
