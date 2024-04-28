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
		List<Product> productList = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();

		for (int i = 1; i <= 20; i++) {
			Product product = new Product();
			product.setId(String.valueOf(i));
			product.setName("Product " + i);
			product.setCategory("Category " + (char)('A' + (i - 1) % 3));
			product.setPrice(100 + i * 10);
			product.setBrand("Brand " + (char)('X' + (i - 1) % 3));
			productList.add(product);
		}

		for (Product product : productList) {
			String json = objectMapper.writeValueAsString(product);
			productRepository.save(product);
		}
	}
}
