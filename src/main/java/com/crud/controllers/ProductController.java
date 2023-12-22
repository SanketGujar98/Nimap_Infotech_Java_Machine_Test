package com.crud.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.crud.models.Product;
import com.crud.services.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	ProductService productService;
	
	
    @GetMapping(" ")
	public ResponseEntity<?> getAllProducts(@RequestParam (defaultValue = "1" ) int page,@RequestParam(defaultValue ="5" )int pagesize)
	{
		return productService.getAllProducts(page, pagesize);
	}
	
    
    @PostMapping(" ")
	public ResponseEntity<?> addProduct(@RequestBody @Valid Product product)
	{
		return productService.addProduct(product);
	}
	
	@GetMapping("/{productid}")
	public ResponseEntity<?> getProductById(@PathVariable int productid)
	{
		return productService.getProductById(productid);
	}
	
	@PutMapping("/{productid}")
	public ResponseEntity<?> updateById(@RequestBody @Valid Product product,@PathVariable int productid)
	{
		return productService.updateById(product, productid);
		
	}
	
	@DeleteMapping("/{productid}")
	public ResponseEntity<?> deleteById(@PathVariable int productid)
	{
		return productService.deleteById(productid);
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> rangePrice(@RequestParam int low,@RequestParam int high)
	{
		return productService.rangePrice(low, high);
	}
	
	
}
