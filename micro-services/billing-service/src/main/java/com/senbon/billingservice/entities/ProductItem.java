package com.senbon.billingservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.senbon.billingservice.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Bill bill;

    private int quantity;
    private double price;
    private double discount;

    @Transient //Je sais qu'il est là, mais pour JPA on l'ignore.
    private Product product;
}
