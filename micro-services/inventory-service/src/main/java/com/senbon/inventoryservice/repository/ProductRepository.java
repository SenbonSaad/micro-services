package com.senbon.inventoryservice.repository;

import com.senbon.inventoryservice.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Products, Long> {
}
