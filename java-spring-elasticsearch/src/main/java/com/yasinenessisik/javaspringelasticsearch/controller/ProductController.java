package com.yasinenessisik.javaspringelasticsearch.controller;

import com.yasinenessisik.javaspringelasticsearch.ProductService;
import com.yasinenessisik.javaspringelasticsearch.dto.SearchRequestDto;
import com.yasinenessisik.javaspringelasticsearch.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createIndex(Product product){
        return productService.createIndex(product);
    }
    @GetMapping
    public List<Product> getProduct(String index) throws IOException {
        return productService.getAllDataFromIndex(index);
    }
    @GetMapping("/search")
    public List<Product> searchProductByFieldAndValue(SearchRequestDto searchRequestDto){
        return productService.searchProductByFieldAndValue(searchRequestDto);
    }
}
