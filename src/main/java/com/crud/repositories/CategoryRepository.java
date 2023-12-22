package com.crud.repositories;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Page<Category> findAll(Pageable pageable);
	
	Optional<Category> findByCategoryname(String categoryname);
	
	Optional<Category> findByCategoryid(int categoryid);
	
	Optional<Category> deleteByCategoryid(int categoryid);
	
}
