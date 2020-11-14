package cl.walmart.controller;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.walmart.domain.Product;
import cl.walmart.repository.ProductRepository;

@RestController()
@CrossOrigin
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
		
		System.out.println("searchValue "+searchValue+" page "+page+" size "+size);
		
		//verifica que sea palindromo
		boolean isPalindrome = checkPalindrome(searchValue);
		
		try {
			Long id = Long.parseLong(searchValue);
			Optional<Product> p =  productRepository.findById(id);
			if(p.isPresent()) {
				if(isPalindrome) {
					p.get().setPalindrome(true);
					p.get().setOrginalPrice( p.get().getPrice() );
					p.get().setPrice( (Integer.parseInt(p.get().getPrice()) / 2) + "" );
				}
				return new PageImpl<Product>(Arrays.asList(p.get()),PageRequest.of(0, 1),1);
			}
			
		}catch(NumberFormatException e) {
			//intenta realizar por el termino
		}
			
		Page<Product> result = productRepository.findByBrandRegexOrDescriptionRegex(searchValue,PageRequest.of(page, size));
		
		for (Product product : result) {
			if(isPalindrome) {
				product.setPalindrome(true);
				product.setOrginalPrice( product.getPrice() );
				product.setPrice( (Integer.parseInt(product.getPrice()) / 2) + "" );
			}
		}
		
		
		System.out.println("getNumberOfElements "+result.getNumberOfElements()+" getNumber "+result.getNumber()+" getTotalElements "+result.getTotalElements());
		
		return result;
	}
	
	/**
	 * Permite verificar si una palabra es palindromo
	 * @param searchValue
	 * @return
	 */
	private boolean checkPalindrome(String searchValue) {
		String reverse = "";
	    for (int i = searchValue.length() - 1; i >= 0; i--)
	        reverse = reverse + searchValue.charAt(i);
		return searchValue.equals(reverse);
	}

}
