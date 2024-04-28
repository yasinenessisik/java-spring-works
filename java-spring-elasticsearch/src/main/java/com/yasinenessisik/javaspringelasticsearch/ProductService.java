package com.yasinenessisik.javaspringelasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.yasinenessisik.javaspringelasticsearch.dto.SearchRequestDto;
import com.yasinenessisik.javaspringelasticsearch.model.Product;
import com.yasinenessisik.javaspringelasticsearch.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import util.ESutil;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final ElasticsearchClient elasticsearchClient;

    public Product createIndex(Product product){
        return productRepository.save(product);
    }
    public List<Product> getAllDataFromIndex(String indexName) throws IOException {
        Query query = ESutil.createMatchAllQuery();
        log.info("Elastich Search Query {}",query.toString());
        SearchResponse<Product> response = elasticsearchClient.search(
                q-> q.index(indexName).query(query), Product.class
        );
        log.info("Elastich Search Response {}",response);
        return extractProductFromResponse(response);
    }

    public List<Product> extractProductFromResponse(SearchResponse<Product> response){
        return response.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
    }

    public List<Product> searchProductByFieldAndValue(SearchRequestDto searchRequestDto) {
        return null;
    }
}
