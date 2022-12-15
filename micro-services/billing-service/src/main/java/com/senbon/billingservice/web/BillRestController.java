package com.senbon.billingservice.web;

import com.senbon.billingservice.entities.Bill;
import com.senbon.billingservice.repository.BillRepository;
import com.senbon.billingservice.repository.ProductItemRepository;
import com.senbon.billingservice.services.CustomerRestClient;
import com.senbon.billingservice.services.ProductRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerRestClient customerRestClient;
    @Autowired
    private ProductRestClient productRestClient;

    public Bill bill(@PathVariable Long id){
        Bill bill = billRepository.findById(id).get(); //.orElse(null);
        bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerID()));
        bill.getProductItem().forEach(pi -> {
            pi.setProduct(productRestClient.findProductById(pi.getProductId()));
        });
        return bill;
    }
}
