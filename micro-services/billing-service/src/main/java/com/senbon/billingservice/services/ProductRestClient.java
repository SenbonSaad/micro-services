package com.senbon.billingservice.services;

import com.senbon.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "INVENTORY-SERVICE")
public interface ProductRestClient {
    @GetMapping(path = "/productses/{id}")
    public Product findProductById(@PathVariable Long id);
    @GetMapping(path = "/productses")
    public PagedModel<Product> allProducts();
}
