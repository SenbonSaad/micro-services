package com.senbon.inventoryservice;

import com.senbon.inventoryservice.entities.Products;
import com.senbon.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration){
        return args -> {
            restConfiguration.exposeIdsFor(Products.class);
            productRepository.saveAll(
                    List.of(
                            Products.builder().name("Telephone").quantity(2).price(5000).build(),
                            Products.builder().name("Computer").quantity(1).price(30000).build(),
                            Products.builder().name("TV").quantity(20).price(9000).build()
                    )
            );
        };
    }

}
