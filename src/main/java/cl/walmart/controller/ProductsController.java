package cl.walmart.controller;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.walmart.domain.Product;
import cl.walmart.repository.ProductRepository;

@RestController()
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping(produces = "application/json" )
	public Page<Product> list(
			@RequestParam(required = false) String searchValue,
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "20") int size) {
		
		//set 20 items max
		if( size > 20)
			size = 20;
		
		try {
			Long id = Long.parseLong(searchValue);
			Optional<Product> p =  productRepository.findById(id);
			if(p.isPresent())
				return new PageImpl<Product>(Arrays.asList(p.get()),PageRequest.of(0, 1),1);
			
		}catch(NumberFormatException e) {
			
		}
			
		return productRepository.findAll(PageRequest.of(page, size));

	}

}
