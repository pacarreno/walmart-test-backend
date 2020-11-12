package cl.walmart.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cl.walmart.domain.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
	
	Optional<Product> findById(Long id);



}
