package com.crud.models;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int categoryid;
	
	@Column(nullable = false)
	@Size(min=5,max=20,message="Category Name Should Be Between 5-100 character ")
	private String categoryname;
	
	@Column(nullable = false)
	@Size(min=5,max=100,message="Description Should Be Between 5-100 character ")
	private String description;
	
	@CreatedDate
	private Instant createdAt;
	
	@LastModifiedDate
	private Instant updatedAt;
	
	
	@OneToMany(mappedBy ="category" ) // used to indicate one category will have multiple products.
	@JsonIgnore // Used When we Fetch Data from Get Method It will not show particular Column name.
	private List<Product> product;

	
	


	
	
	
	
}
