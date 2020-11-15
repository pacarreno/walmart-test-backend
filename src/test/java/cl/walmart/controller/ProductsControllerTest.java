package cl.walmart.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableSpringDataWebSupport
class ProductsControllerTest {
	
	final String baseURL = "http://localhost:"; 

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    

	@Test
	void find_without_params() {
		
        ResponseEntity<String> responseEntityExpedient = 
        restTemplate.getForEntity(baseURL+port+"/products",  String.class);

        String body = responseEntityExpedient.getBody();
        
        assertEquals(HttpStatus.OK,responseEntityExpedient.getStatusCode());
        assertNotNull(body);
        
        JsonObject convertedObject = new Gson().fromJson(body, JsonObject.class);
        
        assertTrue(convertedObject.isJsonObject());
        assertEquals(20,convertedObject.get("numberOfElements").getAsInt());
    	assertTrue(convertedObject.get("first").getAsBoolean() == true);
    	assertTrue(convertedObject.get("content").getAsJsonArray().size() == 20 );
        
	}
	
	@Test
	void find_search_by_id() {
		
		Long id = 1l;
		
        ResponseEntity<String> responseEntityExpedient = 
        restTemplate.getForEntity(baseURL+port+"/products?searchValue="+id,  String.class);

        String body = responseEntityExpedient.getBody();
        assertNotNull(body);
        
        JsonObject convertedObject = new Gson().fromJson(body, JsonObject.class);
        
        assertTrue(convertedObject.isJsonObject());
    	assertEquals(1,convertedObject.get("numberOfElements").getAsInt());
    	assertTrue(convertedObject.get("first").getAsBoolean() == true);
    	assertTrue(convertedObject.get("content").getAsJsonArray().size() == 1 );
        
	}
	
	@Test
	void find_search_by_value() {
		
		String searchValue = "hqho";
		
        ResponseEntity<String> responseEntityExpedient = 
        restTemplate.getForEntity(baseURL+port+"/products?searchValue="+searchValue,  String.class);

        String body = responseEntityExpedient.getBody();
        assertNotNull(body);
        
	}
	
	@Test
	void find_search_by_invalid_value() {
		
		String invalidValue = "ab";
		
        ResponseEntity<String> responseEntityExpedient = 
        restTemplate.getForEntity(baseURL+port+"/products?searchValue="+invalidValue ,  String.class);

        String body = responseEntityExpedient.getBody();
        assertNotNull(body);
        
	}
	
	@Test
	void find_search_by_palindromo() {
		
		String palindromo = "abba";
		
        ResponseEntity<String> responseEntityExpedient = 
        restTemplate.getForEntity(baseURL+port+"/products?searchValue="+palindromo,  String.class);

        String body = responseEntityExpedient.getBody();
        assertNotNull(body);
        
	}
	
	@Test
	void test_valid_checkpalindrome() {
		ProductsController productController = new ProductsController();
		
		String validPalindrome1 = "1";
		String validPalindrome2 = "131";
		String validPalindrome3 = "a";
		String validPalindrome4 = "asdsa";
		
		assertTrue(productController.checkPalindrome(validPalindrome1));
		assertTrue(productController.checkPalindrome(validPalindrome2));
		assertTrue(productController.checkPalindrome(validPalindrome3));
		assertTrue(productController.checkPalindrome(validPalindrome4));
		
	}
	
	@Test
	void test_invalid_checkpalindrome() {
		ProductsController productController = new ProductsController();
		
		String invalidPalindrome1 = "12";
		String invalidPalindrome2 = "1321";
		String invalidPalindrome3 = "ab";
		String invalidPalindrome4 = "ajdsa";
		
		assertTrue(!productController.checkPalindrome(invalidPalindrome1));
		assertTrue(!productController.checkPalindrome(invalidPalindrome2));
		assertTrue(!productController.checkPalindrome(invalidPalindrome3));
		assertTrue(!productController.checkPalindrome(invalidPalindrome4));
		
	}

}
