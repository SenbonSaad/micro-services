package com.senbon.customerservice;

import com.senbon.customerservice.entities.Customer;
import com.senbon.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration){
        return args -> {
            restConfiguration.exposeIdsFor(Customer.class);
            customerRepository.saveAll(
                    List.of(
                            Customer.builder().name("Saad").email("saadkurosaki@gmail.com").build(),
                            Customer.builder().name("Mouad").email("mouadsaki@gmail.com").build(),
                            Customer.builder().name("Jad").email("jadsaki@gmail.com").build(),
                            Customer.builder().name("Reda").email("redasaki@gmail.com").build(),
                            Customer.builder().name("Youssefi").email("youssefi@gmail.com").build()
                    )
            );
            customerRepository.findAll().forEach(System.out::println);
        };
    }

}
