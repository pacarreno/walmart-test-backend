package cl.walmart.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cl.walmart.domain.Product;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class ProductRepositoryUnitTest {

	@Autowired
	ProductRepository productRepository;
	
	@Test
	void find_all() {
		List<Product> products = productRepository.findAll();
		assertNotNull(products);
		assertNotEquals(0, products.size());
		assertEquals(3000, products.size());
	}
	
	
	@Test
	void find_by_first_page() {
		Page<Product> products = productRepository.findAll(PageRequest.of(0, 20));
		assertNotNull(products.getContent());
		assertNotEquals(0, products.getContent().size());
		assertEquals(20, products.getContent().size());
		assertEquals(true, products.isFirst());
	}
	
	@Test
	void find_by_page() {
		Page<Product> products = productRepository.findAll(PageRequest.of(1, 20));
		assertNotNull(products.getContent());
		assertNotEquals(0, products.getContent().size());
		assertEquals(20, products.getContent().size());
		assertEquals(false, products.isFirst());
	}

}
