package com.yasinenessisik.javaspringelasticsearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yasinenessisik.javaspringelasticsearch.model.Product;
import com.yasinenessisik.javaspringelasticsearch.repository.ProductRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JavaSpringElasticsearchApplication implements ApplicationRunner {
	private final ProductRepository productRepository;

    public JavaSpringElasticsearchApplication(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(JavaSpringElasticsearchApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

	}
}
