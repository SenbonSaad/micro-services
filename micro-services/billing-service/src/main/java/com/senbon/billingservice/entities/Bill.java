package com.senbon.billingservice.entities;

import com.senbon.billingservice.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billDate;

    @OneToMany(mappedBy = "bill")
    private List<ProductItem> productItem;

    private long customerID;
    //L'attribut customer se trouve dans la classe mais ne va pas s'afficher dans la base de donneé
    @Transient
    private Customer customer;

}
