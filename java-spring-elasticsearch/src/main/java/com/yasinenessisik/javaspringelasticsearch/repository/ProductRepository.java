package com.yasinenessisik.javaspringelasticsearch.repository;

import com.yasinenessisik.javaspringelasticsearch.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product,String> {
}
