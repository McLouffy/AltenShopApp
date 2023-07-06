package com.alten.shop.alterShop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.alten.shop.alterShop.model.Product; 
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	
}
