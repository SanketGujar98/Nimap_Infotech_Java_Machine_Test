package com.crud.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.crud.models.Category;
import com.crud.models.Product;
import com.crud.repositories.CategoryRepository;
import com.crud.repositories.ProductRepository;
import com.crud.responsewrappers.AllResponseWrapper;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	AllResponseWrapper arw=new AllResponseWrapper();
	
	public ResponseEntity<?> getAllProducts(int page,int pagesize)
	{
		Pageable pageData=PageRequest.of(page -1, pagesize);
		Page<Product> data=productRepository.findAll(pageData);
		if(!data.hasNext())
		{
		    throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no Data in database please add some");	
		}
		else
		{
			arw.setData(data);
			arw.setMessage("Product Founded");
			return new ResponseEntity<>(arw,HttpStatus.FOUND);
		}
		
	}
	
	public ResponseEntity<?> addProduct(Product product)
	{
		String category_found=product.getCategory().getCategoryname();
		Category category=categoryRepository.findByCategoryname(category_found).
				          orElseThrow(()->
				          {
                     		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no Category with name "+category_found);
		                  }
				          );
		
		product.setCategory(category);
	    Product newproduct=productRepository.save(product);
		if(newproduct==null)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"There is some probe in server.");
		}
		else
		{
			arw.setData(newproduct);
			arw.setMessage("Product Added Sucessfully.");
			return new ResponseEntity<>(arw,HttpStatus.CREATED);
		}
		
	}
	
	
	public ResponseEntity<?> getProductById(int productid)
	{
		Product productFound=productRepository.findByProductid(productid).
				             orElseThrow(()->
				             {
			                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product With Id "+productid+" not Exist.");
		                     }
				             );
		arw.setData(productFound);
		arw.setMessage("Product With Id "+productid+" Exist.");
		return new ResponseEntity<>(arw,HttpStatus.FOUND);
	}
	
	
	
	public ResponseEntity<?> updateById(Product product,int productid)
	{
		Product productFound=productRepository.findByProductid(productid).
				             orElseThrow(()->{
			                                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product With Id "+productid+" not Exist.");
		                                     });
		product.setProductid(productFound.getProductid());	
		String category_found=product.getCategory().getCategoryname();
		Category category=categoryRepository.findByCategoryname(category_found).
				          orElseThrow(()->{
			                                 throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is not category with name "+category_found);
		                                   });
		product.setCategory(category);
		product.setCreatedAt(productFound.getCreatedAt());
		category.setCreatedAt(category.getCreatedAt());
		Product updatedProduct=productRepository.save(product);
		arw.setData(updatedProduct);
		arw.setMessage("Product With Id "+productid+" updated sucessfully");
		return new ResponseEntity<>(arw,HttpStatus.CREATED);
		
	}
	
	
	public ResponseEntity<?> deleteById(int productid)
	{
		getProductById(productid);
		productRepository.deleteByProductid(productid);
		arw.setData(" ");
		arw.setMessage("Product With Id "+productid+" Deleted");
		return new ResponseEntity<>(arw,HttpStatus.OK);
	}
	
	
	public ResponseEntity<?> rangePrice(int low,int high)
	{
		Product data=productRepository.findByProductpriceBetween(low, high).
				               orElseThrow(()->{
	                                             throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product Price Between "+low+ " and "+high+" not found");
                                               });
		arw.setData(data);
		arw.setMessage(" Product Founded");
		return new ResponseEntity<>(arw,HttpStatus.FOUND);
		
	}
	
}
