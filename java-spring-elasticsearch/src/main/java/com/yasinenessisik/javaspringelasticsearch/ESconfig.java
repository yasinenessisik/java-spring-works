package com.yasinenessisik.javaspringelasticsearch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories
@ComponentScan
public class ESconfig extends ElasticsearchConfiguration {
    @Value("${elasticsearch.url}")
    private String elasticSearchUrl;
    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder().connectedTo(elasticSearchUrl).build();
    }
}
