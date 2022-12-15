package com.senbon.billingservice;

import com.senbon.billingservice.entities.Bill;
import com.senbon.billingservice.entities.ProductItem;
import com.senbon.billingservice.model.Customer;
import com.senbon.billingservice.model.Product;
import com.senbon.billingservice.repository.BillRepository;
import com.senbon.billingservice.repository.ProductItemRepository;
import com.senbon.billingservice.services.CustomerRestClient;
import com.senbon.billingservice.services.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductRestClient productRestClient){
        return args -> {
            Collection<Product> products = productRestClient.allProducts().getContent();
            Bill bill = new Bill();
            bill.setBillDate(new Date());

            Long customerId=1L;
            Customer customer = customerRestClient.findCustomerById(customerId);
            if (customer ==null) throw new RuntimeException("Customer Not found");

            bill.setCustomerID(customerId);
            Bill savedBill = billRepository.save(bill);
            products.forEach(product -> {
                ProductItem productItem = new ProductItem();
                productItem.setBill(savedBill);
                productItem.setQuantity(1+new Random().nextInt(10));
                productItem.setPrice(product.getPrice());
                productItem.setDiscount(Math.random());
                productItemRepository.save(productItem);
            });

        };
    }
}
