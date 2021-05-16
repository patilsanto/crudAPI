package com.crudapp.topicApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;

	public List<Product> listAll() {
		return repo.findAll();
	}
	
	
	public void saveProduct(Product product) {
		repo.save(product);
	}
	
	
	public Product getProductById(long id) {
		return repo.findById(id).get();
	}
	
	public void deleteProductById(long id) {
		repo.deleteById(id);
	}
}
