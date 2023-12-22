package com.crud.models;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product {

	@Id   // Used to Indicate Primary Key
	@GeneratedValue(strategy = GenerationType.AUTO) // Used for Auto_Increment
	private int productid;
	
	
	@Column(unique = true,nullable = false) // Indicate Column will not have duplicate name and will not be empty
	@Size(min = 5, message = "Product Name Should be minimum 5 characters.")
	private String productname;
	
	
	@Column(nullable = false)
	@Min(value = 50,message = "Product Should be minimum 50 rs.")
	private double productprice;
	
	
	@Column(nullable = false)
	private String quantityperunit;
	
	
	@Column(nullable = false)
	private int productinstock;

	@CreatedDate
	private Instant createdAt;
	
	@LastModifiedDate
	private Instant updatedAt;

	@ManyToOne    // Indicate many product have one category
	@JoinColumn(name="category_id",referencedColumnName = "categoryid")
	private Category category;

	



	
	
	
	
	
	
}
