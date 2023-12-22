package com.crud.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.crud.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	Page<Product> findAll(Pageable pageable);
	
	Optional<Product> findByProductid(int productid);
	
	Optional<Product> deleteByProductid(int productid);
	
	Optional<Product> findByProductpriceBetween(double low,double high);

}
