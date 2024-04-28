package com.yasinenessisik.javaspringelasticsearch.dto;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Data
public class SearchRequestDto {
    private List<String> fieldNames;
    private List<String> searchValue;

}
