package com.holm.ecommerce.service;

import com.holm.ecommerce.pojo.Product;
import com.holm.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    // Create
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Read
    public Product getProduct(Long id) {
        return productRepository.findById(id).get();
    }

    public List<Product> getInventory() {
        return (List<Product>) productRepository.findAll();
    }

    // Update
    public Product updateProduct(long id, Product product) {
        Product existingProduct = productRepository.findById(id).get();
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        return productRepository.save(existingProduct);
    }

    // Delete
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
