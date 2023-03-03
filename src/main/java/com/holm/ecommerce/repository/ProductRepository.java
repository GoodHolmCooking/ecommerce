package com.holm.ecommerce.repository;

import com.holm.ecommerce.pojo.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends CrudRepository<Product, Long> { }
